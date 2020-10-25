<%-- 
    Document   : list
    Created on : 03-oct-2020, 16:50:11
    Author     : Àlex
--%>

<%@page import="java.io.File"%>
<%@page import="org.apache.xml.security.utils.Base64"%>
<%@page import="java.io.OutputStream"%>
<%@page import="servicio.Image"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>



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
            <h1 class="alert alert-primary">Listado imagenes</h1>
            </CENTER>
            <input type="BUTTON" style="float: right" value="Menú" class="btn btn-info" onclick="window.location.href='menu.jsp'">
          <%/*
                int rss = 0;
                String[] res = null;
                Connection cn = null;
                try {
                    servicio.WS_Service service = new servicio.WS_Service();
                    servicio.WS port = service.getWSPort();
                    List<Object> lista = port.listImage();
                    Iterator<Object> it = lista.iterator();
                    while (it.hasNext()){
                        // Inici while
                        Image imagen = (Image) it.next();
                        String content = imagen.getContenido().toString();
                        */%>
        <div>
               <%-- start web service invocation --%><hr/>
            <%
            try {
                String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3WSServlet\\web\\im";
                servicio.WS_Service service = new servicio.WS_Service();
                servicio.WS port = service.getWSPort();
                // TODO process result here
                java.util.List<java.lang.Object> result = port.listImage();
                Iterator<Object> it = result.iterator();
                Image imagen = null;
                byte[] contingut = new byte[102400];
                while(it.hasNext()) {
                    imagen = (Image) it.next();
                    String content = imagen.getContenido().toString();                    
            %>
    <%-- end web service invocation --%><hr/>
 
        <div>
            <ul>
            <img src="data:image/png;base64,<%=content%>" width="200" height="200">
            <li>Títol: <%= imagen.getTitol() %></li>
            <li>Data creació: <%= imagen.getDatac() %></li>
            <li>Descripció: <%= imagen.getDescripcio() %></li>
            <li>Autor: <%= imagen.getAutor() %></li>
            <li>Keywords: <%= imagen.getKeywords() %></li>
            
          </ul>
        <%
                      if (imagen.getAutor().equals(user)){ 
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
                        
                            
        

                        <%
                        }
                     %>
        </div><!-- /.col-sm-4 -->
      </div>
                   
    
       
                     <%
 
                    }
                }
                catch(Exception e) {
                    session.setAttribute("codigo", "1");
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    //System.out.println("Error amb la base de dades");
                    //System.err.println(e.getMessage());
                }         
            %>
            <%-- start web service invocation --%><hr/>
    <%-- end web service invocation --%><hr/>

      </div>
    </body>
</html>
