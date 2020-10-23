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
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //TODO write your implementation code here:
        return null;
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
        System.out.println("Aqui tambien entro");
        
        boolean salt = false;
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        System.out.println("Conectado");
        final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";
        //System.out.println("El path es: " + path);
        
        FileInputStream ins = null;
        FileOutputStream ous = null;
        String nom = image.getFilename();
        int posicio = nom.lastIndexOf("\\");
        String fich = nom.substring(posicio + 1);
        System.out.println(fich);
        try {
            File fi = new File(nom);
            File fo = new File(path + File.separator + fich);
            
            ins = new FileInputStream(fi);
            ous = new FileOutputStream(fo);
            
            byte[] buffer = new byte[1024];
            
            int longitut;
            while ((longitut = ins.read(buffer)) > 0) {
                ous.write(buffer, 0, longitut);
            }
            ins.close();
            ous.close();         
          
            /*String nom = image.getFilename();
            System.out.println(nom);
            OutputStream escritura = null;
            escritura = new FileOutputStream(new File(path + File.separator + nom));
            InputStream filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while((read = filecontent.read(bytes)) != -1) {
                escritura.write(bytes, 0, read);
            }*/
            image.setId(db.getID());           
            boolean comprobacio = db.newImage(image.getId(), image.getTitol(), image.getDescripcio(), image.getKeywords(), image.getAutor(), image.getDatac(), image.getFilename());
            if(!comprobacio) {
                File f = new File(path + File.separator + nom);
                f.delete();
                return 0;
            }           
        } catch (FileNotFoundException ex) {
            System.out.println("La causa és: " + ex.getCause());
            //Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
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
        //return 1;
    }
}
