<%-- 
    Document   : error
    Created on : 27/09/2020, 20:23:31
    Author     : marcu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <form action="error" method="POST">
            <h1>ERROR</h1>
        </form> 
    </body>
</html>

<%
    session = request.getSession();
    String returnPath = (String) session.getAttribute("errorReturnPath");
    if(returnPath != null){
        out.println("<form action=\""+returnPath+"\">");
        out.println("<button type=\"submit\"> Volver Pagina Anterior </button>");
        out.println("</form>");
    } else  {       
        out.println("<form action=login.jsp>");
        out.println("<button type=\"submit\"> Volver Pagina Anterior </button>");
        out.println("</form>");
    }
%>
