<%-- 
    Document   : modificarImagen
    Created on : 04-oct-2020, 17:00:33
    Author     : Àlex
--%>


<%@page import="java.util.Base64"%>
<%@page import="servicio.Image"%>
<%@page import="java.util.Iterator"%>
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
    <input type="BUTTON" style="float: right" value="Menú" class="btn btn-info" onclick="window.location.href='menu.jsp'">
      </div>
       <%
           servicio.WS_Service service = new servicio.WS_Service();
	servicio.WS port = service.getWSPort();
        
	java.util.List<java.lang.Object> result = port.listImage();
        Iterator<Object> it = result.iterator();
        boolean trobat = false;
        Image imagen = new Image();
        String idaux = request.getParameter("id");
        while(it.hasNext() && !trobat){
            imagen = (Image) it.next();
            int id = imagen.getId();
            if(id == Integer.parseInt(idaux)){
                trobat = true;
            }
        }
        
        byte[] contingut = null;
        contingut = new byte[1024];
        contingut = imagen.getContenido();

        byte[] encodedBase64 = Base64.getEncoder().encode(contingut);
        String encoded = new String(encodedBase64, "UTF-8");
        encoded = "data:image/png;base64," + encoded;
        String titol = request.getParameter("titol");
        String data = request.getParameter("datac");
        String tag =  request.getParameter("keywords");
        String descrip =  request.getParameter("descripcio");
        String autor = request.getParameter("autor");
        String id = request.getParameter("id");
        String filename = request.getParameter("filename");
        

    %>
    <CENTER>
      <div>
          <img id="imatge reg" src="<%=encoded%>" width="200" height="200">
            <form action="modificarImagen" method="POST" >
                Titulo:<input type="text" style="margin-top: 10px" name="titol" placeholder="Títol" value="<%= titol %>" required> <br>
                Descripcion: <input type="text" name="descripcio" placeholder="Descripció" value="<%= descrip %>" required><br>
                Keywords: <input type="text" name="keywords" placeholder="keywords" value="<%= tag %>" required><br>
                Autor: <input type="text" name="autor" placeholder="Autor" value="<%= autor %>" required readonly="readonly"><br>
                Fecha de Creacion: <input type="text" name="datac" value="<%= data %>" required>          <br>      
                <input type="hidden" name="id" value="<%= id %>"  >

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
      </div>
                </CENTER>
    </body>
</html>
