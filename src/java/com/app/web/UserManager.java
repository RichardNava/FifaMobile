package com.app.web;

import com.app.dao.DAOUser;
import com.app.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;

// ASIGNAR LAS SIGUIENTE ANOTACIONES EN UNA CLASE
// LA CONVIERTE EN UN CDI BEAN O MANAGED BEAN
// Esto significa que podemos usarlo desde la vista (web) tal cual hacemos en Java
@Named("um") // Con esta anotación indicamos el nombre que recibe el objeto desde web
@ApplicationScoped // El alcance o ambito de uso. En este caso la información 
                   // se mantiene en toda la aplicación
public class UserManager implements Serializable { // Implementar la interfaz serializable no es obligatorio pero se recomienda para ciertos procesos del BEAN
    // Número de versión, aconsejable para prevenir incoherencia en la serialización
    private static final long serialVersionUID = 1L;
    // Atributos
    private String username, email, password, mensaje;
    private boolean logeado;
    // El objeto que nos permite acceder a las funcionalidades CRUD con la BBDD
    // Esta marcado como final (es una constante) y transient (No entra en el proceso de serialización)
    private transient final DAOUser dao; 
    // Aqui almacenamos la información del usuario actual (el que esta usando la aplicación)
    private User currentUser;

    // El constructor sin argumentos es obligatorio para poder usar el BEAN desde web
    public UserManager() {
        dao = new DAOUser();
    }
    // Getters y Setters - Son obligatorios porque se usan desde el xhtml para ver o editar los valores
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
    // El siguiente método nos permite verificar si el usuario puede acceder a la aplicación
    public String entrar() {
        // Comprobamos que se hayan introducido los datos necesarios
        if (this.email.isBlank() || this.password.isBlank()) {
            // En caso de que no sea así, editamos el mensaje para mostrarle al usuario que debe 
            // introducir valores en los campos
            mensaje = "Debe introducir los datos";
            return ""; // Devolver un String vacio a la ruta de navegación significa refrescar la página actual
                       // En este caso la refrescaremos con el mensaje editado
        }
        currentUser = dao.leer(email); // Recuperamos la información de la BBDD que coincide con el email que nos han pasado
        if (currentUser == null) { // Verificamos si hemos fallado en la busqueda
            // Editamos el mensaje para indicar al usuario que no existe ninguna cuenta asociada a ese email
            mensaje = "No existe ningun usuario con ese email";
            return ""; // Refrescamos la página como os he explicado en la linea 86
            // En caso de que exista el registro, comprobamos si las contraseñas coinciden
        } else if (currentUser.getPassword().equals(this.password)) {
            // Limpiamos el mensaje porque ya no hará falta
            mensaje = null;
            return "show"; // Navegamos a 'show.xhtml' como se indica en las reglas de navegación del archivo "/web pages/WEB-INF/faces-config.xml"
        }
        // Si todo lo anterior falla es que la contraseña no coincide. Editamos el mensaje para comunicarlo
        mensaje = "La contraseña no es válida";
        return ""; // Refrescamos la página
    }
    // El siguiente método los usaremos para viajar al index reseteando valores
    public String reset() {
        // Reseteamos todos los valores
        this.username = "";
        this.password = "";
        this.email = "";
        this.mensaje = "";
        this.currentUser = null;

        return "index"; // // Navegamos a 'index.xhtml' como se indica en las reglas de navegación del archivo "/web pages/WEB-INF/faces-config.xml"
    }
    // El siguiente método los usaremos en la página de creación de usuario 'log.xhtml'
    public String create() {
        // TODO: Verificar que se han facilitado todos los datos
        // Asignamos los datos a una instancia de User que almacenamos en nuestro campo de clase que representa el usuario actual
        currentUser = new User(username, email, password);
        // Usamos nuestro objeto DAO para crear el registro en la BBDD 
        dao.grabar(currentUser);
        // Editamos el mensaje para indicar que el usuario se ha creado con éxito
        mensaje = "Usuario creado con exito";
        return ""; // Refrescamos la página en la que se mostrará el mensaje
    }

}
