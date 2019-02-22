<%-- 
    Document   : login
    Created on : 22-feb-2019, 10:35:52
    Author     : USUARIO
--%>

<%@page import="com.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    session.setAttribute("function", "Edit");
               request.getRequestDispatcher("registro.jsp").forward(request, response);
    
    String[] fails = (String[]) request.getAttribute("fails");
    if(fails != null){
        for(String f : fails){
            %><p style="color: red">Falta: <%=f%></p> <%
        }
        request.removeAttribute("fail");
    }else{
        String errorUser = (String) request.getAttribute("ErrorRegistro");
         if(errorUser != null){
            %><p style="color: red"><%=errorUser%></p> <%
            request.removeAttribute("ErrorRegistro");
        }       
    }
    Usuario u = (Usuario)session.getAttribute("Usuario");
    String nombre = "";
    String edad = "";
    String email = "";
    String pass = "";
    if(u != null){
        nombre = u.getNombre();
        edad = String.valueOf(u.getEdad());
        email = u.getEmail();
        pass  = u.getPassword();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div>Formulario HTML de registro</div>          
        <form name="form1" method="post" action="registro.do">

            <table border="1">
                <tr><td>Nombre:</td><td>
                        <input type="text" name="nom" id="nom" size="25" value="<%=nombre %>"/>  </td></tr>
                <tr><td>Edad:</td><td>
                        <input type="text" name="eda" id="eda" value="<%=edad %>"/>  </td></tr> 
               <tr><td>Email</td><td>
                        <input type="email" name="email" id="email" value="<%=email %>"/>  </td></tr> 
                    <tr><td>Contraseña</td><td>
                        <input type="password" name="psswd" id="psswd" value="<%=pass %>"/>  </td></tr> 
            </table>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
