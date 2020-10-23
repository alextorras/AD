/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3ws_client_application;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Practica3WS_Client_Application 
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);    
        System.out.println("Bienvenido usuari@");
        System.out.println("Que desea hacer?");
        System.out.println("1 - Registrar imagen \n"
                + "2 - Listar imagen \n"
                + "3 - Buscar imagen \n"
                + "4 - Eliminar imagen");
        
        int num = Integer.parseInt(sc.nextLine());
        if (num == 1) registrarImagen();
        if (num == 2) listarImagen();
        if (num == 3) buscarImagen();
        if (num == 4) eliminarImagen();        
    }
    
    public static void registrarImagen() {
        System.out.println("Has escogido registrar imagen");
        System.out.println("Que imagen quieres registrar? (Por favor introducir todo el path)");
        Scanner sc = new Scanner(System.in);
        String nombre_fichero = sc.nextLine();
        System.out.println(nombre_fichero);
        
        
        
    }
    
    public static void listarImagen() {
        
    }
    
    public static void buscarImagen() {
        
    }
    
    public static void eliminarImagen() {
        
    }

    private static int registerImage(servicio.Image image) {
        servicio.WS_Service service = new servicio.WS_Service();
        servicio.WS port = service.getWSPort();
        return port.registerImage(image);
    }
    
    
    
    
    
    
}
