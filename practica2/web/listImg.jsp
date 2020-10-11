<%-- 
    Document   : list
    Created on : 03-oct-2020, 16:50:11
    Author     : Àlex
--%>

<%@page import="classes.imagenData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="classes.callsSQL"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>



<%
    callsSQL database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        String user = null;
        if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
        }else user = (String) session.getAttribute("user");        
    %>  
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>    
        <div>
          <%
                int rss = 0;
                String[] res = null;
                Connection cn = null;
                try {
                    
                    List<imagenData> lista = database.listarImagenes();
                    Iterator<imagenData> it = lista.iterator();
                    while (it.hasNext()){
                        // Inici while
                        imagenData imagen = it.next();
                        %>
                        <div>
        <div>          
        </div>
        <div>
            <ul>
            <img src="<%="imagenes\\" + imagen.getFilename()%>" width="200" height="200">
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
                                <button type="submit">Editar</button>
                            </p>
                
                        </form> 
                        <% session.setAttribute("idImage",imagen.getId());%>
                        <% session.setAttribute("name", imagen.getFilename()); %>
                        
                            <input type="hidden" name="titol" value ="<%=imagen.getTitol()%>">
                            <input type="hidden" name="descripcio" value ="<%=imagen.getDescripcio()%>">
                            <input type="hidden" name="datac" value ="<%=imagen.getDatac() %>">
                            <input type="hidden" name="keywords" value ="<%=imagen.getKeywords()%>">
                            <input type="hidden" name="autor" value ="<%=imagen.getAutor() %>">
                            <input type="hidden" name="id" value="<%= imagen.getId()%>">
                            <input type="hidden" name="filename" value="<%=imagen.getFilename()%>">
                            <p>
                                <button type="submit" onclick="window.location.href='eliminarImagen.jsp'">Eliminar</button>
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
                    System.out.println("Error amb la base de dades");
                    System.err.println(e.getMessage());
                }
                
                finally {
                    try {
                        if(cn != null)
                        cn.close();
                    }
                    catch(SQLException e) {
                        // connection close failed.
                        System.err.println(e.getMessage());
                    }
                }
      
            %>
        
      </div>
    </body>
</html>
