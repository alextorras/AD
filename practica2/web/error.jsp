<%-- 
    Document   : proba
    Created on : 12-oct-2020, 16:35:38
    Author     : admin
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>

        <%Integer aux = Integer.parseInt((String) session.getAttribute("codigo"));
        String mensaje = null;
        String boton = null;
        switch(aux) 
        {
            case 1:
                mensaje = "<p class=\"card-text\">Error de SQL</p>" ;
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                //out.println(mensaje);
                break;
            case 2:
                mensaje = "<p class=\"card-text\">No se ha encontrado la clase</p>" ;
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 3:
                mensaje = "<p class=\"card-text\">No hay resultados</p>";
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 4:
                mensaje = "<p class=\"card-text\">Usuario o contraseña incorrectos</p>";
                boton = "<a href=\"login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 5:
                mensaje = "<p class=\"card-text\">Error en el IO</p>";
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 6:
                mensaje = "<p class=\"card-text\">No se ha encontrado el fichero</p>";
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";
            case 7:
                mensaje = "<p class=\"card-text\">Error del Servlet</p>";
                boton = "<a href=\"menu.jsp\" class=\"btn btn-primary\">Back</a>";

            default:
                mensaje = "<p class=\"card-text\">Error inclasificable</p>" ;
                boton = "<a href=\"login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
                
                
                
                
        }
         %>
    <CENTER>
         <div class="card" style="width: 18rem;">
            <CENTER>
            <img class="card-img-top" src="https://www.wpextremo.com/wp-content/uploads/2019/11/500-internal-server-error-featured-image-1.png" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">ERROR</h5>
                <%=mensaje%>
                <%=boton%>
            </div>
            </CENTER>
         </div>
    </CENTER>
    </body>
</html>
