<%-- 
    Document   : error.jsp
    Created on : 04-oct-2020, 4:05:30
    Author     : Alejandro 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <%
        String mensaje;
        String boton;
        String localcodigo;
        localcodigo = request.getParameter("codigo");
        switch(localcodigo)
        {
            case "1":
                mensaje = "<p class=\"card-text\">Error de SQL</p>" ;
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case "2":
                mensaje = "<p class=\"card-text\">No se ha encontrado la clase</p>" ;
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case "4":
                mensaje = "<p class=\"card-text\">No hay resultados</p>";
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case "5":
                mensaje = "<p class=\"card-text\">Usuario o contraseña incorrectos</p>";
                boton = "<a href=\"login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            default:
                mensaje = "<p class=\"card-text\">Error inclasificable</p>" ;
                boton = "<a href=\"login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
        }
        
        %>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">ERROR</h5>
                <%= mensaje %>
                <%= boton %>
            </div>
        </div>
    </body>
</html>
