/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
//import com.sun.jersey.multipart.FormDataParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import pr3.Imagen;
import sun.misc.IOUtils;
//import restAD.multipart.FormDataParam;
/**
 * REST Web Service
 *
 * @author figaro
 */
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    
    
    @Context private HttpServletRequest request;
    
    
    
    
    @Path("username")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String username(){
        if (request.getSession().getAttribute("username") != null) 
            return request.getSession().getAttribute("username").toString();
        return "error";
    }
    
    
    @Path("login")     
    @POST     
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)     
    @Produces(MediaType.TEXT_HTML)     
    public String login (@FormParam("login") String login,
                    @FormParam("password") String password){
        
    
    if(login.isEmpty() || password.isEmpty()){
        return "Error: login or password missing";

    } else {
            if(userExists(login, password)){
                HttpSession session = request.getSession();
                session.setAttribute("username", login);
                session.setAttribute("password", password);
                return "ok";      
            } else {
                return "Error: wrong username and/or password";
            }
        }

    
    }
    
    
            
            
    @Path("register")     
    @POST     
    @Consumes(MediaType.MULTIPART_FORM_DATA)     
    @Produces(MediaType.TEXT_HTML)     
    public String registerImage (@FormDataParam("filecontent") InputStream filecontent,
                    @FormDataParam("title") String title,
                    @FormDataParam("description") String description,
                    @FormDataParam("keywords") String keywords,
                    @FormDataParam("creation") String crea_date
    ) throws FileNotFoundException, IOException{


            
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) return "<h1>you are not logged in</h1>";
            
            if(title.isEmpty() || description.isEmpty() || keywords.isEmpty() || crea_date.isEmpty() )
                return "<h1> one or more fields are empty</h1>";
        
            String storageDate = java.time.LocalDate.now().toString();
            String filename = title;
            
            // InputStream -> encoded byte[]
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            int read = 0;
            final byte[] bytes = new byte[4];
            
            while ((read = filecontent.read(bytes)) != -1) {
                outStream.write(bytes, 0, read);
            }
            byte[] imgInBytes = outStream.toByteArray();
            
            outStream.close();
            filecontent.close();
            
           
            String encoded = Base64.getEncoder().encodeToString(imgInBytes);

            String author = session.getAttribute("username").toString();

            Imagen newImage = new Imagen();
            newImage.setId(getNewImageId());
            newImage.setTitle(title);
            newImage.setDescription(description);
            newImage.setKeywords(keywords);
            newImage.setAuthor(author);
            newImage.setCreationDate(crea_date);
            newImage.setStorageDate(storageDate);
            newImage.setFilename(filename);
            newImage.setEncodedData(encoded);
            newImage.setStringId(Integer.toString(newImage.getId()));

            registerImage_1(newImage);
            return "<h1>Image registered</h1>";
            
    }

    @Path("list")     
    @GET         
    @Produces(MediaType.TEXT_HTML)
    public String listImages (){
        
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) return "you are not logged in";
        String ret = "";
           
        List<Imagen> images = listImages_1();
        for(Imagen img : images) {
            ret += String.valueOf(img.getId());
            ret += " ";
            ret += img.getTitle();
            ret += " ";
            ret += img.getAuthor();
            ret += " ";
            ret += img.getKeywords();
            ret += " ";
            ret += img.getStorageDate();
            ret += " ";
            ret += img.getFilename();
            ret += " ";           
            ret += img.getDescription();
            ret += " ";

        }

            return ret;
        

    } 
    
    
    @Path("modify")     
    @POST     
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)     
    @Produces(MediaType.TEXT_HTML)     
    public String modifyImage (@FormParam("id") String id, 
            @FormParam("title") String title,             
            @FormParam("description") String description,             
            @FormParam("keywords") String keywords,             
            @FormParam("creation") String crea_date){
            
        HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) return "<h1>you are not logged in</h1>";

        if( title.isEmpty() || description.isEmpty() || keywords.isEmpty() || crea_date.isEmpty() )
            return "<h1> one or more fields are empty</h1>";

        String storageDate = java.time.LocalDate.now().toString();
        String filename = title;

        String author = session.getAttribute("username").toString();
        
        Imagen img = searchbyId(Integer.parseInt(id));
        if(!author.equals(img.getAuthor())) {
            return "error: you are not the author";
        }
        
        
        Imagen newImage = new Imagen();
        newImage.setId(Integer.parseInt(id));
        newImage.setTitle(title);
        newImage.setDescription(description);
        newImage.setKeywords(keywords);
        newImage.setAuthor(author);
        newImage.setCreationDate(crea_date);
        newImage.setStorageDate(storageDate);
        newImage.setFilename(filename);
        newImage.setEncodedData("temporaryfield");
        //newImage.setEncodedData(new String(targetArray));
        newImage.setStringId(Integer.toString(newImage.getId()));


        modifyImage(newImage);
        return "ok";
        
    } 
    
            
    
    @Path("delete")     
    @POST     
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)     
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage (@FormParam("id") String id){
        
        HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) return "<h1>you are not logged in</h1>";

        if(id.isEmpty() )
            return "<h1> one or more fields are empty</h1>";
        
        System.out.println("id = " + id);
        String author = session.getAttribute("username").toString();
        
        Imagen img = searchbyId(Integer.parseInt(id));
        if(!author.equals(img.getAuthor())) {
            return "error: you are not the author";
        }
        
        deleteImage(searchbyId(Integer.parseInt(id)));
        return "ok";
    } 
    
    
    @Path("searchby")     
    @POST        
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)     
    @Produces(MediaType.TEXT_HTML)     
    public String searchBy (@FormParam("num") String num, 
                            @FormParam("searchby") String searchby){
            System.out.println(num + " " + searchby);

        switch (Integer.parseInt(num)) {
            case 1:
                return searchByID(Integer.parseInt(searchby));
            case 2:
                return searchByTitle(searchby);
            case 3:
                return searchByCreationDate(searchby);
            case 4:
                return searchByKeywords(searchby);
            case 5:
                return searchByAuthor(searchby);
            default:
                break;
        }
        return "error";
    }     

    
    @Path("searchID/{id}")     
    @GET         
    @Produces(MediaType.TEXT_HTML)     
    public String searchByID (@PathParam("id") int id){
        Imagen img = searchbyId(id);
        System.out.println("login " + request.getSession().getAttribute("login"));

        String ret = "";
        ret += img.getId();
        ret += " ";
        ret += img.getTitle();
        ret += " ";
        ret += img.getDescription();
        ret += " ";
        ret += img.getAuthor();
        ret += " ";
        ret += img.getKeywords();
        ret += " ";
        ret += img.getStorageDate();
        ret += " ";
        ret += img.getFilename();
        ret += " ";
        ret += img.getCreationDate();
        return ret;
    }     

    @Path("searchTitle/{title}")     
    @GET         
    @Produces(MediaType.TEXT_HTML)     
    public String searchByTitle (@PathParam("title")  String title){
        List<Imagen> images = searchbyTitle(title);
        String ret = "";

        for(Imagen img : images){
            ret += img.getId();
            ret += " ";
            ret += img.getTitle();
            ret += " ";
            ret += img.getDescription();
            ret += " ";
            ret += img.getAuthor();
            ret += " ";
            ret += img.getKeywords();
            ret += " ";
            ret += img.getStorageDate();
            ret += " ";
            ret += img.getFilename();
            ret += " ";
            ret += img.getCreationDate();
            ret += " ";
            }

        return ret;
    } 
    
    @Path("searchCreationDate/{date}")     
    @GET         
    @Produces(MediaType.TEXT_HTML)     
    public String searchByCreationDate (@PathParam("date") String date){
        List<Imagen> images = searchbyCreaDate(date);
        String ret = "";
        
        for(Imagen img : images){
            ret += img.getId();
            ret += " ";
            ret += img.getTitle();
            ret += " ";
            ret += img.getDescription();
            ret += " ";
            ret += img.getAuthor();
            ret += " ";
            ret += img.getKeywords();
            ret += " ";
            ret += img.getStorageDate();
            ret += " ";
            ret += img.getFilename();
            ret += " ";
            ret += img.getCreationDate();
            }

        return ret;
    } 
    
    @Path("searchAuthor/{author}")     
    @GET         
    @Produces(MediaType.TEXT_HTML)     
    public String searchByAuthor (@PathParam("author") String author){
        List<Imagen> images = searchbyAuthor(author);
        String ret = "";
       
        for(Imagen img : images){
            ret += img.getId();
            ret += " ";
            ret += img.getTitle();
            ret += " ";
            ret += img.getDescription();
            ret += " ";
            ret += img.getAuthor();
            ret += " ";
            ret += img.getKeywords();
            ret += " ";
            ret += img.getStorageDate();
            ret += " ";
            ret += img.getFilename();
            ret += " ";
            ret += img.getCreationDate();
            }

        return ret;
    }     
    
    @Path("searchKeywords/{keywords}")     
    @GET         
    @Produces(MediaType.TEXT_HTML)     
    public String searchByKeywords (@PathParam("keywords") String keywords){
        List<Imagen> images = searchbyKeywords(keywords);
        String ret = "";
        
        for(Imagen img : images){
            ret += img.getId();
            ret += " ";
            ret += img.getTitle();
            ret += " ";
            ret += img.getDescription();
            ret += " ";
            ret += img.getAuthor();
            ret += " ";
            ret += img.getKeywords();
            ret += " ";
            ret += img.getStorageDate();
            ret += " ";
            ret += img.getFilename();
            ret += " ";
            ret += img.getCreationDate();
            }
        return ret;
    } 

    private static int deleteImage(pr3.Imagen iamgen) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.deleteImage(iamgen);
    }

    private static java.util.List<pr3.Imagen> listImages_1() {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.listImages();
    }

    private static int modifyImage(pr3.Imagen imagen) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.modifyImage(imagen);
    }

   

    private static java.util.List<pr3.Imagen> searchbyAuthor(java.lang.String autor) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.searchbyAuthor(autor);
    }

    private static java.util.List<pr3.Imagen> searchbyCreaDate(java.lang.String date) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.searchbyCreaDate(date);
    }

    private static Imagen searchbyId(int id) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.searchbyId(id);
    }

    private static java.util.List<pr3.Imagen> searchbyKeywords(java.lang.String keywords) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.searchbyKeywords(keywords);
    }

    private static java.util.List<pr3.Imagen> searchbyTitle(java.lang.String titulo) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.searchbyTitle(titulo);
    }

    private static int getNewImageId() {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.getNewImageId();
    }

    private static Boolean registerImage_1(pr3.Imagen img) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.registerImage(img);
    }

    private static boolean userExists(java.lang.String arg0, java.lang.String arg1) {
        pr3.Pr3_Service service = new pr3.Pr3_Service();
        pr3.Pr3 port = service.getPr3Port();
        return port.userExists(arg0, arg1);
    }



}
