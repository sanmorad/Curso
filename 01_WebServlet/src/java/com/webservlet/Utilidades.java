/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author USUARIO
 */
public class Utilidades {
    
    public static boolean comprobarParam(HttpServletRequest req) {
        List<String> fails = new ArrayList<>();
        boolean OK = true;
        Enumeration<String> names = req.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            if (req.getParameter(name).equals("")) {
            fails.add(name);
            OK =  false;
        } 
        }
        if(!OK){
            String[] s =(String[]) fails.toArray(new String[fails.size()]);
            req.setAttribute("fails", s);
        }
        return OK;
    }
    
    public static int getIntEdad(HttpServletRequest req, String edad){
        int rtn = 0;
        if(edad.matches("^[0-9]*$")){
           int iEdad = Integer.parseInt(edad);
           if(iEdad >= 18){
               rtn = iEdad;
           }else{
               req.setAttribute("ErrorRegistro", "Es menor de 18 años");
               rtn = 0;
           }
        }else{
            req.setAttribute("ErrorRegistro", "No es un numero");
            rtn = 0;
        }
        return rtn;
    }
    
    public static boolean validarEdad(String edad) {
        return edad.matches("^[0-9]*$");
    }
}
