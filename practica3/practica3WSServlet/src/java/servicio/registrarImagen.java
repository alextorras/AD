/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;



/**
 *
 * @author admin
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/practica3Server/WS.wsdl")
    private WS_Service service;
     

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        Image i = null;
        HttpSession s = request.getSession();
        final String path = "C:\\Users\\tuxis\\Documents\\ad\\AD\\practica3\\practica3Server\\web\\imagenes";
        
        String usuari = (String) s.getAttribute("user");
        
        try {
            if(usuari.equals(null)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            i = new Image();
            out = response.getWriter();
            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datacreation");
                       
            final Part filePart = request.getPart("file");
            String name = request.getParameter("file");
            System.out.println(name);
            

            System.out.println("El nombre del fichero es: " + filePart);
            String nom = getName(filePart);            
            int punt = nom.lastIndexOf('.');
            String extensio = nom.substring(punt);
            
            if (!extensio.equals(".JPG") || !extensio.equals(".jpg") || !extensio.equals(".png")) {
                out.println("Mal formato");
            }
            
            /*OutputStream escritura = null;
            escritura = new FileOutputStream(new File(path + File.separator + nom));*/
            InputStream filecontent = filePart.getInputStream();
            int read = 0;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] aux = new byte[102400000];
            while((read = filecontent.read(aux)) != -1) {
                buffer.write(aux, 0, read);
            }
            byte[] envio = buffer.toByteArray();
            
            
            
            
            /*while((read = filecontent.read(bytes)) != -1) {
                escritura.write(bytes, 0, read);
            }*/
            
            i.setTitol(titol);
            i.setDescripcio(descripcio);
            i.setKeywords(keywords);
            i.setAutor(autor);
            i.setDatac(datac);
            i.setFilename(nom);
            i.setContenido(envio);
            int auxiliar = registerImage(i);
            if (auxiliar == 1) {
                response.sendRedirect(request.getContextPath() + "/opcions_registrar.jsp");
            }
            else {
                s.setAttribute("codigo", "10");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
            
            
        } catch(IOException e) {
            try {
                s.setAttribute("codigo", "5");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } catch (ServletException e) {
            try {
                s.setAttribute("codigo", "7");
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

    private int registerImage(servicio.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        servicio.WS port = service.getWSPort();
        return port.registerImage(image);
    }

    
    
}
