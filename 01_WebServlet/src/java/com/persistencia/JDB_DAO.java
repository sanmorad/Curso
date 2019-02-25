/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class JDB_DAO implements Usuario_DAO{

    private static JDB_DAO instancia;
    
    private JDB_DAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (Exception e) {
        }
    }
    
    public static JDB_DAO getInstancia() {
        if (instancia == null) {
            instancia = new JDB_DAO();
        }
        return instancia;
    }
    
    

    @Override
    public Usuario readUser(String email, String pass) {
       try (Connection conn = DriverManager.
                getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement(
                    "SELECT ID, NOMBRE, EDAD, EMAIL, PASS  FROM USUARIOS WHERE EMAIL = ? AND PASS = ?");
            st.setString(1, email);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            Usuario u = null;
            if (rs.next()) {
                    String nombre = rs.getString("NOMBRE");
                    int edad = rs.getInt("EDAD");
                    String emailDB = rs.getString("EMAIL");
                    String passDB = rs.getString("PASS");
                    u = new Usuario(nombre, edad, emailDB, passDB);
                    Integer id = rs.getInt("ID");
                    u.setId(id);
            } 
            return u;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario addUser(Usuario u) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement("INSERT INTO USUARIOS( NOMBRE, EDAD, EMAIL, PASS )  VALUES (? , ? , ? , ?) ");
            st.setString(1, u.getNombre());
            st.setInt(2, u.getEdad());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPassword());
            st.executeUpdate();
            Usuario rUser = readUser(u.getEmail(), u.getPassword());
            return rUser;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement("DELETE FROM USUARIOS WHERE ID = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario modifyUser(Usuario u) {
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

    @Override
    public List<Usuario> allUser(int localUser) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyServerDB", "root", "root")) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM USUARIOS");
            ResultSet rs = st.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                    String nombre = rs.getString("NOMBRE");
                    int edad = rs.getInt("EDAD");
                    String emailDB = rs.getString("EMAIL");
                    String passDB = rs.getString("PASS");
                    Integer id = rs.getInt("ID");
                    Usuario u = new Usuario(nombre, edad, emailDB, passDB, id);
                    if(id != localUser){
                        lista.add(u);
                    }
            } 
            return lista;
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace();
            return null;
        }
    }
    
}
