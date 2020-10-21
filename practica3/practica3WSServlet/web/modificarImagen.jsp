<%-- 
    Document   : modificarImagen
    Created on : 04-oct-2020, 17:00:33
    Author     : Àlex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

        String user = null;
        if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
        }else user = (String) session.getAttribute("user");        
      
    %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
<div>
    <CENTER>
    <h1 class="alert alert-primary">Modificar imagen</h1>
    </CENTER>
      </div>
       <%
        String titol = request.getParameter("titol");
        String data = request.getParameter("datac");
        String tag =  request.getParameter("keywords");
        String descrip =  request.getParameter("descripcio");
        String autor = request.getParameter("autor");
        String id = request.getParameter("id");
        String filename = request.getParameter("filename");
    %>
      <div>
          <img src="<%="imagenes\\" + request.getParameter("filename")%>" width="200" height="200">
            <form action="modificarImagen" method="POST" >
                <input type="text" style="margin-top: 10px" name="titol" placeholder="Títol" value="<%= titol %>" required>
                <input type="text" name="descripcio" placeholder="Descripció" value="<%= descrip %>" required>
                <input type="text" name="keywords" placeholder="keywords" value="<%= tag %>" required>
                <input type="text" name="autor" placeholder="Autor" value="<%= autor %>" required>
                <input type="text" name="datac" value="<%= data %>" required>                
                <input type="hidden" name="id" value="<%= id %>"  >

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
      </div>
    </body>
</html>
