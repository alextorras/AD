<%-- 
    Document   : descargarImagen
    Created on : 14-nov-2020, 17:20:52
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
        <h1 class="alert alert-primary"> Descargar Imagen</h1>
        <form action="descargarImagen" method="POST">
            <input type="text" name="localizacion" placeholder="Ubicación descarga" required>
            <span class="popuptext" id="myPopUp" class="badge badge-info">Por ejemplo: C:\Users\admin\Desktop\Dani\UPC\AD\practiques\practica3WSServlet\web\im</span>
            <input type="hidden" name="content" value="<%=request.getParameter("content")%>">
            <input type="hidden" name="filename" value="<%=request.getParameter("filename")%>">
            <br>
            <br>
            <input type="submit" value="Descargar" class="btn btn-primary">
        </form>
    </body>
    

</html>
