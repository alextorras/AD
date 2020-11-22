<%-- 
    Document   : eliminarImagen
    Created on : Oct 26, 2020, 10:48:32 AM
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
        <form action="webresources/generic/delete" method="POST"  enctype="application/x-www-form-urlencoded">
            <br>
            <h1>Eliminar Imagen con ID = <%out.println(request.getParameter("id"));%></h1>
            <input type="text" name="id" readonly value="<%out.println(request.getParameter("id"));%>" style="display: none;">
            <input type="submit" value="Delete" name="eliminarImagen">
        </form> 
    </body>
</html>
