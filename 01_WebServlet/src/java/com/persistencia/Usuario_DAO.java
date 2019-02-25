/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.modelo.Usuario;
import java.util.List;


/**
 *
 * @author USUARIO
 */
public interface Usuario_DAO {
    public Usuario readUser(String email, String pass);
    
    public Usuario addUser(Usuario u);
    
    public boolean deleteUser(int id); 
    
    public Usuario modifyUser(Usuario u); 
    
    public List<Usuario> allUser(int localUser);
}
