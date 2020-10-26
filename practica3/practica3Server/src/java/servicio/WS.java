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
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
@WebService(serviceName = "WS")
public class WS {
    
    callsSQL db = null;
    

    /**
     * Modificar una imagen existente
     * @param image
     * @return 
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Borrar una imagen existente
     * @param image
     * @return 
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        boolean salt = false;
        final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";
        try {
            db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            int aux = image.getId();
            String nom_f = db.nom_eliminar_imagen(aux);
            db.eliminar_imagen(aux);
            File f = new File(path + File.separator + nom_f);
            if(!f.delete()) {
                salt = true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            salt = true;
        } finally {
            try {
                db.cerrarConexion();       
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } 
        if(salt) return 0;
        else return 1;        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImage")
    public List ListImage() {
        String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";
        List<Image> lista = null;
        List<Image> data = new ArrayList<Image>();
        try {
            db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            lista = db.listarImagenes();
            Iterator<Image> it = lista.iterator();
            Image imagen = null;
            InputStream filecontent = null;
            ByteArrayOutputStream buffer = null;
            
            byte[] envio = new byte[1024];
            File f = null;
            while (it.hasNext()){
                //System.out.println("\n Iteracion:");
                imagen = it.next();
                System.out.println("Estoy en WS con la imagen con id: " + imagen.getId());
                String aux_nom = imagen.getFilename();
                //System.out.println("Estoy en WS " + aux_nom);
                
                f = new File(path + File.separator + aux_nom);
                filecontent = new FileInputStream(f);
                int read = 0;
                byte[] aux = new byte[1024];
                buffer = new ByteArrayOutputStream();
                while((read = filecontent.read(aux)) != -1) {
                    buffer.write(aux, 0, read);
                }
                
                filecontent.close();
                envio = buffer.toByteArray();
                System.out.println(envio);
                
                imagen.setContenido(envio);
                data.add(imagen);
            }
                
        } catch (FileNotFoundException e) {
            System.out.println("La causa del error es: " + e.getCause());
            //Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            System.out.println("La causa del error es: " + e.getCause());
            //Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("La causa del error es: " + e.getCause());
            //Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    db.cerrarConexion();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        return data;
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage(@WebParam(name = "image") Image image) {
        
        boolean salt = false;
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";

        FileOutputStream ous = null;
        String nom = image.getFilename();
        byte[] contingut = image.getContenido();
              
        File fo = null;
        try {
            //fi = new File(nom);
            fo = new File(path + File.separator + nom);
            ous = new FileOutputStream(fo);
            ous.write(contingut);
            image.setId(db.getID());           
            boolean comprobacio = db.newImage(image.getId(), image.getTitol(), image.getDescripcio(), image.getKeywords(), image.getAutor(), image.getDatac(), image.getFilename());
            if(!comprobacio) {
                File f = new File(path + File.separator + nom);
                f.delete();
                return 0;
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("La causa és: " + ex.getCause());
            salt = true;
        } catch (IOException e) {
            e.printStackTrace();
            salt = true;
        } catch (SQLException e) {
            e.printStackTrace();
            salt = true;
        } finally {
            try {
                db.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(salt) return 0;
        else return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "MultiSearch")
    public List MultiSearch(@WebParam(name = "titulo") String titulo, @WebParam(name = "description") String description, @WebParam(name = "keywords") String keywords, @WebParam(name = "autor") String autor, @WebParam(name = "datacreation") String datacreation, @WebParam(name = "datasubida") String datasubida, @WebParam(name = "filename") String filename) {
        List<Image> resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagen(titulo, description, keywords, autor, datacreation, datasubida, filename);
        
         } catch (SQLException e) {
            e.printStackTrace();
            // lasesion.setAttribute("codigo", "1");
            
        } 
        
        finally
        {
            try {
                db.cerrarConexion();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
           
        }
     return resultados;
    }
}
