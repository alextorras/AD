<%-- 
    Document   : buscarImagen
    Created on : 03-oct-2020, 20:00:12
    Author     : Alejandro Capella
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
        <form action="buscarImagen.jsp" method="GET">
  <div class="form-group">
    <label for="titol">Títol</label>
    <input type="text" class="form-control" class ="form-control" id="titol" name="titol"  placeholder="Introdueix el títol">
    
  </div>
           </div><div class="container p-5">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card-header bg-dark text-white text-center">
                        <h3>Cerca Imatge</h3>
                    </div>
                    
                    <div class ="card-body" >          
  <div class="form-group">
    <label for="descripcio">Descripció</label>
    <input type="text" class="form-control" class ="form-control" id="exampleInputPassword1" name="descripcio" placeholder="Descripció">
  </div>
  <div class="form-group">
    <label for="Keywords">Keywords</label>
    <input type="text" class="form-control"class ="form-control" id="Keywords" name="keywords" placeholder="Keywords">
  </div>
  <div class="form-group">
    <label for="Autor">Autor</label>
    <input type="text" class="form-control" class ="form-control" id="Autor" name="autor" placeholder="Autor">
  </div>          
  <div class="form-group">
    <label for="DataCreation">Data de creació</label>
    <input type="date" class="form-control" class ="form-control" id="datacreacio" name="datacreation" placeholder="Data Creacío">
  </div>  
 <div class="form-group">
    <label for="DataSubida">Data de upload</label>
    <input type="date" class="form-control" class ="form-control" id="datasubida" name="dataSubida" placeholder="Data Upload">
  </div>   
 <div class="form-group">
    <label for="filename">Nom de l'arxiu</label>
    <input type="text" class="form-control" class ="form-control" id="filename" name="filename" placeholder="Nom Arxiu">
  </div>  
  <button type="submit" class="btn btn-primary">Submit</button>
            </div>
</form>
</div>
    </body>
</html>
