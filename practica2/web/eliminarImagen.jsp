<%-- 
    Document   : eliminarImagen
    Created on : 03-oct-2020, 11:33:43
    Author     : Dani
--%>

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
        <h1>Eliminar Imagen</h1>
    <CENTER>
        <text class="alert alert-primary">Estas seguro que quieres eliminar la imagen <%=session.getAttribute("name")%>?</text>
        <form action="eliminarImagen" method="POST" enctype="multipart/form-data">
        <br>
            <input type="hidden" name="id" value="<%=session.getAttribute("idImage")%>">
            <input type="submit" name="boto_si" class="btn btn-primary" value="Si" onclick="window.location.href='eliminarImagen'">
            <input type="button" name="boto_no" value="No" class="btn btn-secondary" onclick="window.location.href='listImg.jsp'">
        </form>
    </CENTER>
            
    </body>
</html>
