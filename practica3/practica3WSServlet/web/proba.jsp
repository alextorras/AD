<%-- 
    Document   : proba
    Created on : 24-oct-2020, 23:27:11
    Author     : admin
--%>

<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.nio.file.Files"%>
<%@page import="basedatos.callsSQL2"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="servicio.Image"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    callsSQL2 db = new callsSQL2("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    String user = null;
    if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
    } else user = (String) session.getAttribute("user");
    
    String path = "im\\";
    File dir = new File(path);
    dir.mkdir();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Listar Imagenes</title>
    </head>
    <body>
    <CENTER>
        <h1 class="alert alert-primary">Listar Imagenes </h1>
    </CENTER>
    <input type="BUTTON" style="float: right" value="Menú" class="btn btn-info" onclick="window.location.href='menu.jsp'">
        <%-- start web service invocation --%><hr/>
    <%
    try {
	servicio.WS_Service service = new servicio.WS_Service();
	servicio.WS port = service.getWSPort();
        
	java.util.List<java.lang.Object> result = port.listImage();
        Iterator<Object> it = result.iterator();
        File f = null;
        OutputStream ous = null;
        while(it.hasNext()) {
            byte[] contingut = new byte[1024];
            Image imagen = (Image) it.next();
            System.out.println(imagen.getId());
            contingut = imagen.getContenido();
            f = new File(path + imagen.getFilename());
            out.println(f.getAbsolutePath());
            ous = new FileOutputStream(f);
            ous.write(contingut);
            ous.close();
            
    %>
        <div>
            <ul>
                <img src="<%=f.getAbsolutePath()%>" width="200" height="200">
                <li>Títol: <%= imagen.getTitol() %></li>
                <li>Data creació: <%= imagen.getDatac() %></li>
                <li>Descripció: <%= imagen.getDescripcio() %></li>
                <li>Autor: <%= imagen.getAutor() %></li>
                <li>Keywords: <%= imagen.getKeywords() %></li>
            </ul>
            <% 
                if(imagen.getAutor().equals(user)) {
            %>
            <form action="modificarImagen.jsp" method="POST">               
                <input type="hidden" name="titol" value ="<%=imagen.getTitol()%>">
                <input type="hidden" name="descripcio" value ="<%=imagen.getDescripcio()%>">
                <input type="hidden" name="datac" value ="<%=imagen.getDatac() %>">
                <input type="hidden" name="keywords" value ="<%=imagen.getKeywords()%>">
                <input type="hidden" name="autor" value ="<%=imagen.getAutor() %>">
                <input type="hidden" name="id" value="<%= imagen.getId()%>">
                <input type="hidden" name="filename" value="<%=imagen.getFilename()%>">
                <p>
                    <button type="submit" class="btn btn-primary">Editar</button>
                </p>
            </form>
                <form action="eliminarImagen.jsp" method="POST">
                <input type="hidden" name="titol" value ="<%=imagen.getTitol()%>">
                <input type="hidden" name="descripcio" value ="<%=imagen.getDescripcio()%>">
                <input type="hidden" name="datac" value ="<%=imagen.getDatac() %>">
                <input type="hidden" name="keywords" value ="<%=imagen.getKeywords()%>">
                <input type="hidden" name="autor" value ="<%=imagen.getAutor() %>">
                <input type="hidden" name="id" value="<%= imagen.getId()%>">
                <input type="hidden" name="filename" value="<%=imagen.getFilename()%>">
                <p>
                    <button type="submit" class="btn btn-secondary" onclick="window.location.href='eliminarImagen.jsp'">Eliminar</button>
                </p>  
            </form>
                <%
                    }
                %>
    </div>
    <%
        }
    } catch (FileNotFoundException e) { 
        e.printStackTrace();
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>


</html>
