<%-- 
    Document   : proba2
    Created on : 25-oct-2020, 19:58:33
    Author     : admin
--%>

<%@page import="servicio.Image"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
            <%-- start web service invocation --%><hr/>
    <%
    try {
	servicio.WS_Service service = new servicio.WS_Service();
	servicio.WS port = service.getWSPort();
	// TODO process result here
	java.util.List<java.lang.Object> result = port.listImage();
	//out.println("Result = "+result);
        File f = null;
        Iterator<Object> it = result.iterator();
        OutputStream ous = null;
        Image imagen = null;
        byte[] contingut = null;
        while(it.hasNext()) {
            imagen = (Image) it.next();            
            contingut = new byte[1024];
            contingut = imagen.getContenido();
            
        }
        
        
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
