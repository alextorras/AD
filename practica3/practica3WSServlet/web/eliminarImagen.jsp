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
        <title>Eliminar Imagen</title>
    </head>
    <body>
        <CENTER>
        <h1 class="alter alert-primary">Eliminar Imagen</h1>
        <br>
        <text class="alert alert-warning">Estas seguro que quieres eliminar la imagen <%=request.getParameter("filename")%>?</text>
        <form action="eliminarImagen" method="POST" enctype="multipart/form-data">
        <br>
            <input type="hidden" style="margin-top: 10px" name="id" value="<%=request.getParameter("id")%>">
            <input type="submit" name="boto_si" class="btn btn-primary" value="Si" onclick="window.location.href='eliminarImagen'">
            <input type="button" name="boto_no" value="No" class="btn btn-secondary" onclick="window.location.href='listImg.jsp'">
        </form>
    </CENTER>   
    </body>
</html>
