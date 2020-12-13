<%-- 
    Document   : Login
    Created on : 09-dic-2020, 16:24:24
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String path = "http://localhost:44742/HelloWorld/";%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <CENTER>
        <h1 class="alert alert-primary">Login</h1>
        <br>
        <form action="<%=path%>resources/generic/login" method="POST">
            <input type="text" placeholder="Usuario" name="usuario" required>
            <br>
            <br>
            <input type="password" placeholder="Contraseña" name="password" required>
            <br>
            <input style="margin-top: 10px" type="submit" class="btn btn-primary" value="Enviar">
            
        </form>
            <button style="margin-top: 10px" class="btn btn-secondary" onclick="window.location.href='registrarUsuario.jsp'">Registrar</button>
    </CENTER
    </body>
</html>


>