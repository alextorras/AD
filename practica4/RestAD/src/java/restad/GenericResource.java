/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

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

/**
 * REST Web Service
 *
 * @author admin
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

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
        @FormParam("creation") String crea_date) {
        return null;
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
    
    
}
