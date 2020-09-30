<%-- 
    Document   : login
    Created on : 30-sep-2020, 10:30:31
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Practica 2!</h1>
        <form action="Login" method="POST">
            <label>Usuario:</label>
            <input type="text" name="usuario">
            <br>
            
            <label>Contraseña: </label>
            <input type="passowrd" name="password">
            <input type="submit" value="Enviar">
            <%/*<input type="file" name="formulario">*/%>
        </form>
    </body>
</html>
