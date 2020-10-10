
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Àlex
 */
@WebServlet(name="registrarImagen", urlPatterns = {"/registrarImagen.jsp"})
public class registrarImagen {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
       
            /* TODO output your page here. You may use following sample code. */
        try {
            final Part filePart = request.getPart("imatge");
            String nom = getName(filePart);
            int punt = nom.lastIndexOf('.');
            String extensio = nom.substring(punt);
            if(!extensio.equals("jpg")) {
                throw new Exception("Extension");
            }
            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datacreation");
            String datas = request.getParameter("datasubida");
            Connection cn = null;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement getid = cn.prepareStatement("SELECT MAX(id) FROM imatges");
            int id;
            ResultSet rs = getid.executeQuery();
            if(rs.next()) id = rs.getInt(1)+1;
            else id=0;
            PreparedStatement uploader = cn.prepareStatement("insert into image (id, title,description, keywords, author, creation_date, storage_date, filename)"
                    + " VALUES(" + id + ',' + titol + ',' + descripcio + ',' + keywords + ',' + autor + ',' + datac + ',' + datas + ',' + nom + ")");
            
            uploader.executeQuery();
        }
        
        
        
        catch (Exception e) {
            if(e.equals("Extension")) {
                System.out.println("La imatge no te extensio JPEG");
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
            throws ServletException, IOException, Exception {
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
            throws ServletException, IOException, Exception {
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

