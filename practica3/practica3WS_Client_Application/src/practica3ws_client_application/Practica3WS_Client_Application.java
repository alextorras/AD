/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3ws_client_application;

import java.util.Scanner;
import servicio.Image;
import basedatos.callsSQL2;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;

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
    final String path = "C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica3\\practica3Server\\web\\imagenes";
    
    
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
                + "4 - Eliminar imagen");
        while(!sortir) {
            int num = Integer.parseInt(sc.nextLine());
            if (num == 1) {
                registrarImagen();
                primer_cop = false;
            }
            if (num == 2) {
                listarImagen();
                primer_cop = false;
            }
            if (num == 3) {
                buscarImagen();
                primer_cop = false;
            }
            if (num == 4) {
                eliminarImagen();
                primer_cop = false;
            }
            if(!primer_cop) System.out.println("Que desea hacer? \n"
                    + "1 - Registrar imagen \n"
                    + "2 - Listar imagen \n"
                    + "3 - Buscar imagen \n"
                    + "4 - Eliminar imagen");
        }
    }
    
    public static void registrarImagen() {
        System.out.println("Has escogido registrar imagen");
        System.out.println("Que imagen quieres registrar? (Por favor introducir todo el path)");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        //System.out.println(nombre_fichero);
        i = new Image();
        System.out.println("Añade título a la imagen");
        String titol = sc.nextLine();
        System.out.println("Añade una descripción a la imagen");
        String descripcio = sc.nextLine();
        System.out.println("Añade palabras clave");
        String keywords = sc.nextLine();
        System.out.println("Añade la fecha");
        String datac = sc.nextLine();       
        
        i.setAutor(usuari_sessio);
        i.setDatac(datac);
        i.setDescripcio(descripcio);
        i.setFilename(nombre_fichero);
        i.setKeywords(keywords);
        i.setTitol(titol);        
        registerImage(i);
        
        System.out.println("Quieres salir de la session? \n"
                + "1 - Si \n"
                + "2 - No");
        
        int s = Integer.parseInt(sc.nextLine());
        if(s == 1) sortir = true;
        else sortir = false;
    }
    
    public static void listarImagen() {
        
    }
    
    public static void buscarImagen() {
        
    }
    
    public static void eliminarImagen() {
        
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
    
    
    
    
    
    
}
