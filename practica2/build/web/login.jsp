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
        <h1>Practica 2!</h1>
        <br>
    <CENTER>
        <form action="login" method="POST">
            <input type="text" placeholder="Usuario" name="usuario" required>
            <br>
            <br>
            <input type="password" placeholder="Contraseña" name="password" required>
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </CENTER>
    </body>
</html>
