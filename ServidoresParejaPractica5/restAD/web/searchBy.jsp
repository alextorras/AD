<%-- 
    Document   : searchBy
    Created on : Oct 28, 2020, 8:48:32 PM
    Author     : figaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="webresources/generic/searchby" method="POST"  enctype="application/x-www-form-urlencoded">
            <br>
            <h1>Buscar imagen</h1>
            <br><br>
            <select name = "num">
                <option value = "1" selected>ID</option>
                <option value = "2">Title</option>
                <option value = "3">Creation Date</option>
                <option value = "4">Keywords</option>
                <option value = "5">Author</option>
            </select>
            
            <label>Search by: </label>
            <input type="text" name="searchby">
            <br>     
            <br>     
            <input type="submit"  name="buscarImagen" value="entregar">
        </form> 
    </body>
</html>


