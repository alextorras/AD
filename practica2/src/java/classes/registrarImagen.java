package classes;

import classes.callsSQL;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Àlex
 */

@WebServlet(name="registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {
    callsSQL database = null;
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            {
        response.setContentType("text/html;charset=UTF-8");
        final String path = ("D:\\Documentos\\NetBeansProjects\\AplicacionesDist\\web\\imagenes");
       
            /* TODO output your page here. You may use following sample code. */
        try (PrintWriter out = response.getWriter()) {

            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datacreation");
            
            final Part filePart = request.getPart("imatge");
            String nom = getName(filePart);
            int punt = nom.lastIndexOf('.');
            String extensio = nom.substring(punt);
            database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            int id = database.getID();
            
            database.newImage(id, titol, descripcio, keywords, autor, datac, nom);
             OutputStream escritura = null;
        try {
            escritura = new FileOutputStream(new File(path + File.separator
                    + nom));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            escritura.write(bytes, 0, read);
            }
        }
        
        catch (Exception e) {
            if(e.equals("Extension")) {
                System.out.println("La imatge no te extensio JPEG");
            } else {
                e.printStackTrace();
            }
        }  
        finally {
            try (PrintWriter send = response.getWriter()) {
                database.cerrarConexion();
                send.println("<!DOCTYPE html>");
                send.println("<html>");
                send.println("<head>");
                send.println("<title>Imagen registrada con exito</title>");        
                send.println("</head>");
                send.println("<body>");
                Thread.sleep(5000);
                response.sendRedirect(request.getContextPath() + "/menu.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       
          /* try {
               response.sendRedirect(request.getContextPath() + "/menu.jsp");
           } catch (IOException ex) {
               Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
           }*/
        
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

    private String getName(Part filePart) {
        final String partHeader = filePart.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : filePart.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}

