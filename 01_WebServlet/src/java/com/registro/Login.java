package com.registro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.modelo.ServicioUsuarios;
import com.modelo.Usuario;
import com.webservlet.Utilidades;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author USUARIO
 */
public class Login extends HttpServlet {

   


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                if(Utilidades.comprobarParam(request)){
                    String email = request.getParameter("email");
                    String pass = request.getParameter("psswd");
                    ServicioUsuarios service = ServicioUsuarios.getInstancia();
                    Usuario u = service.readUser(email, pass);
                    if(u != null){
                        HttpSession session = request.getSession();
                        session.setAttribute("Usuario", u);
                        request.getRequestDispatcher("Page.jsp").forward(request, response);
                    }else{
                        request.setAttribute("errorUser", "si");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }else{
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
}
