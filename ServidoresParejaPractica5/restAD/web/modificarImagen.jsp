<%-- 
    Document   : modificarImagen
    Created on : 27/09/2020, 20:46:46
    Author     : marcu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    
    
        <form action="webresources/generic/modify" method="POST"  enctype="application/x-www-form-urlencoded">
            <br>
            <h1>MODIFICAR IMAGEN</h1>
            <br><br>
            <input type="text" name="id" value = "<%out.write(request.getParameter("id"));%>" readonly style="display:none">
            <label>Title: </label>
            <input type="title" name="title" value = "<%out.write(request.getParameter("title"));%>">
            <br><br>
            <label>Description: </label>
            <input type="description" name="description" value = "<%out.write(request.getParameter("description"));%>">
            <br><br>
            <label>Keywords: </label>
            <input type="keywords" name="keywords" value = "<%out.write(request.getParameter("keywords"));%>">

            <label>Creation Date </label>
            <input type="text" name="creation" value = "<%out.write(request.getParameter("creation"));%>">
            <br>       
            <br>     
            <input type="submit"  name="modificarImagen" value="entregar">
        </form> 
    </body>
</html>
