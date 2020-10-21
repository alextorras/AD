<%-- 
    Document   : registrarImagen
    Created on : 21-oct-2020, 17:16:19
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="basedatos.callsSQL2"%>
<!DOCTYPE html>
<%
    String user = null;
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    } else user = (String) session.getAttribute("user");
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
    <CENTER>
        <h1 class="alert alert-primary">Registrar Imagen</h1>
        <form action="registrarImagen" method="POST" enctype="multipart/form-data">      
            <input style="margin-top: 10px;" type="file" id="imatge" name="imatge" required autofocus> <br>
            <input style="margin-top: 10px;"type="text" name="titol" placeholder="Titulo" required><br>
            <input style="margin-top: 10px;" type="text" name="descripcio" placeholder="Descripción" required><br>
            <input style="margin-top: 10px;" type="text" name="keywords" placeholder="Keywords" required><br>
            <input style="margin-top: 10px;" type="text" name="autor" value=${user} required readonly="readonly"><br>
            <input style="margin-top: 10px;" type="text" name="datacreation" placeholder="aaaa/mm/dd" required><br>
            <button style="margin-top: 10px;" class="btn btn-primary" type="submit">Submit</button>
            <input style="margin-top: 10px;" type="BUTTON" value="Menú" class="btn btn-secondary" onclick="window.location.href='menu.jsp'">
        </form>        
    </CENTER>
    </body>
</html>
