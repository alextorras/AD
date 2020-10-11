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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name="eliminarImagen", urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {
    Integer id_aux = 0;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            callsSQL database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            String url = request.getHeader("referer");
            
            //Si vienes de listar imagen, vete al JSP primero
            if(url.equals("http://localhost:8080/practica2/listImg.jsp")) {
                id_aux = Integer.parseInt(request.getParameter("id"));
                nom_aux = database.nom_eliminar_imagen(id_aux);
                request.setAttribute("nom_foto", nom_aux);
                request.getRequestDispatcher("eliminarImagen.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/eliminarImagen.jsp");
            }
                                    
            out.println("<html>" + id_aux + "</html>");
            if(id_aux == null) out.println("<html><br>No existeix tal imatge <br></html>");
            //LANZAR EXCEPCIÓN PARA ESTE CASO
            
            out.println("<html><br>" + nom_aux + "<br></html>");
            boolean resultat = false;
            if(nom_aux.equals(null)) out.println("<html>La imagen " + nom_aux + " no existeix.</html>");
            else {
                resultat = database.eliminar_imagen(id_aux);
            }
            
            out.println(nom_aux);
            out.println("\n");
            
            out.println("<html>Imatge eliminada amb exit<br></html>");            
            File f = new File("C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica2\\web\\imagenes\\" + nom_aux);
            if(f.delete() == true)
            {
                response.sendRedirect(request.getContextPath() + "/opcions.jsp");
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
