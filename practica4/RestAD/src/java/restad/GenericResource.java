/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import basedatos.callsSQL;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    private callsSQL db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    private String usuario_sesion;
    private final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica4\\RestAD\\web\\imagenes";

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of restad.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        return "<html><head/><body><h1>Hello World!</h1></html>";
        //throw new UnsupportedOperationException();

    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @param fileName
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage (@FormParam("title") String title,
        @FormParam("description") String description,
        @FormParam("keywords") String keywords,
        @FormParam("author") String author,
        @FormParam("creation") String crea_date,
        @FormParam("file") String fn) {
        String t = title;
        String ds = description;
        String key = keywords;
        String aut = author;
        String dc = crea_date;
        String nom = fn;
        int id = 0;
        boolean registrat = false;        
        

        try {
            id = db.getID();
            registrat = db.newImage(id, t, ds, key, aut, dc, nom);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(registrat) return red_regImg_be();
        else return "<html>No</html>";       
    }
    
         /**
    * POST method to modify an existing image
    * @param user
    * @param password
    * @return
    */
    @Path("login")
    @POST
    public String login (@FormParam("usuario") String user, @FormParam("password") String password) {
        boolean entra = true;
        String redireccio = null;
        usuario_sesion = user;
        try {
        entra = db.login(user, password);
        if(!entra) {
            redireccio = "No";
        }
        else {
            redireccio = red_login_be();
        }
        } catch(SQLException e) {
            entra = false;
            System.out.println("La causa del error es: " + e.getCause());

        } finally {
            try {
                db.cerrarConexion();
            } catch (SQLException e) {
                System.out.println("No se ha cerrado bien la base de datos. " + e.getCause());
            }
        }      
        return redireccio;        
    }
    
    
    /**
    * POST method to modify an existing image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("id") String id, @FormParam("title") String title,
        @FormParam("description") String description,
        @FormParam("keywords") String keywords,
        @FormParam("author") String author,
        @FormParam("creation") String crea_date) {
        return null;
    }
    
    /**
    * POST method to delete an existing image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage (@FormParam("id") String id) {
        return null;
    }
    
    /**
    * GET method to list images
    * @return
    */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listImages () {
        return null;
    }
    
    /**
    * GET method to search images by id
    * @param id
    * @return
    */
    @Path("searchID/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByID (@PathParam("id") int id) {
        return null;
    }
    
    /**
    * GET method to search images by title
    * @param title
    * @return
    */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle (@PathParam("title") String title) {
        return null;
    }
    
    /**
    * GET method to search images by creation date
    * @param creaDate
    * @return
    */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate (@PathParam("date") String date) {
        return null;
    }
    
    /**
    * GET method to search images by author
    * @param author
    * @return
    */
    @Path("searchAuthor/{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByAuthor (@PathParam("author") String author) {
        return null;
    }
    /**
    * GET method to search images by keyword
    * @param keywords
    * @return
    */
    @Path("searchKeywords/{keywords}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByKeywords (@PathParam("keywords") String keywords) {
        return null;
    }
    
    private String red_login_be() {
        String a = "<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>Menú</title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <CENTER>\n" +
"        <h1 class=\"alert alert-primary\">Menú</h1>\n" +
"        <form>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Registrar Imagen\" class=\"btn btn-primary\" onclick=\"window.location.href='http://localhost:8080/RestAD/registrarImagen.jsp'\">\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Listar Imagenes\" class=\"btn btn-secondary\" onclick=\"window.location.href='listImg.jsp'\">\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Buscar Imagen\" class=\"btn btn-dark\" onclick=\"window.location.href='buscarImagen.jsp'\">\n" +
"        <br>\n" +
"        <br>        \n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Logout\" class=\"btn btn-info\" onclick=\"window.location.href='logout.jsp'\">\n" +
"        <br>\n" +
"        </form>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>\n";
        return a;
    }
    
    private String red_regImg_be() {
        String a = 
"<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>JSP Page</title>\n" +
"    </head>\n" +
"    <body>\n" +
"    <CENTER>\n" +
"        <br>\n" +
"        <h1 class=\"alert alert-success\">La imagen se ha registrado correctamente</h1>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=\"window.location.href='http://localhost:8080/RestAD/menu.jsp'\">\n" +
"        <br>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Cerrar la sessión\" class=\"btn btn-secondary\" onclick=\"window.location.href='http://localhost:8080/RestAD/logout.jsp'\">\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
        return a;
    }
    
}
