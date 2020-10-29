<%-- 
    Document   : registrarUsuario
    Created on : 22-oct-2020, 16:25:03
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%String path = "http://localhost:8080/RestAD/"; %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Registrar Usuario</title>
    </head>
    <body>
    <CENTER>
        <h1 class="alert alert-primary">Bienvenido usuario nuevo</h1>
        <br>
        <text class="alert alert-warning" style="margin-top: 10px;">Introduce los valores para registrarte</text>
        <br>
        <form action="<%=path%>webresources/generic/registrarUsuario" method="POST">
            <br>
            <input style="margin-top: 10px;" type="text" placeholder="Usuario" name="newUser" required>
            <br>
            <input style="margin-top: 10px;" type="password" placeholder="Contraseña" name="newPassword" required>
            <br>
            <input style="margin-top: 10px;" type="submit" class="btn btn-primary" value="Registrar">
        </form>
    </CENTER>
    </body>
</html>
