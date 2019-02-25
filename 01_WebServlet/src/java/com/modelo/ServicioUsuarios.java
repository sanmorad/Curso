/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.persistencia.JDB_DAO;
import com.persistencia.Usuario_DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author USUARIO
 */
public class ServicioUsuarios {

    private static ServicioUsuarios instancia;
    private Usuario_DAO persistencia;
    
    
    private ServicioUsuarios() {
        persistencia = JDB_DAO.getInstancia();
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
        return persistencia.addUser(u);
    }
    
    public boolean eliminarUser(int id){
          return persistencia.deleteUser(id);
    }
    
    public Usuario modificarUser(Usuario u){
          return persistencia.modifyUser(u);
    }

    public Usuario readUser(String rEmail, String rPasswd) {
         return persistencia.readUser(rEmail,rPasswd);
    }

    public List<Usuario> readAll(int localUser){
        return persistencia.allUser(localUser);
    }
   
}
