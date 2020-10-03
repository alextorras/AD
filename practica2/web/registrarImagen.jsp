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
        <form action="registrarImagen.jsp" method="POST">
       
            <input type="file" id="imatge" name="imatge" required autofocus> <br>
            <input type="text" name="titol" placeholder="Títol" required><br>
            <input   type="text" name="descripcio" placeholder="Descripció" required><br>
            <input  type="text" name="keywords" placeholder="Keywords" required><br>
            <input   type="text" name="autor" placeholder="Autor" required><br>
            <input   type="date" name="datacreation" required><br>
            <input   type="date" name="datasubida" required><br>
            <button style="margin-top: 10px;" type="submit">Puja</button>
        </form>
    </body>
</html>
