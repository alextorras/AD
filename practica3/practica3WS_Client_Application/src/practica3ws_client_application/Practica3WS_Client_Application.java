/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3ws_client_application;

import java.util.Scanner;
import servicio.Image;
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
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in); 
        boolean primer_cop = true;
        int op = opcions_entrar();
        boolean comproba = false;
        if(op == 1) {
            comproba = inicia_sessio();
        }
        if(op == 2) {
            registra_usuari();
            comproba = inicia_sessio();
        }
        System.out.println("Que desea hacer?");
        System.out.println("1 - Registrar imagen \n"
                + "2 - Listar imagen \n"
                + "3 - Buscar imagen \n"
                + "4 - Eliminar imagen \n"
                + "6 - buscar imagen por ID \n"
                    + "7 - buscar imagen por titulo \n"
                   + "8 -  buscar imagen por keyword \n"
                    + "9 - buscar imagen por autor \n"
                     + "10 - buscar imagen por fecha creación\n"
                + "5 - Salir de la sessión");
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
            else if (num.equals("6")) {
                buscarImagenporId();
                primer_cop = false;
            }
            else if (num.equals("7")) {
                buscarImagenporTitulo();
                primer_cop = false;
            }
            else if (num.equals("8")) {
                buscarImagenporKeywords();
                primer_cop = false;
            }
            else if (num.equals("9")) {
               buscarImagenporAutor();
                primer_cop = false;
            }
            else if (num.equals("10")) {
               buscarImagenporDatac();
                primer_cop = false;
            }
            
            else if(num.equals("5")) {
                sortir = true;
            }
            else {
                System.out.println("Introduzca un valor correcto");
            }
            if(!primer_cop && !sortir) System.out.println(
                    "Que desea hacer? \n"
                    + "1 - Registrar imagen \n"
                    + "2 - Listar imagen \n"
                    + "3 - Buscar imagen \n"
                    + "4 - Eliminar imagen \n"
                    + "6 - buscar imagen por ID \n"
                    + "7 - buscar imagen por titulo \n"
                   + "8 -  buscar imagen por keyword \n"
                    + "9 - buscar imagen por autor \n"
                     + "10 - buscar imagen por fecha creación\n"
                    + "5 - Salir de la sessión");
        }
        if(sortir) System.out.println("La ejecución del programa ha finalizado");
    }
    
    private static int opcions_entrar() {
        boolean marcat = false;
        Scanner sc = new Scanner(System.in);
        int num = 0;
        while(!marcat) {
            System.out.println("Bienvenido, que desea hacer: \n"
                    + "1 - Iniciar sesión \n"
                    + "2 - Registrarse");
            
            num = Integer.parseInt(sc.nextLine());
            if (num == 1 || num == 2) {
                marcat = true;
            }
        }
        return num;           
    }
    private static boolean inicia_sessio() {
        System.out.println("Introduzca nombre usuario:");
        Scanner sc = new Scanner(System.in);
        String u = sc.nextLine();
        System.out.println("Introduzca contraseña:");
        String passwd = sc.nextLine();
        return iniSession(u, passwd);
    }
    private static void registra_usuari() {
        System.out.println("Introduzca nombre usuario:");
        Scanner sc = new Scanner(System.in);
        String u = sc.nextLine();
        System.out.println("Introduzca contraseña:");
        String passwd = sc.nextLine();
        boolean a = afegeixUser(u, passwd);
        if(a) System.out.println("Usuario registrado con exito");
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
        System.out.println("Has escogido listar imagen");
        List<Image> resultados = (List<Image>)(Object)listImage();
        
        for (Image i : resultados) {        
                PrintImageData(i);
        }
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
    public static void buscarImagenporAutor() {
        System.out.println("Has escogido buscar Imagen por Autor");
        System.out.println("Ve introduciendo el Autor, primero pulse enter");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        List<Image> resultados = null;
       
         System.out.println("Que autor tiene tu imagen");
        String autor = sc.nextLine();
        
        resultados = (List<Image>)(Object)searchbyAuthor(autor);
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
    public static void buscarImagenporId() {
        System.out.println("Has escogido buscar Imagen por Id");
        System.out.println("Ve introduciendo el id");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        Image resultados = null;
        System.out.println("¿Que id tiene su imagen?");
        int id = Integer.parseInt(sc.nextLine());
        
        resultados = searchbyId(id);
              
                PrintImageData(resultados);
        
        
       

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
    public static void buscarImagenporDatac() {
        System.out.println("Has escogido buscar Imagen");
        System.out.println("Ve introducciendo la fecha de creación,primero pulsa enter");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        List<Image> resultados = null;
      
         System.out.println("Que data de creación tiene tu imagen ");
        String datacreation = sc.nextLine();
         
        resultados = (List<Image>)(Object)searchbyCreaDate(datacreation);
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
    public static void buscarImagenporKeywords() {
        System.out.println("Has escogido buscar Imagen por Keywords");
        System.out.println("Ve introduciendo las keywords, pulse enter primro");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        List<Image> resultados = null;
        System.out.println("¿Que keywords tiene su imagen");
        String keywords = sc.nextLine();
    
        resultados = (List<Image>)(Object)searchbyKeyword(keywords);
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
    public static void buscarImagenporTitulo() {
        System.out.println("Has escogido buscar Imagen por titulo");
        System.out.println("Ve introduciendo el titulo,pulsa enter");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        List<Image> resultados = null;
        System.out.println("¿Que título tiene tu imagen?");
        String titol = sc.nextLine();
        
 
        resultados = (List<Image>)(Object)searchbyTitle(titol);
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
        System.out.println("ID: " + i.getId()+"\t Titulo: "+i.getTitol()+"\t Descripcion: "+i.getDescripcio()+"\t Keywords: "+i.getKeywords()+"\t Autor: "
                +i.getAutor()+"\t Fecha de creacion: "+i.getDatac()+"\t Fecha de subida: "+i.getDatas()+"\t Nombre del fichero: "+i.getFilename());
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


    private static java.util.List<java.lang.Object> listImage() {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.listImage();
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

    private static boolean afegeixUser(java.lang.String user, java.lang.String password) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.afegeixUser(user, password);
    }

    private static boolean iniSession(java.lang.String user, java.lang.String password) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.iniSession(user, password);
    }

    private static boolean comprobaUser(java.lang.String user) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.comprobaUser(user);
    }

    private static java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.searchbyAuthor(author);
    }

    private static java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.searchbyCreaDate(creaDate);
    }

    private static java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.searchbyTitle(title);
    }

    private static java.util.List<java.lang.Object> searchbyKeyword(java.lang.String keyword) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.searchbyKeyword(keyword);
    }

    private static Image searchbyId(int id) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.searchbyId(id);
    }

}
