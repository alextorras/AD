<%-- 
    Document   : menu
    Created on : 01-oct-2020, 7:45:34
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Menu</h1>
        <a href="login.jsp">Login</a>
        <form>
        <input type="BUTTON" value="Login" onclick="window.location.href='login.jsp'">
        <br>
        <input type="BUTTON" value="Registrar Imagen" onclick="window.location.href='registrarImagen.jsp'">;
        <br>
        <input type="BUTTON" value="Listar Imagenes" onclick="window.location.href='listImg.jsp'">
        <br>
        <input type="BUTTON" value="Buscar Imagen" onclick="window.location.href='buscarImagen.jsp'">;
        </form>
    </body>
</html>
