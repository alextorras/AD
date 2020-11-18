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
        <input type="BUTTON" style="margin-top: 10px" value="Listar Imagenes" class="btn btn-secondary" onclick="window.location.href='http://localhost:8080/RestAD/webresources/generic/list'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen" class="btn btn-dark" onclick="window.location.href='buscarImagen.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen por Id" class="btn btn-dark" onclick="window.location.href='buscarImagenporId.jsp'">
        <br>
         <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen por titulo" class="btn btn-dark" onclick="window.location.href='buscarImagenporTitulo.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen por autor" class="btn btn-dark" onclick="window.location.href='buscarImagenporAutor.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen por fecha creación" class="btn btn-dark" onclick="window.location.href='buscarImagenporDatac.jsp'">
        <br>
                 <input type="BUTTON" style="margin-top: 10px" value="Buscar Imagen por keywords" class="btn btn-dark" onclick="window.location.href='buscarImagenporTitulo.jsp'">
        <br>
        <input type="BUTTON" style="margin-top: 10px" value="Eliminar Imagen" class="btn-dark" onclick="window.location.href='eliminarImagen.jsp'">
        <br>        
        <input type="BUTTON" style="margin-top: 10px" value="Modificar Imagen" class="btn-dark" onclick="window.location.href='modificarImagen.jsp'">
        <br>
        </form>
    </CENTER>
    </body>
</html>
