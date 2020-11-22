<%-- 
    Document   : menu
    Created on : 24/09/2020, 16:20:22
    Author     : marcu
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div style="background-color:blue; opacity: 0.5; color:black; font-size:30px; border: 0, 20px, 0, 20px; padding:10px; text-align:right">
            Hola <%out.write(request.getParameter("username"));%>
        </div>
        <h1 style="color:green; padding-left:50px"><%out.write(request.getParameter("message"));%></h1>
        <form action="menu" method="POST">
            <a href="webresources/generic/register">REGISTRAR IMAGEN</a>
            <br><br>
            <a href="webresources/generic/list">LISTA IMAGENES</a>
            <br><br>
            <a href="webresources/generic/searchby">BUSCAR IMAGEN</a>
        </form>
    </body>
</html>
