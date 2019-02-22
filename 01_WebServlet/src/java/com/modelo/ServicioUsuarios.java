/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author USUARIO
 */
public class ServicioUsuarios {

    private static ServicioUsuarios instancia;
    private final ArrayList<Usuario> usuarios;

    private ServicioUsuarios() {
        this.usuarios = new ArrayList<Usuario>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (Exception e) {
        }
    }

    public static ServicioUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ServicioUsuarios();
        }
        return instancia;
    }

    public Usuario addUser(Usuario u) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement("INSERT INTO USUARIOS( NOMBRE, EDAD, EMAIL, PASS )  VALUES (? , ? , ? , ?) ");
            st.setString(1, u.getNombre());
            st.setInt(2, u.getEdad());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPassword());
            st.executeUpdate();
            Usuario rUser = validacionPasswd(u.getEmail(), u.getPassword());
            return rUser;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return null;
        }

    }
    
    public boolean eliminarUser(Usuario u){
         try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement("DELETE FROM USUARIOS WHERE ID = ?");
            st.setInt(1, u.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return false;
        }
    }
    
    public Usuario modificarUser(Usuario u){
         try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE USUARIOS SET NOMBRE = ?, EDAD = ?, EMAIL = ?, PASS = ? WHERE ID = ?");
            st.setString(1, u.getNombre());
            st.setInt(2, u.getEdad());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPassword());
            st.setInt(5, u.getId());
            st.executeUpdate();
            return null;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario validacionPasswd(String rEmail, String rPasswd) {
        try (Connection conn = DriverManager.
                getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement(
                    "SELECT ID, NOMBRE, EDAD, EMAIL, PASS  FROM USUARIOS WHERE EMAIL = ? AND PASS = ?");
            st.setString(1, rEmail);
            st.setString(2, rPasswd);
            ResultSet rs = st.executeQuery();
            Usuario u = null;
            if (rs.next()) {
                    String nombre = rs.getString("NOMBRE");
                    int edad = rs.getInt("EDAD");
                    String email = rs.getString("EMAIL");
                    String pass = rs.getString("PASS");
                    u = new Usuario(nombre, edad, email, pass);
                    Integer id = rs.getInt("ID");
                    u.setId(id);
            } 
            return u;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

   
}
