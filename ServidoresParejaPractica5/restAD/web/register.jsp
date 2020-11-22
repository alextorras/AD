<%-- 
    Document   : registrarImagen
    Created on : Oct 26, 2020, 10:49:41 AM
    Author     : figaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="webresources/generic/register" method="POST"  enctype="multipart/form-data">
            <br>
            <h1>REGISTRE IMATGE</h1>
            <br><br>
            <label>Imatge: </label>
            <input type="file" name="filecontent">
            <br><br>
            <label>Titol: </label>
            <input type="text" name="title">
            <br><br>
            <label>Descripció: </label>
            <input type="text" name="description">
            <br><br>
            <label>Paraules clau: </label>
            <input type="text" name="keywords">
            <br><br>
            <label>Creation Date </label>
            <input type="text" name="creation">
            <br>     
            <br>     
            <input type="submit" value="Registar Imatge" name="registerResource">
        </form> 
    </body>
</html>
