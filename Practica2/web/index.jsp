<%-- 
    Document   : index
    Created on : 30-sep-2020, 10:39:48
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
        <h1>Hello World!</h1>
        <a href="login.jsp">Redireccionar</a>
        <form action="login" method="POST">
            <label>Usuario:</label>
            <input type="text" name="usuario">
            <br>
            
            <label>Contraseña: </label>
            <input type="passowrd" name="password">
            <input type="submit" value="Enviar">
            <%/*<input type="file" name="formulario">*/%>
        </form>
    </body>
</html>
