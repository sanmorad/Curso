<%-- 
    Document   : eliminar
    Created on : 25-feb-2019, 12:11:08
    Author     : USUARIO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.modelo.Usuario"%>
<%@page import="java.util.List"%>
<% 
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");
    if(lista == null || lista.size() < 1){
        %> <h1>No hay usuarios</h1><%
    }else{
    
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar</title>
    </head>
    <body>
        <h1>Eliminar Usuario</h1>
        <form name="formDelete" method="POST" action="userOptions.do">
            <select name="eleccion">
                <% for(Usuario u : lista){
                    out.println("<option value=\""+u.getId()+"\">"+u.getNombre()+"</option>");
                }
                %>
            </select>
            <input type="hidden" name="type" id="type" value="delete"/>
            <input type="submit" value="Eliminar" />
        </form>
    </body>
</html>
<%} %>