/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JButton;

/**
 *
 * @author admin
 */
@WebServlet(name="eliminarImagen", urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            callsSQL database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            out.println("<html>Connectat<br></html>");
            response.sendRedirect("opcions.jsp");
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet eliminarImagen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet eliminarImagen at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/ 
           
            
            HttpSession s = request.getSession();
            String user_aux = (String) s.getAttribute("user");
            Integer id_aux = (Integer) s.getAttribute("idImage");
            
            if(id_aux == null) out.println("<html>No existeix tal imatge</html>");
            if(user_aux.equals(null)) out.println("<html>La sessió s'ha tancat</html>");
            //LANZAR EXCEPCIONES PARA ESTOS DOS CASOS
            
            
            String nom_aux = database.nom_eliminar_imagen(id_aux);
            boolean resultat = false;
            if(nom_aux.equals(null)) out.println("<html>La base de dades no existeix</html>");
            else {
                resultat = database.eliminar_imagen(id_aux);
            }
            

            
            
            out.println("<html>Imatge eliminada amb exit</html>");            
            File f = new File("../" + nom_aux);
            if(f.delete())
            {
                response.sendRedirect("/opcions.jsp");
                out.println("<html>La imatge s'ha eliminat correctament</html>");
                out.println("<html>Vols tornar al menu?</html>");   
                //FALTA IMPLEMENTAR BOTÓN PARA VOLVER AL MENÚ PRINCIPAL.
            }
            else {
                response.sendRedirect("/menu.jsp");
            }
            
            database.cerrarConexion();
            
            
        }catch(SQLException e) {
            e.printStackTrace();            
        } catch(ClassNotFoundException e) {
            System.out.println("<html>No s'ha trobat la classe</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
