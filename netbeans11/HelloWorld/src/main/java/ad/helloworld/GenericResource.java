/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.helloworld;

import basedatos.callsSQL;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("generic")
@RequestScoped
public class GenericResource {
    
    private callsSQL db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    private String usuario_sesion;
    private final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\netbeans11\\HelloWorld\\src\\main\\java\\modelo\\im\\";
    HttpSession s;
    String tit;


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of ad.helloworld.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "<html><body><h1>Hello World!</h1></body></html>";
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
    * POST method to modify an existing image
    * @param user
    * @param password
    * @return
    */
    @Path("login")
    @POST
    public String login (@FormParam("usuario") String user, @FormParam("password") String password) {
        return "<html><body><h1>Hello World 2!</h1></body></html>";
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
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-2)>\n" +
"        <br>\n" +
"        <br>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
        return a;
    }
    
    
    
}
