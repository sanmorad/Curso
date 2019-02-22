<%-- 
    Document   : login
    Created on : 22-feb-2019, 10:35:52
    Author     : USUARIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    if(session.getAttribute("Usuario") != null){
        request.getRequestDispatcher("Page.jsp").forward(request, response);
    }else{
        String[] fails = (String[]) request.getAttribute("fails");
     if(fails != null){
         for(String f : fails){
             %><p style="color: red">Falta: <%=f%></p> <%
         }
         request.removeAttribute("fail");
     }else{
         String errorUser = (String) request.getAttribute("errorUser");
          if(errorUser != null){
             %><p style="color: red">Error en email/contraseña</p> <%
             request.removeAttribute("errorUser");
         }       
     } 
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div>Formulario HTML de login</div>    
        <form name="form1" method="post" action="login.do">

            <table border="1">
               <tr><td>Email</td><td>
                        <input type="email" name="email" id="email" value=""/>  </td></tr> 
                    <tr><td>Contraseña</td><td>
                        <input type="password" name="psswd" id="psswd" value=""/>  </td></tr> 
            </table>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
