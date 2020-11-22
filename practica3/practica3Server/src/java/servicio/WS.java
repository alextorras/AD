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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
@WebService(serviceName = "WS")
public class WS {
    
    private callsSQL db = null;
    private int codi_error;

    
    

    /**
     * Modificar una imagen existente
     * @param image
     * @return 
     */
    @WebMethod(operationName = "ModifyImage")
public int ModifyImage(@WebParam(name = "image") Image image) {
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        try {
            db.updateImage(image.getTitol(), image.getDescripcio(), image.getKeywords(), image.getAutor(), image.getDatac(), image.getId());
        } catch (SQLException ex) {
            Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            codi_error = 1;            
            salt = true;
            System.out.println("La causa del error es con la BD y el motivo: " + e.getCause());
        } finally {
            try {
                db.cerrarConexion();       
            } catch(SQLException e) {
                System.out.println(e.getCause());
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
                imagen = it.next();
                String aux_nom = imagen.getFilename();
                
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
                
                imagen.setContenido(envio);
                data.add(imagen);
            }
                
        } catch (FileNotFoundException e) {
            codi_error = 6;
            System.out.println("La causa del error es: " + e.getCause());
        } catch (IOException e) {
            codi_error = 5;
            System.out.println("La causa del error es: " + e.getCause());
        } catch (SQLException e) {
            codi_error = 1;
            System.out.println("La causa del error es: " + e.getCause());
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
      Image resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagenporId(id);
        
         } catch (SQLException e) {
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
   List<Image> resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagenporTitulo(title);
        
         } catch (SQLException e) {
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
    List<Image> resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagenporDatac(creaDate);
        
         } catch (SQLException e) {
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
     List<Image> resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagenporAutor(author);
        
         } catch (SQLException e) {
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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
            codi_error = 6;
            System.out.println("La causa és: " + ex.getCause());
            salt = true;
        } catch (IOException e) {
            codi_error = 5;
            System.out.println("La causa de error es de IO y el motivo: " + e.getCause());
            salt = true;
        } catch (SQLException e) {
            codi_error = 1;
            System.out.println("La causa del error es relativa a la base de datos y el motivo: " + e.getCause());
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
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "iniSession")
    public boolean iniSession(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        boolean entra = true;
        try {
        entra = db.login(user, password);
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
        return entra;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "comprobaUser")
    public boolean comprobaUser(@WebParam(name = "user") String user) {
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        boolean entra = true;
        try {
        entra = db.existeix(user);
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
        return entra;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "afegeixUser")
    public boolean afegeixUser(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        boolean entra = true;
        try {
        entra = db.newUser(user, password);
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
        return entra;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyKeyword")
    public List SearchbyKeyword(@WebParam(name = "keyword") String keyword) {
   List<Image> resultados = null;
        try{
        
        db = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        resultados = db.buscarImagenporKeywords(keyword);
        
         } catch (SQLException e) {
             codi_error = 1;
             System.out.println("La causa del error es: " + e.getCause());
            //e.printStackTrace();
            
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
