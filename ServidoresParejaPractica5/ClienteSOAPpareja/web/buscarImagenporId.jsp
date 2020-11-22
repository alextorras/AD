<%-- 
    Document   : buscarImagenporId
    Created on : 14-nov-2020, 21:21:06
    Author     : tuxis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        String user = null;
        if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
        }else user = (String) session.getAttribute("user");        
    %>  
    
        <form action="buscarImagenporId" method="GET">

        <div class="container p-5">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card-header bg-dark text-white text-center">
                        <h3>Buscar Imagen</h3>
                    </div>

                    <div class ="card-body" > 

                        <div class="form-group">
                            <label for="id">Id</label>
                            <input type="text" class="form-control" class ="form-control" id = "id" name="id"  placeholder="Introduce el Id">

                        </div>
                       
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='menu.jsp'">Menú</button>
                    </div>
                </div>
            </div>
        </div>
       </form>
                
    </body>
    </html>
