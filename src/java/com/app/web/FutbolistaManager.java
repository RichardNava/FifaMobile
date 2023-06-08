package com.app.web;

import com.app.dao.DAOFutbolista;
import com.app.models.Futbolista;
import com.app.models.Posicion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// ASIGNAR LAS SIGUIENTE ANOTACIONES EN UNA CLASE
// LA CONVIERTE EN UN CDI BEAN O MANAGED BEAN
// Esto significa que podemos usarlo desde la vista tal cual hacemos en Java
@Named("fm")  // Con esta anotación indicamos el nombre que recibe el objeto desde web
@ApplicationScoped // El alcance o ambito de uso. En este caso la información 
                   // se mantiene en toda la aplicación
public class FutbolistaManager implements Serializable {
    // Número de versión, aconsejable para prevenir incoherencia en la serialización
    private static final long serialVersionUID = 1L;

    private transient Map<Posicion, List<Futbolista>> futbolistasPorPosicion; // Usamos un Map para dividir los jugadores en función de su posición
    private Posicion posicionActual; // Este atributo permite saber que posición estamos revisando y sirve de flag (bandera) para pasar a la siguiente posición
    private ArrayList<Futbolista> futbolistasSeleccionados; // En esta lista almacenaremos los futbolistas que se seleccionen en la aplicación
    private transient List<Futbolista> futbolistasActuales; // En esta lista volcaremos los futbolistas que tenemos que revisar dependiendo de la posición que corresponda

    // El constructor sin argumentos es obligatorio para poder usar el BEAN desde web
    public FutbolistaManager() { 
        // Inicializar la estructura de datos con los futbolistas por posición
        futbolistasPorPosicion = new HashMap<>();
        /* Para añadir los datos en este caso utilizo el mismo método pero cambiando la clausula WHERE.
           No es la solución más eficiente porque para obtener todos los datos estamos usando 4 conexiones a la BBDD.
           Otra posibilidad sería utlizar bucles para separar todos los resultados de una consulta sin WHERE, tampoco es
           la solución más eficiente, lo mejor sería aplicar un criterio de filtrado con programación funcional (Si, se que soy pesado, pero es la verdad)
        */   
        futbolistasPorPosicion.put(Posicion.POR, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"POR\"")); 
        futbolistasPorPosicion.put(Posicion.DEF, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"DEF\""));
        futbolistasPorPosicion.put(Posicion.MED, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"MED\""));
        futbolistasPorPosicion.put(Posicion.DEL, new DAOFutbolista().leerTodos("Futbolista WHERE Posicion = \"DEL\""));
        // Inicializar el resto de datos
        posicionActual = Posicion.POR;
        futbolistasSeleccionados = new ArrayList<>();
    }

    public List<Futbolista> getFutbolistasActuales() {
        // Obtenemos la lista de jugadores que corresponde a la posición actual y lo devolvemos para usarla en web mostrandolo en una tabla
        futbolistasActuales = futbolistasPorPosicion.get(posicionActual);
        return futbolistasActuales;
    }

    public String siguientePosicion() {
        // Iteramos en los futbolistas que estamos usando actualmente (por posición)
        for (Futbolista futbolista : futbolistasActuales) {
            // Si el usuario ha hecho click en el checkBox se actualizara el atributo booleano de los jugadores
            // esto nos permite saber que jugadores han sido seleccionados
            if(futbolista.isSeleccionado()){ // La condición es que el usario los haya elegido 
                futbolistasSeleccionados.add(futbolista); // Añadimos al jugador a la lista de futbolistas seleccionados
            }
        }
        Posicion siguientePosicion = posicionActual.siguientePosicion(); // Usamos el método del enum Posicion para avanzar una posición
        // Comprobamos que la posición sea distinta a null y que nuestro map contenga esa posición como clave (evitando posibles errores de la BBDD)
        if (siguientePosicion!=null && futbolistasPorPosicion.containsKey(siguientePosicion)) { 
            posicionActual = siguientePosicion; // Actualizamos la posición actual a la que corresponde
            return "show"; // Devolvemos el alias especificado en el archivo "/web pages/WEB-INF/faces-config.xml" que nos permite navegar a 'show.xhtml'
        }
        else{
            return "resumen"; // En caso contrario, cuando ya hemos terminado de revisar todas las posiciones, navegamos a 'resumen.xhtml'
        }
    }
    // Getters y Setters - Son obligatorios porque se usan desde el xhtml para ver o editar los valores
    public Map<Posicion, List<Futbolista>> getFutbolistasPorPosicion() {
        return futbolistasPorPosicion;
    }

    public void setFutbolistasPorPosicion(Map<Posicion, List<Futbolista>> futbolistasPorPosicion) {
        this.futbolistasPorPosicion = futbolistasPorPosicion;
    }

    public Posicion getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Posicion posicionActual) {
        this.posicionActual = posicionActual;
    }

    public ArrayList<Futbolista> getFutbolistasSeleccionados() {
        return futbolistasSeleccionados;
    }

    public void setFutbolistasSeleccionados(ArrayList<Futbolista> futbolistasSeleccionados) {
        this.futbolistasSeleccionados = futbolistasSeleccionados;
    }
    
}
