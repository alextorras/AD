<%-- 
    Document   : eliminarImagen
    Created on : 03-oct-2020, 11:33:43
    Author     : Dani
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    final String path = "http://localhost:8080/RestAD/";
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Eliminar Imagen</title>
    </head>
    <body>
        <CENTER>
        <h1 class="alter alert-primary">Eliminar Imagen</h1>
        <br>
        <text class="alert alert-warning">Inserte el ID de la imagen</text>
        <form action="<%=path%>webresources/generic/delete" method="POST" enctype="multipart/form-data">
            <br>
            <input type="text" name="id" placeholder="ID">
            <br>
            <input style="margin-top: 10px" type="submit" name="boto_si" class="btn btn-primary" value="Si">
            <input style="margin-top: 10px" type="button" name="boto_no" value="No" class="btn btn-secondary" onclick="window.location.href='menu.jsp'">
        </form>
    </CENTER>   
    </body>
</html>
