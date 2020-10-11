/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Àlex
 */
@WebServlet(name="modificarImagen", urlPatterns = {"/modificarImagen"})
@MultipartConfig
public class modificarImagen extends HttpServlet{
    callsSQL database = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            {
        response.setContentType("text/html;charset=UTF-8");
        final String path = ("D:\\Documentos\\NetBeansProjects\\AplicacionesDist\\imagenes");
       
            /* TODO output your page here. You may use following sample code. */
        try (PrintWriter out = response.getWriter()) {

            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datac");
            Integer id = Integer.parseInt(request.getParameter("id"));
            
            database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            
            
            
            database.updateImage(titol, descripcio, keywords, autor, datac, id);
            
            response.sendRedirect(request.getContextPath() + "/menu.jsp");
            
        } catch (IOException ex) {
            Logger.getLogger(modificarImagen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificarImagen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(modificarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                database.cerrarConexion();                
            } catch (SQLException ex) {
                Logger.getLogger(modificarImagen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
    
          @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             {
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            {
            processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
