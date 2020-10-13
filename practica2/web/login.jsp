<%-- 
    Document   : login
    Created on : 30-sep-2020, 10:30:31
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <CENTER>
        <h1 class="alert alert-primary">Login</h1>
        <br>    
        <form action="login" method="POST">
            <input type="text" placeholder="Usuario" name="usuario" required>
            <br>
            <br>
            <input type="password" placeholder="Contraseña" name="password" required>
            <br>
            <br>
            <input type="submit" class="btn btn-primary" value="Enviar">
            <button class="btn btn-secondary" onclick="window.location.href='registrarUsuario.jsp'">Registrar</button>
            
        </form>
        
    </CENTER>
    </body>
</html>
