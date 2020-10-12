<%-- 
    Document   : logout
    Created on : 12-oct-2020, 14:05:02
    Author     : admin
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
        <h1>Logout</h1>
    <CENTER>
        <text>Vols tancar la sessió?</text>
        <br>
        <form action="logout" method="POST">
            <input type="submit" value="Si">
            <br>
            <input type="button" value="No" onclick="window.location.href='menu.jsp'">
        </form>
        <br>
    </CENTER>
    
    </body>
</html>
