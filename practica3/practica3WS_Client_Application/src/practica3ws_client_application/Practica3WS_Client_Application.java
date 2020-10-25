/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3ws_client_application;

import java.util.Scanner;
import servicio.Image;
import basedatos.callsSQL2;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Practica3WS_Client_Application 
{
    
    static Image i = null;
    static boolean sortir = false;
    static String usuari_sessio;
    static callsSQL2 db = null;
    //final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);   
        boolean a = login();
        boolean primer_cop = true;
        System.out.println("Bienvenido usuari@");
        System.out.println("Que desea hacer?");
        System.out.println("1 - Registrar imagen \n"
                + "2 - Listar imagen \n"
                + "3 - Buscar imagen \n"
                + "4 - Eliminar imagen\n"
                + "5 - Salir");
        String num = null;
        while(!sortir) {
            num = sc.nextLine();
            if (num.equals("1")) {
                registrarImagen();
                primer_cop = false;
            }
            else if (num.equals("2")) {
                listarImagen();
                primer_cop = false;
            }
            else if (num.equals("3")) {
                buscarImagen();
                primer_cop = false;
            }
            else if (num.equals("4")) {
                eliminarImagen();
                primer_cop = false;
            }
            else if (num.equals("5")) {
                
                primer_cop = false;
                sortir = true;
            }
            
            else {
                System.out.println("Introduzca un valor correcto");
            }
            if(!primer_cop) System.out.println(
                    "Que desea hacer? \n"
                    + "1 - Registrar imagen \n"
                    + "2 - Listar imagen \n"
                    + "3 - Buscar imagen \n"
                    + "4 - Eliminar imagen\n"
                    + "5 - Salir");
        }
    }
    
    public static void registrarImagen() {
        System.out.println("Has escogido registrar imagen");
        System.out.println("Que imagen quieres registrar? (Por favor introducir todo el path)");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        i = new Image();
        System.out.println("Añade título a la imagen");
        String titol = sc.nextLine();
        System.out.println("Añade una descripción a la imagen");
        String descripcio = sc.nextLine();
        System.out.println("Añade palabras clave");
        String keywords = sc.nextLine();
        System.out.println("Añade la fecha");
        String datac = sc.nextLine();
        
        int posicio = nombre_fichero.lastIndexOf("\\");
        String nom = nombre_fichero.substring(posicio + 1);
        
        File f = new File(nombre_fichero);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read = 0;
        try {
            InputStream in = new FileInputStream(f);
            byte[] aux = new byte[1024];
            while((read = in.read(aux)) != -1) {
                buffer.write(aux, 0, read);
            }
            byte[] envio = buffer.toByteArray();
            i.setAutor(usuari_sessio);
            i.setDatac(datac);
            i.setDescripcio(descripcio);
            i.setFilename(nom);
            i.setKeywords(keywords);
            i.setTitol(titol); 
            i.setContenido(envio);
            registerImage(i);
        } catch (FileNotFoundException e) {
            System.out.println("La causa del error es: " + e.getCause());
        } catch (IOException e) {
            System.out.println("La causa del error es: " + e.getCause());
        }

        System.out.println("Quieres salir de la session? \n"
                + "1 - Si \n"
                + "2 - No");
        boolean entrat = false;
        String s = null;
        while(!entrat) {
            s = sc.nextLine();
            if(s.equals("1")) {
                sortir = true;
                entrat = true;
            }
            else if(s.equals("2")){
                sortir = false;
                entrat = true;
            }
            if(!s.equals("1") || !s.equals("2")) {
                System.out.println("Introduzca valor correcto");
            }
        }
            
    }
    
    public static void listarImagen() {
        
    }
    
    public static void buscarImagen() {
     System.out.println("Has escogido buscar Imagen");
        System.out.println("Ve introduciendo campo por campo los campos por los que quieres buscar, si no quiere introducir algún campo simplemente pulse enter");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        List<Image> resultados = null;
        System.out.println("¿Que título tiene tu imagen?");
        String titol = sc.nextLine();
        System.out.println("¿Que descripción tiene tu imagen ?");
        String descripcio = sc.nextLine();
        System.out.println("Que palabra clave tiene tu imagen");
        String keywords = sc.nextLine();
         System.out.println("Que autor tiene tu imagen");
        String autor = sc.nextLine();
         System.out.println("Que data de creación tiene tu imagen ");
        String datacreation = sc.nextLine();
         System.out.println("Que data de subida tiene tu imagen");
        String datasubida = sc.nextLine();
         System.out.println("Que nombre de archivo tiene tu imagen");
        String filename = sc.nextLine();
        resultados = (List<Image>)(Object)multiSearch(titol,descripcio,keywords,autor,datacreation,datasubida,filename);
         System.out.println("ID  Título  Descripción Keywords Autor Datacreation DataSubida filename "); 
        for (Image i : resultados) {
                
                PrintImageData(i);
                
            }
        
       

        System.out.println("Quieres salir de la session? \n"
                + "1 - Si \n"
                + "2 - No");
        boolean entrat = false;
        String s = null;
        while(!entrat) {
            s = sc.nextLine();
            if(s.equals("1")) {
                sortir = true;
                entrat = true;
            }
            else if(s.equals("2")){
                sortir = false;
                entrat = true;
            }
            if(!s.equals("1") || !s.equals("2")) {
                System.out.println("Introduzca valor correcto");
            }
        }
            
       
        
    }
    private static void PrintImageData(Image i)
    {
        System.out.println(i.getId()+"\t"+i.getTitol()+"\t"+i.getDescripcio()+"\t"+i.getKeywords()+"\t"+i.getAutor()+"\t"+i.getDatac()+"\t"+i.getDatas()+"\t"+i.getFilename());
    }
    
    public static void eliminarImagen() {
        System.out.println("Has eliegido eliminar imagen");
        System.out.println("Que imagen quieres eliminar?(Introduce su ID)");
        //PRIMERO LE LISTAMOS TODAS LAS IMAGENES QUE TENGA DISPONIBLES PARA QUE PUEDA ELEGIR LA QUE QUIERA
        int num = 0;
        Scanner sc = new Scanner(System.in);
        num = Integer.parseInt(sc.nextLine());
        i = new Image();
        i.setId(num);
        int resultat = deleteImage(i);
        if(resultat == 1) System.out.println("La imagen se ha eliminado con éxito");
        else System.out.println("La imagen no se ha podido eliminar");
        System.out.println("Quieres salir de la session? \n"
                + "1 - Si \n"
                + "2 - No");
        boolean entrat = false;
        String s = null;
        while(!entrat) {
            s = sc.nextLine();
            if(s.equals("1")) {
                sortir = true;
                entrat = true;
            }
            else if(s.equals("2")){
                sortir = false;
                entrat = true;
            }
            if(!s.equals("1") || !s.equals("2")) {
                System.out.println("Introduzca valor correcto");
            }
        }
        
    }


    
    private static boolean login() {
        
        boolean existeix = false;
        
        try {
            System.out.println("Llego aqui");
            db = new callsSQL2("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el usuario");
            String user = sc.nextLine();
            System.out.println("Introduce la contraseña");
            String password = sc.nextLine();
            existeix = db.login(user, password);
            if(existeix) usuari_sessio = user;
            return existeix;
        } catch(ClassNotFoundException e) {
            System.out.println("La causa del error es: " + e.getCause());
        } catch(SQLException e) {
            System.out.println("La causa del error es: " + e.getCause());
        } /*finally {
            try {
                db.cerrarConexion();
            } catch(SQLException e) {
                System.out.println("La causa del error es: " + e.getCause());
            }
        } */
        return existeix;     
    }

    private static int registerImage(servicio.Image image) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.registerImage(image);
    }

    private static int deleteImage(servicio.Image image) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.deleteImage(image);
    }

    private static java.util.List<java.lang.Object> multiSearch(java.lang.String titulo, java.lang.String description, java.lang.String keywords, java.lang.String autor, java.lang.String datacreation, java.lang.String datasubida, java.lang.String filename) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.multiSearch(titulo, description, keywords, autor, datacreation, datasubida, filename);
    }
    
    
    
    
    
    
}
