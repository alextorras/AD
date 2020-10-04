<%-- 
    Document   : eliminarImagen
    Created on : 03-oct-2020, 11:33:43
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
        <h1>Eliminar Imagen</h1>
        <text>Estas seguro que quieres eliminar la imagen?</text>
        <form action="eliminarImagen" method="POST">
            <input type="button" name="boto_si" value="Si" onclick="window.location.href='eliminarImagen'"><br>
            <input type="button" name="boto_no" value="No" onclick="window.location.href='eliminarImagen'">
        </form>
    </body>
</html>
