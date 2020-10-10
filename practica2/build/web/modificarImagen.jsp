<%-- 
    Document   : modificarImagen
    Created on : 04-oct-2020, 17:00:33
    Author     : Àlex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

        String user = null;
        if(session.getAttribute("username") == null){
                response.sendRedirect("login.jsp");
        }else user = (String) session.getAttribute("username");        
      
    %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<div>
        <h1>Modificar imagen:</h1>
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
                
                <input type="text" name="titol" placeholder="Títol" value="<%= titol %>" required>
                <input type="text" name="descripcio" placeholder="Descripció" value="<%= descrip %>" required>
                <input type="text" name="keywords" placeholder="keywords" value="<%= tag %>" required>
                <input type="text" name="autor" placeholder="Autor" value="<%= autor %>" required>
                <input type="text" name="datac" value="<%= data %>" required>                
                <input type="hidden" name="id" value="<%= id %>"  >

                <button type="submit">Submit</button>
            </form>
      </div>
    </body>
</html>
