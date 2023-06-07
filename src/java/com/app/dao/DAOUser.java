package com.app.dao;

import com.app.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.util.List;

public class DAOUser extends DAO<User> {

    public DAOUser() {

    }

    @Override
    public void grabar(User elemento) {
        try (Connection myCon = DriverManager.getConnection("jdbc:sqlite:FutbolManager.db")) {
            PreparedStatement pst = myCon.prepareStatement("INSERT INTO Usuario (Email, Nombre, Password) VALUES(?,?,?)");
            pst.setString(1, elemento.getEmail());
            pst.setString(2, elemento.getUsername());
            pst.setString(3, elemento.getPassword());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Ha habido un error de SQL", ex);
        }
    }

    @Override
    public boolean eliminar(String... valores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(String... valores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User leer(String... value) {
        User u = null;
        try (Connection myCon = DriverManager.getConnection("jdbc:sqlite:FutbolManager.db")) {
            PreparedStatement pst = myCon.prepareStatement("SELECT * FROM Usuario WHERE Email = ?");
            pst.setString(1, value[0]);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                try {
                    while (rs.next()) {
                        u = new User(rs.getString(2), rs.getString(1), rs.getString(3));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Error SQL", ex);
                }
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Ha habido un error de SQL", ex);
        }
        return u;
    }

    @Override
    public List<User> leerTodos(String table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
