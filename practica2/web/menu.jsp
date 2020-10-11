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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Menu</h1>
    <CENTER>
        <form>
        <br>
        <input type="BUTTON" value="Registrar Imagen" onclick="window.location.href='registrarImagen.jsp'">
        <br>
        <br>
        <input type="BUTTON" value="Listar Imagenes" onclick="window.location.href='listImg.jsp'">
        <br>
        <br>
        <input type="BUTTON" value="Buscar Imagen" onclick="window.location.href='buscarImagen.jsp'">
        <br>
        <br>
        <% /*<input type="BUTTON" value="Eliminar Imagen" onclick="window.location.href='eliminarImagen.jsp'"> */ %>
        </form>
    </CENTER>
    </body>
</html>
