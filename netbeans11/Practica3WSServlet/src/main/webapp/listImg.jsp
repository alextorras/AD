<%-- 
    Document   : proba
    Created on : 24-oct-2020, 23:27:11
    Author     : Àlex
--%>

<%@page import="java.util.Base64"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="servicio.Image"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String user = null;
    if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
    } else user = (String) session.getAttribute("user");
    
    
    
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
        
        if (!it.hasNext()) { %>
        <center>
        <br>
        <text class="alert-warning">No hay imagenes para listar</text>
        </center>
        <%
        }       

        

        byte[] contingut = null;
        
        String aux = System.getProperty("user.dir"); 
        Image imagen = null;
        String actual = null;
        while(it.hasNext()) {
            contingut = new byte[1024];
            imagen = (Image) it.next();
            contingut = imagen.getContenido();

            byte[] encodedBase64 = Base64.getEncoder().encode(contingut);
            String encoded = new String(encodedBase64, "UTF-8");
            String descargar = Base64.getEncoder().encodeToString(contingut);
            encoded = "data:image/png;base64," + encoded;
            
            
    %>
    <div> 
            <ul>
            <table class="table">
                <tr>
                    <th style="width: 200px;">
                <img id="imatge reg" src="<%=encoded%>" width="200" height="200">
                    </th>
                    <th style="text-align: left" style="width: 20px;">
                        Titulo: <text style="font-weight: lighter"><%=imagen.getTitol()%></text>
                        <br>
                        Fecha creación: <text style="font-weight: lighter"><%=imagen.getDatac()%></text>
                        <br>
                        Descripcion: <text style="font-weight: lighter"><%=imagen.getDescripcio()%></text>
                        <br>
                        Autor: <text style="font-weight: lighter"><%=imagen.getAutor()%></text>
                        <br>
                        Keywords: <text style="font-weight: lighter"><%=imagen.getKeywords()%></text>
                    </th>
                    
            </tr>
            </table>
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
                    <button type="submit" class="btn btn-secondary">Eliminar</button>
                </p>  
            </form>
            <form action="descargarImagen.jsp" method="POST">
                <input type="hidden" name="content" value="<%=descargar%>">
                <input type="hidden" name="titol" value ="<%=imagen.getTitol()%>">
                <input type="hidden" name="descripcio" value ="<%=imagen.getDescripcio()%>">
                <input type="hidden" name="datac" value ="<%=imagen.getDatac() %>">
                <input type="hidden" name="keywords" value ="<%=imagen.getKeywords()%>">
                <input type="hidden" name="autor" value ="<%=imagen.getAutor() %>">
                <input type="hidden" name="id" value="<%= imagen.getId()%>">
                <input type="hidden" name="filename" value="<%=imagen.getFilename()%>">
                <input type="submit" value="Descargar" class="btn btn-info">
            </form>
                <%
                    }
                %>
    </div>
    <%
        }
    } catch (Exception e) { 
        e.printStackTrace();
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>


</html>
