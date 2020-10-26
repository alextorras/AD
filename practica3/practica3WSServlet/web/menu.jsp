<%-- 
    Document   : menu
    Created on : 01-oct-2020, 7:45:34
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Menú</title>
    </head>
    <body>
        <CENTER>
        <h1 class="alert alert-primary">Menú</h1>
        <form>
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Registrar Imagen" class="btn btn-primary" onclick="window.location.href='registrarImagen.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Listar Imagenes" class="btn btn-secondary" onclick="window.location.href='listImg.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen" class="btn btn-dark" onclick="window.location.href='buscarImagen.jsp'">
        <br>
        <br>        
        <input type="BUTTON" style="margin-top: 10px" value="Logout" class="btn btn-info" onclick="window.location.href='logout.jsp'">
        <br>
        <% /*<input type="BUTTON" style="margin-top: 10px" value="Eliminar Imagen" class="btn btn-info" onclick="window.location.href='eliminarImagen.jsp'"> */ %>
        </form>
    </CENTER>
    </body>
</html>
