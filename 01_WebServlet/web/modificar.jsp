<%-- 
    Document   : modificar
    Created on : 25-feb-2019, 12:10:20
    Author     : USUARIO
--%>

<%@page import="java.util.List"%>
<%@page import="com.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");
    if(lista == null || lista.size() < 1){
        %> <h1>No hay usuarios</h1><%
    }else{
    Usuario uEdit = (Usuario) request.getAttribute("UserEdit");
    boolean editar = false;
    String nombre = "";
    String edad = "";
    String email = "";
    String pass = "";
    if(uEdit != null){
        editar = true;
        nombre = uEdit.getNombre();
        edad = String.valueOf(uEdit.getEdad());
        email = uEdit.getEmail();
        pass  = uEdit.getPassword();
    }
        
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar</title>
    </head>
    <body>
        <h1>Modificar Usuario</h1>
        <form name="formModify" method="POST" action="userOptions.do">
            <select name="eleccion">
                <% for(Usuario u : lista){
                    out.println("<option value=\""+u.getId()+"\">"+u.getNombre()+"</option>");
                }
                %>
            </select>
            <input type="hidden" name="type" id="type" value="delete"/>
            <input type="submit" value="Eliminar" />
        </form>
            <br/>
            <br/>
            <br/>
         <table border="1">
                <tr><td>Nombre:</td><td>
                        <input type="text" name="nom" id="nom" size="25" value="<%=nombre %>" <%if(editar){%>disabled<%}%>/>  </td></tr>
                <tr><td>Edad:</td><td>
                        <input type="text" name="eda" id="eda" value="<%=edad %>" <%if(editar){%>disabled<%}%>/>  </td></tr> 
               <tr><td>Email</td><td>
                        <input type="email" name="email" id="email" value="<%=email %>" <%if(editar){%>disabled<%}%>/>  </td></tr> 
                    <tr><td>Contraseña</td><td>
                        <input type="password" name="psswd" id="psswd" value="<%=pass %>" <%if(editar){%>disabled<%}%>/>  </td></tr> 
            </table>
                        <input type="submit" value="Enviar" <%if(editar){%>disabled<%}%>/>
        </form>
    </body>
</html>
<%} %>
