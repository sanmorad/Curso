/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro;

import com.modelo.ServicioUsuarios;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USUARIO
 */

/*
create table "ROOT".USUARIOS
(
    ID INT not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,
    NOMBRE VARCHAR(50) not null,
    EDAD INTEGER not null,
    EMAIL VARCHAR(50) unique,
    PASS VARCHAR(50) not null,
    primary key (ID)
)
*/
public class UserOptions extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String opcion = request.getParameter("boton");
        ServicioUsuarios servicio = ServicioUsuarios.getInstancia();
        HttpSession sesion = request.getSession();
        Usuario u  = (Usuario)sesion.getAttribute("Usuario");
       switch(opcion){
           case "Modificar":
               sesion.setAttribute("function", "Edit");
               request.getRequestDispatcher("registro.jsp").forward(request, response);
               break;
           case "Eliminar":
               servicio.eliminarUser(u);
               request.setAttribute("Mssg", "Usuario "+u.getNombre()+" eliminado");
               sesion.removeAttribute("Usuario");
               request.getRequestDispatcher("index.html").forward(request, response);
               break;
           case "Salir":
                sesion.removeAttribute("Usuario");
               request.getRequestDispatcher("index.html").forward(request, response);
               break;
           case "Eliminar otro Usuario":
               sesion.setAttribute("function", "Delete");
               request.getRequestDispatcher("registro.jsp").forward(request, response);
               request.getRequestDispatcher("index.html").forward(request, response);
               break;
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
