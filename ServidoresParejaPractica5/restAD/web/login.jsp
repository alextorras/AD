<%-- 
    Document   : login
    Created on : 23/09/2020, 15:53:39
    Author     : marcu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color:red; padding-left:50px"><%out.write(request.getParameter("message"));%></h1>
        <div id="container">
            <form action="webresources/generic/login" method="POST" enctype="application/x-www-form-urlencoded">
                <br>
                <label for="name" >Username:</label>
                <input type="username" name="login">
                <br> 
                <br>     
                <label for="password">Password:</label>
                <input type="password" name="password">
                <br>     
                <br>     
                <input type="submit" value="Login" name="login">
            </form>
        </div>
    </body>
</html>



