<%-- 
    Document   : Page
    Created on : 22-feb-2019, 10:29:28
    Author     : USUARIO
--%>

<%@page import="com.modelo.Usuario"%>
<% 
    String nombre = null;
    Usuario u = (Usuario)session.getAttribute("Usuario");
    nombre = u.getNombre();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido <%= nombre %></h1>
        <form name="form3" method="post" action="userOptions.do">
            <input type="submit" name="boton" value="Salir"/>
            <input type="submit" name="boton" value="Modificar"/>
            <input type="submit" name="boton" value="Eliminar"/>
        </form>
    </body>
</html>
