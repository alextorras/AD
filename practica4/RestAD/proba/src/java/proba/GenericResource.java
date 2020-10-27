/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import basedatos.callsSQL;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
    callsSQL db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of proba.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    /**
     * POST method to login
     * @param user
     * @param password
     * @return
     */
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String login(@FormParam("usuario") String user, @FormParam("password") String password) {
        
        String usuari = user;
        String passwd = password;
        boolean log = false;
        try {
            log = db.login(usuari, passwd);
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
        
        if(log) return "1";
        else return "0";
    }
    
    
    

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
