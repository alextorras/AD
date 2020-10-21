/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import modelo.Image;
import basedatos.callsSQL;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
@WebService(serviceName = "WS")
public class WS {
    
    callsSQL db = null;
    
    /**
     * Registrar una nueva imagen
     */
    @WebMethod(operationName = "RegistrarImagen")
    public int RegistrarImagen(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        try {
            db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            db.newImage(image.getId(), image.getTitol(), image.getDescripcio(), image.getKeywords(), image.getAutor(), image.getDatac(), image.getFilename());
            return 1;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.cerrarConexion();
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

    }

    /**
     * Modificar una imagen existente
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
         try {
            db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            db.updateImage(image.getTitol(), image.getDescripcio(), image.getKeywords(), image.getAutor(), image.getDatac(), image.getId());
         }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.cerrarConexion();
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    /**
     * Borrar una imagen existente
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:        
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImage")
    public List ListImage() {
        List<Image> lista = null;
         try {
            db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            lista = db.listarImagenes();
         }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.cerrarConexion();
            } catch(SQLException e) {
                e.printStackTrace();
            }
         }
        return lista;
    }
         

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
        return null;
    }
}
