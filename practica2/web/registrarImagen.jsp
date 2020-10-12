<%-- 
    Document   : registrarImagen
    Created on : 03-oct-2020, 11:33:23
    Author     : Àlex
--%>

<%@page import="classes.callsSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String user = null;
    if(session.getAttribute("user") == null){
            response.sendRedirect("login.jsp");
    }else user = (String) session.getAttribute("user");        
    %>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <br>
        <form action="registrarImagen" method="POST" enctype="multipart/form-data">
       
            <input type="file" id="imatge" name="imatge" required autofocus> <br>
            <input type="text" name="titol" placeholder="Títol" required><br>
            <input   type="text" name="descripcio" placeholder="Descripció" required><br>
            <input  type="text" name="keywords" placeholder="Keywords" required><br>
            <input   type="text" name="autor" placeholder="Autor" required><br>
            <input   type="text" name="datacreation" placeholder="aaaa/mm/dd" required><br>
            <button style="margin-top: 10px;" type="submit">Submit</button>
            <input type="BUTTON" value="Menú" onclick="window.location.href='menu.jsp'">
        </form>
    </center>
    </body>
</html>
