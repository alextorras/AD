/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import pr3.Imagen;
import pr3.Pr3_Service;

/**
 *
 * @author tuxis
 */
@WebServlet(name = "eliminarImagen", urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/pr3/pr3.wsdl")
    private Pr3_Service service;
    
    Imagen i = null;

    

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        HttpSession s = request.getSession();
        String usuari = (String) s.getAttribute("user");
        String id = (String) s.getAttribute("valor");
        try 
        {
            if(usuari.equals(null)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            
            out = response.getWriter();
            i = new Imagen();
            //int aux = Integer.parseInt((String) request.getParameter("valor"));
            int aux = Integer.parseInt(id);
            
            if(aux < 0) {
                s.setAttribute("codigo", "8");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
            i.setId(aux);
            int numero = deleteImage(i);
            
            if (numero == 1) {
                response.sendRedirect(request.getContextPath() + "/opcions.jsp");
            }
            else {
                s.setAttribute("codigo", "9");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (IOException e) {
            try {
                s.setAttribute("codigo", "5");
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

    private int deleteImage(pr3.Imagen iamgen) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        pr3.Pr3 port = service.getPr3Port();
        return port.deleteImage(iamgen);
    }

    

}
