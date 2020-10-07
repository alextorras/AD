<%-- 
    Document   : list
    Created on : 03-oct-2020, 16:50:11
    Author     : Àlex
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%
      /*  String user = null;
        if(session.getAttribute("username") == null){
                response.sendRedirect("login.jsp");
        }else user = (String) session.getAttribute("username");  */      
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
                    
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    cn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
                    PreparedStatement getImages = cn.prepareStatement("SELECT * FROM image ORDER BY creation_date DESC");
                    ResultSet rs = getImages.executeQuery();                                                          
                    while (rs.next()){
                        // Inici while
                        %>
                        <div>
        <div>          
        </div>
        <div>
            <ul>
            <li>Títol: <%= rs.getString("title") %></li>
            <li>Data creació: <%= rs.getString("creation_date") %></li>
            <li>Descripció: <%= rs.getString("description") %></li>
            <li>Autor: <%= rs.getString("author") %></li>
            <li>Keywords: <%= rs.getString("keywords") %></li>
            
          </ul>
        <%
                      // if (rs.getString("autor").equals(user)){ 
                       %>
                        <form  action="modificarImagen.jsp" method="POST">
                            <input type="hidden" name="titol" value ="<%=rs.getString("title")%>">
                            <input type="hidden" name="descripcio" value ="<%=rs.getString("description")%>">
                            <input type="hidden" name="datac" value ="<%=rs.getString("creation_date")%>">
                            <input type="hidden" name="keywords" value ="<%=rs.getString("keywords")%>">
                            <input type="hidden" name="autor" value ="<%=rs.getString("author")%>">
                            <input type="hidden" name="id" value="<%= rs.getInt("id")%>">
                            <input type="hidden" name="filename" value="<%= rs.getString("filename")%>">
                            <p>
                                <button type="submit">Editar</button>
                            </p>
                
                        </form> 
                        <form action="eliminarImagen" method="POST">
                            <input type="hidden" name="titol" value ="<%=rs.getString("title")%>">
                            <input type="hidden" name="descripcio" value ="<%=rs.getString("description")%>">
                            <input type="hidden" name="data" value ="<%=rs.getString("creation_date")%>">
                            <input type="hidden" name="tags" value ="<%=rs.getString("keywords")%>">
                            <input type="hidden" name="autor" value ="<%=rs.getString("author")%>">
                            <input type="hidden" name="id" value="<%= rs.getInt("id")%>">
                            <input type="hidden" name="filename" value="<%= rs.getString("filename")%>">
                            <p>
                                <button type="submit" >Eliminar</button>
                            </p>
                
                        </form> 
        

                        <%
                        //}
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
