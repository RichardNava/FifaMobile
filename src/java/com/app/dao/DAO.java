/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dao;

import com.app.models.Futbolista;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO<T> {

    public DAO() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Error en la carga del Driver", ex);
        }
    }
    
    public static final String URL = "jdbc:sqlite:FutbolManager.db";

    public abstract void grabar(T elemento);

    protected abstract T leer(String... value);

    public abstract boolean eliminar(String value); // String value

    public abstract boolean eliminar(String... valores);

    public abstract void modificar(String... valores);

    public abstract List<T> leerTodos(String table);


}
/*

import com.richard.csv.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
        private final static String URL = "jdbc:sqlite:football_stats.db";


    private List<Player> players = new ArrayList<>();
    static {
         try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, " El driver no carga ", ex);
        }
    }
    public DBConnect(){
        showData();
   
    }
       
    public void insertData() {
       
        try (Connection con = DriverManager.getConnection(URL)) {

            Statement st = con.createStatement();

            st.execute("INSERT INTO player (name,team) VALUES ('Lassana diarra','RMA')");

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertData( String name, String team) {
       
       
        try (Connection con = DriverManager.getConnection(URL)) {
            PreparedStatement st = con.prepareStatement("INSERT INTO player (name,team) VALUES (?,?)");

            st.setString(1, name);
            st.setString(2, team);

            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertData( List<Player> data) {
     
        try (Connection con = DriverManager.getConnection(URL)) {
            PreparedStatement st = con.prepareStatement("INSERT INTO player (name,team,Minutos,TAmarillas,Goles,FuerasDeJuego,FaltasRecibidas,FaltasCometidas,Centros,Corners,Entradas,Duelos,DuelosCuerpo,DuelosAire,Pases,PasesCortos,PasesLargos,PasesHueco,Tiros,TirosPuerta,Asistencias,RegatesExito,RegatesFracaso,Recuperaciones,EntradasExito,EntradasFracaso,DuelosCuerpoExito,DuelosCuerpoFracaso,DuelosAireExito,DuelosAireFracaso,PrecisionTiros,Regates,PrecisionRegates,Importante) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            for (Player pl : data) {
                st.setString(1, pl.getNombre());
                st.setString(2, pl.getEquipo());
                for (int i = 0; i < pl.getStats().length; i++) {
                    st.setDouble(i + 3, pl.getStats()[i]);

                }
                st.execute();
            }

            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showData() {
       
        try (Connection con = DriverManager.getConnection(URL)) {

            Statement st = con.createStatement();

            st.execute("SELECT * FROM player");

            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                //    System.out.println("Nombre: " + rs.getString(2) + " -Equipo: " + rs.getString("team"));
                String[] datos = new String[35];
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = (i == 0) ? "" + rs.getInt(i + 1) : (i < 3) ? rs.getString(i + 1) : "" + rs.getDouble(i + 1);

                }
                players.add(Player.ofPlayer(datos));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void createUser(String user,String email, String password) {
       
        try (Connection con = DriverManager.getConnection(URL)) {

            Statement st = con.createStatement();

            st.execute("INSERT INTO user (username,email,password) VALUES ('"+user+"','"+email+"','"+password+"')");

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
 */
