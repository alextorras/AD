<%-- 
    Document   : logout
    Created on : 12-oct-2020, 14:05:02
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
    <CENTER>
        <h1 class="alert alert-primary">Logout</h1>
        <br>
        <text class="alert alert-warning" style="margin-top: 10px;">Quieres cerrar la sesión?</text>
        <br>
        <form action="logout" method="POST">
            <br>
            <input type="submit" value="Sí" style="margin-top: 10px;" class="btn btn-primary" onclick="window.location.href='logout'">
            <input type="button" value="No" style="margin-top: 10px;" class="btn btn-secondary" onclick="window.location.href='menu.jsp'">
        </form>
        <br>
    </CENTER>
    
    </body>
</html>
