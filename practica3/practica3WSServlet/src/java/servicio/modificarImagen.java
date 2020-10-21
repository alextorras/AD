/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Àlex
 */
@WebServlet(name="modificarImagen", urlPatterns = {"/modificarImagen"})
@MultipartConfig
public class modificarImagen extends HttpServlet{

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/practica3Server/WS.wsdl")
    private WS_Service service;
    Image imagen = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        final String path = ("C:\\Users\\admin\\Desktop\\Dani\\UPC\\AD\\practiques\\AD\\practica2\\web\\imagenes\\");
       
            /* TODO output your page here. You may use following sample code. */
            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datac");
            Integer id = Integer.parseInt(request.getParameter("id"));
            
            imagen.setTitol(titol);
            imagen.setDescripcio(descripcio);
            imagen.setKeywords(keywords);
            imagen.setAutor(autor);
            imagen.setDatac(datac);
            imagen.setId(id);
            
            modifyImage(imagen);
                    
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

    private int modifyImage(servicio.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        servicio.WS port = service.getWSPort();
        return port.modifyImage(image);
    }
}
