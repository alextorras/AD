package classes;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dani
 */
@WebServlet(name="eliminarImagen", urlPatterns = {"/eliminarImagen"})
@MultipartConfig
public class eliminarImagen extends HttpServlet {
   int id_aux = 0;
    String nom_aux = null;

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
            throws ServletException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            String user_aux = (String) session.getAttribute("user");
            if(user_aux.equals(null)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            out = response.getWriter();
            callsSQL database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            //id_aux = (int) session.getAttribute("idImage");
            String auxiliar = request.getParameter("id");
            System.out.println("La ID que tinc ara es: " + auxiliar);
            id_aux = Integer.parseInt(request.getParameter("id"));
            
            nom_aux = database.nom_eliminar_imagen(id_aux);
            if(id_aux == 0){
                session.setAttribute("codigo", "8");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
            boolean resultat;
            resultat = database.eliminar_imagen(id_aux);
            if(!resultat) {
                session.setAttribute("codigo", "9");
                response.sendRedirect(request.getContextPath() + "/error.jsp");                
            }
            
            File f = new File("C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\netbeans11\\Practica2\\Practica2\\web\\imagenes\\" + nom_aux);
            //File f = new File(request.getContextPath() + "/web/images/" + nom_aux);
            if(f.delete())
            {
                response.sendRedirect(request.getContextPath() + "/opcions.jsp");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/menu.jsp");
            }
            database.cerrarConexion();
            
            
        }catch(SQLException e) {
            try {
                session.setAttribute("codigo", "1");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } catch(ClassNotFoundException e) {
            session.setAttribute("codigo", "2");
            try {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch(IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
            //System.out.println("<html>No s'ha trobat la classe</html>");
        } catch(IOException e) {
            session.setAttribute("codigo", "5");
            try {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");                
            }
                      
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
