package com.app.dao;

import static com.app.dao.DAO.URL;
import com.app.models.Futbolista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFutbolista extends DAO<Futbolista> {

    @Override
    protected Futbolista leer(String... value) {
        Futbolista f = null;
        try (Connection myCon = DriverManager.getConnection("jdbc:sqlite:FutbolManager.db")) {
            PreparedStatement pst = myCon.prepareStatement("SELECT * FROM Futbolista WHERE Posicion = ?");
            pst.setString(1, value[0]);
            ResultSet rs = pst.executeQuery();
            try {
                while (rs.next()) {
                    f = new Futbolista();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Error SQL", ex);
            }

            return f;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Ha habido un error de SQL", ex);
        }
        return f;
    }

    @Override
    public List<Futbolista> leerTodos(String table) {
        ArrayList<Futbolista> futbolistas = new ArrayList<>();
        try (Connection myConn = DriverManager.getConnection(URL)) {
            Statement st = myConn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + table);
            while (rs.next()) {
                futbolistas.add(new Futbolista(rs.getString("Nombre"), rs.getString("Apellidos"),
                        rs.getInt("Edad"), rs.getString("Nacionalidad"), rs.getString("Posicion"),
                        rs.getInt("Defensa"), rs.getInt("Ataque"), rs.getInt("Pase"), rs.getInt("Ritmo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Error de SQL", ex);
        }
        return futbolistas;
    }

    @Override
    public void grabar(Futbolista elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String... valores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(String... valores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
