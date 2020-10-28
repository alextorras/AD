/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import basedatos.callsSQL;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
import org.glassfish.jersey.media.multipart.FormDataParam;


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
    HttpSession s;
    String tit;

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
   

    
    /**POST method to register upload a new image
     * @param uploadInputStream
     * @param filename
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param crea_date
     * @return
     * 
     */
    @POST
    @Path("register")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage(@FormDataParam("file") InputStream uploadInputStream,
            @FormDataParam("filename") String filename, 
            @FormDataParam("title") String title,
            @FormDataParam("description") String description,
            @FormDataParam("keywords") String keywords,
            @FormDataParam("author") String author,
            @FormDataParam("creation") String crea_date) {
        String nom_amb_extensio = filename + ".jpg";
        InputStream in = uploadInputStream;
        File f = new File(path + File.separator + nom_amb_extensio);
        OutputStream ous = null;
        String t = title;
        String ds = description;
        String key = keywords;
        String aut = author;
        String dc = crea_date;
                
        int read = 0;
        byte[] aux = new byte[1024];
        byte[] contingut = new byte[1024];
        ByteArrayOutputStream buffer = null;
        boolean registrat = false;
        try {
            buffer = new ByteArrayOutputStream();
            while ((read = in.read(aux)) != -1) {
                buffer.write(aux, 0, read);
            }
            contingut = buffer.toByteArray();
            ous = new FileOutputStream(f);
            ous.write(contingut);
            ous.close();
            in.close();
            buffer.close();
            int id = db.getID();
            registrat = db.newImage(id,t, ds, key, aut, dc, nom_amb_extensio);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
        
        return red_regImg_be();
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
            redireccio = error("1");
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
        
        int num = Integer.parseInt(id);
        if(num == -1) return "Error";
        boolean eliminat = false;
        try {
            String nom = db.nom_eliminar_imagen(num);
            eliminat = db.eliminar_imagen(num);
            if(eliminat) {
                File f = new File(path + File.separator + nom);
                f.delete();
            } 
            else {
                return "La imagen no se ha eliminado";
            }
        } catch(SQLException e) {
            System.out.println(e.getCause());
        }
        return red_elim_be();
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
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Registrar Imagen\" class=\"btn btn-primary\" onclick=\"window.location.href='" + red() + "/registrarImagen.jsp'\">\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Listar Imagenes\" class=\"btn btn-secondary\" onclick=\"window.location.href='" + red() + "/listImg.jsp'\">\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Buscar Imagen\" class=\"btn btn-dark\" onclick=\"window.location.href='" + red() + "/buscarImagen.jsp'\">\n" +
"        <br>\n" +
"        <br>        \n" +
"        <input type=\"BUTTON\" style=\"margin-top: 10px\" value=\"Logout\" class=\"btn btn-info\" onclick=\"window.location.href='" + red() + "/logout.jsp'\">\n" +
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
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=\"window.location.href='" + red() + "/menu.jsp'\">\n" +
"        <br>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Cerrar la sessión\" class=\"btn btn-secondary\" onclick=\"window.location.href='" + red() + "/logout.jsp'\">\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
        return a;
    }
    
    private String red_elim_be() {
        return "<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>Imagen eliminada</title>\n" +
"    </head>\n" +
"    <body>\n" +
"    <CENTER>\n" +
"        <br>\n" +
"        <h1 class=\"alert alert-success\"> La imagen se ha eliminado correctamente </h1>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=\"window.location.href='menu.jsp'\">\n" +
"        <br>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Cerrar la sesión\" class=\"btn btn-secondary\" onclick=\"window.location.href='logout.jsp'\">\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
    }
    
    private String error(String numero) {
        String mensaje;
        String boton;
        int aux = Integer.parseInt(numero);
        System.out.println(aux);
        
        switch(aux) 
        {
            case 1:
                mensaje = "<p class=\"card-text\">Error de SQL</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 2:
                mensaje = "<p class=\"card-text\">No se ha encontrado la clase</p>" ;
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 3:
                mensaje = "<p class=\"card-text\">No hay resultados</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 4:
                mensaje = "<p class=\"card-text\">Usuario o contraseña incorrectos</p>";
                boton = "<a href=\"" + red() + "/login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 5:
                mensaje = "<p class=\"card-text\">Error en el IO</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 6:
                mensaje = "<p class=\"card-text\">No se ha encontrado el fichero</p>";
                boton = "<a href=\"" + red() +"/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 7:
                mensaje = "<p class=\"card-text\">Error del Servlet</p>";
                boton = "<a href=\"" + red() + "menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 8:
                mensaje = "<p class=\"card-text\">ID de la imagen NULL</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 9:
                mensaje = "<p class=\"card-text\">No se ha eliminado la base de datos.</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 10:
                mensaje = "<p class=\"card-text\">Ha fallado el registro de Imagen</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 11:
                mensaje = "<p class=\"card-text\">El usuario ya existe</p>";
                boton = "<a href=\"" + red() + "/login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            case 12:
                mensaje = "<p class=\"card-text\">Formato de la imagen incorrecto</p>";
                boton = "<a href=\"" + red() + "/menu.jsp\" class=\"btn btn-primary\">Back</a>";
            case 13:
                mensaje = "<p class=\"card-text\">Parametros nulos</p>";
                boton = "<a href=\"" + red() + "/login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
            default:
                mensaje = "<p class=\"card-text\">Error incalsificable</p>";
                boton = "<a href=\"" + red() + "/login.jsp\" class=\"btn btn-primary\">Back</a>";
                break;
        }
        String retorn = 
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>ERROR</title>\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"    </head>\n" +
"    <body>" +
" <CENTER>\n" +
"         <div class=\"card\" style=\"width: 18rem;\">\n" +
"            <CENTER>\n" +
"            <img class=\"card-img-top\" src=\"https://www.wpextremo.com/wp-content/uploads/2019/11/500-internal-server-error-featured-image-1.png\" alt=\"Card image cap\">\n" +
"            <div class=\"card-body\">\n" +
"                <h5 class=\"card-title\">ERROR</h5>" + 
                mensaje + 
                boton + 
                "</div>\n" +
"            </CENTER>\n" +
"         </div>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>\n";
        return retorn;
    }
    
    private String red() {
        String redireccio = context.getBaseUri().toString();
        String curt = redireccio.substring(0, redireccio.length() - 1);
        int tallar = curt.lastIndexOf("/");
        return redireccio = curt.substring(0, tallar);
    }

    
}
