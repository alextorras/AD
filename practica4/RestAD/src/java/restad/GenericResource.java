/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import basedatos.callsSQL;
import com.sun.faces.action.RequestMapping;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Image;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 * REST Web Service
 *
 * @author Dani
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    private callsSQL db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    private String usuario_sesion;
    private final String path = "D:\\Documentos\\NetBeansProjects\\git\\AD\\practica4\\RestAD\\web\\imagenes";
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
            redireccio = error("4");
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
    
    
    /**POST method to register new User
     * @param user
     * @param password
     * @return
     */
    @Path("registrarUsuario")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String registerUser(@FormParam("newUser") String user, @FormParam("newPassword") String password) {
        String u = user;
        String p = password;
        boolean existeix = false;
        boolean registrat = false;
        String retorn = null;
        
        try {
            existeix = db.existeix(u);
            if(!existeix) {
                registrat = db.newUser(u, p);
            }
            else {
                retorn = error("11");
            }
            
        } catch (SQLException e) {
            retorn = error("1");
        } finally {
            try {
                db.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(registrat) {
            retorn = red_reg_be();
        }
        return retorn;        
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormDataParam("id") String id, @FormDataParam("title") String title,
        @FormDataParam("description") String description,
        @FormDataParam("keywords") String keywords,
        @FormDataParam("author") String author,
        @FormDataParam("creation") String crea_date) {
        
        try {
            db.updateImage(title, description, keywords, author, crea_date, Integer.parseInt(id));
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return red_modImg_be();
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage (@FormDataParam("id") String id) {
        
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
        String retorno = "<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>JSP Page</title>\n" +
"    </head>\n" +
"    <body>    \n" +
"        <div>\n" +
"            <CENTER>\n" +
"            <h1 class=\"alert alert-primary\">Listado imagenes</h1>\n" +
"            </CENTER>\n" +
"            <input type=\"BUTTON\" style=\"float: right\" value=\"Menú\" class=\"btn btn-info\" onclick=\"history.go(-1)\">";
        try {
            
            Iterator<Image> it = db.listarImagenes().iterator();
            InputStream filecontent = null;
            ByteArrayOutputStream buffer = null;
            byte[] envio = new byte[1024];
            File f = null;
            while(it.hasNext()) {
                Image imagen = (Image) it.next();
                String nom_aux = imagen.getFilename();
                f = new File(path + File.separator + nom_aux);
                filecontent = new FileInputStream(f);
                int read = 0;
                byte[] aux = new byte[1024];
                buffer = new ByteArrayOutputStream();
                while ((read = filecontent.read(aux)) != -1) {
                    buffer.write(aux, 0, read);
                }
                filecontent.close();
                envio = buffer.toByteArray();
                byte[] encodedBase64 = Base64.getEncoder().encode(envio);
                String encoded = new String(encodedBase64, "UTF-8");
                encoded = "data:image/png;base64," + encoded;
                retorno += "<div>          \n" +
"        </div>\n" +
"        <div>\n" +
"            <ul>\n" +
"            <img id=\"imatge reg\" src=\"" + encoded + "\" width=\"200\" height=\"200\">" +
"            <li>Titol:" + imagen.getTitol() + " </li>\n" +
"            <li>Data creacio:" +  imagen.getDatac()+ " </li>\n" +
"            <li>Descripcio:" + imagen.getDescripcio() + "</li>\n" +
"            <li>Autor: " + imagen.getAutor() + "</li>\n" +
"            <li>Keywords:" + imagen.getKeywords() + "</li>\n" +
"            <li>Id: " + imagen.getId() + "</li>\n" +
"            \n" +
"          </ul>";
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

     /**
     * POST method to search images by keyword
     * @param titol
     * @param descripcio
     * @param keywords
     * @param autor
     * @param datac
     * @param datas
     * @param filename
     * 
     * @return
     */
    @Path("MultiSearch")
    @GET
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String MultiSearch(@QueryParam("titol") String titol, @QueryParam("descripcio") String descripcio, @QueryParam("keywords") String keywords, @QueryParam("autor") String autor, @QueryParam("datacreation") String datac, @QueryParam("dataSubida") String datas, @QueryParam("filename") String filename) {
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;        
        try {
            resultados = db.buscarImagen(titol, descripcio, keywords, autor, datac, datas, filename);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<title> Resultat </title>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la busqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menu</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                a += "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
                
                
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
    }

    private String PrintImageData(Image im) {
        String a
                = "<th scope=\"row\" value=\"" + im.getId() + "\"> " + im.getId() + " </th>\n"
                + "<td name=\"titol\" value=\"" + im.getTitol() + "\">" + im.getTitol() + "</td>\n"
                + "<td name=\"descripcio\" value=\"" + im.getDescripcio() + "\"> " + im.getDescripcio() + " </td>\n"
                + "<td name=\"keywords\" value=\"" + im.getKeywords() + "\">" + im.getKeywords() + "</td>\n"
                + "<td name=\"autor\" value=\"" + im.getAutor() + "\">" + im.getAutor() + "</td>\n"
                + "<td name=\"datac\" value=\"" + im.getDatac() + "\"> " + im.getDatac() + "</td>\n"
                + "<td name=\"datas\" value=\"" + im.getDatas() + "\"> " + im.getDatas() + "</td>\n"
                + "<td name=\"nom\" value=\"" + im.getFilename() + "\"> " + im.getFilename() + "</td>\n";
        return a;
    }

   /**
 * GET method to search images by id
 * @param id
 * @return
 */
 @Path("searchID/{id}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByID(@PathParam("id")int id){
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;
        String b = null;
        String c = null;
        try {
            resultados = db.buscarImagenporId(id);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<title> Resultat </title>\n"
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la búsqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                
                    for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                
                c = "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
              
                a.concat(c);
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
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
   db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;
        String b = null;
        String c = null;
        try {
            resultados = db.buscarImagenporTitulo(title);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<title> Resultat </title>\n"
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la búsqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                
                    for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                
                c = "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
              
                a.concat(c);
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
    }
    
    /**
    * GET method to search images by creation date
    * @param creaDate
    * @return
    */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate (@PathParam("date") String datac) {
   db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;
        String b = null;
        String c = null;
        try {
            resultados = db.buscarImagenporTitulo(datac);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<title> Resultat </title>\n"
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la búsqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                
                    for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                
                c = "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
              
                a.concat(c);
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
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
   db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;
        String b = null;
        String c = null;
        try {
            resultados = db.buscarImagenporAutor(author);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<title> Resultat </title>\n"
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la búsqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                
                    for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                
                c = "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
              
                a.concat(c);
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
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
    db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        List<Image> resultados = null;
        String a = null;
        String b = null;
        String c = null;
        try {
            resultados = db.buscarImagenporKeywords(keywords);
            if (resultados != null) {
                a = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" 
                        + "<title> Resultat </title>\n"
                        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<H1>Resultado de la búsqueda</H1></br>\n"
                        + "<td><a style=\"float: right\" class=\"btn btn-primary btn-lg active\" onClick=\"history.go(-2);\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>\n"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th scope=\"col\">id</th>\n"
                        + "<th scope=\"col\">title</th>\n"
                        + "<th scope=\"col\">description</th>\n"
                        + "<th scope=\"col\">keywords</th>\n"
                        + "<th scope=\"col\">author</th>\n"
                        + "<th scope=\"col\">creation_date</th>\n"
                        + "<th scope=\"col\">storage_date</th>\n"
                        + "<th scope=\"col\">filename</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n";
                
                    for (Image i : resultados) {
                    a += "<tr>\n"
                            + PrintImageData(i)
                            + "</tr>\n";
                }
                
                c = "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>\n";
              
                a.concat(c);
            } else {
                a = error("3");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);

        }
        return a;
    }
    
    private String red_login_be() {
        String a = "<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>Login correcto</title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <CENTER>\n" +
"        <h1 class=\"alert alert-primary\">Login correcto</h1>\n"
                + "<input type=\"button\" value=\"Pagina anterior\" onClick=\"history.go(-1);\">" +        
"    </CENTER>\n" +
"    </body>\n" +
"</html>\n";
        return a;
    }
    
    private String red_modImg_be() {
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
"        <h1 class=\"alert alert-success\">La imagen se ha modificado correctamente</h1>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=\"history.go(-2);\">\n" +
"        <br>\n" +
"        <br>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
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
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-2)>\n" +
"        <br>\n" +
"        <br>\n" +
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
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-2)>\n" +
"        <br>\n" +
"        <br>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
    }
    
    private String red_reg_be() {
        String a = 
                "<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" +
"        <title>JSP Page</title>\n" +
"    </head>\n" +
"    <body>\n" +
"    <CENTER>\n" +
"        <h1 class=\"alert alert-primary\">Usuario Registrado</h1>\n" +
"        <br>\n" +
"        <text class=\"alert alert-success\">El usuario se ha registrado con exito</text>\n" +
"        <br>\n" +
"        <br>\n" +
"        <input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-2)>\n" +
"    </CENTER>\n" +
"    </body>\n" +
"</html>";
        return a;
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
                boton = "<a href=\"history.go(-2) class=\"btn btn-primary\">Back</a>";
                break;
            case 4:
                mensaje = "<p class=\"card-text\">Usuario o contraseña incorrectos</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 5:
                mensaje = "<p class=\"card-text\">Error en el IO</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 6:
                mensaje = "<p class=\"card-text\">No se ha encontrado el fichero</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 7:
                mensaje = "<p class=\"card-text\">Error del Servlet</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 8:
                mensaje = "<p class=\"card-text\">ID de la imagen NULL</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 9:
                mensaje = "<p class=\"card-text\">No se ha eliminado la base de datos.</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 10:
                mensaje = "<p class=\"card-text\">Ha fallado el registro de Imagen</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 11:
                mensaje = "<p class=\"card-text\">El usuario ya existe</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            case 12:
                mensaje = "<p class=\"card-text\">Formato de la imagen incorrecto</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
            case 13:
                mensaje = "<p class=\"card-text\">Parametros nulos</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
                break;
            default:
                mensaje = "<p class=\"card-text\">Error incalsificable</p>";
                boton = "<input type=\"BUTTON\" value=\"Volver al menú\" class=\"btn btn-primary\" onclick=history.go(-1)>\n";
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
                "</div>\n" +
"            </CENTER>\n" +
"         </div>\n" +
                boton +
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
