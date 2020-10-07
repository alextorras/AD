<%-- 
    Document   : registrarImagen
    Created on : 03-oct-2020, 11:33:23
    Author     : Àlex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="registrarImagen" method="POST" enctype="multipart/form-data">
       
            <input type="file" id="imatge" name="imatge" required autofocus> <br>
            <input type="text" name="titol" placeholder="Títol" required><br>
            <input   type="text" name="descripcio" placeholder="Descripció" required><br>
            <input  type="text" name="keywords" placeholder="Keywords" required><br>
            <input   type="text" name="autor" placeholder="Autor" required><br>
            <input   type="text" name="datacreation" placeholder="aaaa/mm/dd" required><br>
            <button style="margin-top: 10px;" type="submit">Submit</button>
        </form>
    </body>
</html>
