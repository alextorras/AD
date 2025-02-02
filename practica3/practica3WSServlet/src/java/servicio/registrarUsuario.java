/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebServiceRef;



/**
 *
 * @author admin
 */
@WebServlet(name = "registrarUsuario", urlPatterns = {"/registrarUsuario"})
public class registrarUsuario extends HttpServlet {

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
        HttpSession s = request.getSession();
        try {
            out = response.getWriter();
            //db = new callsSQL2("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            String aux1 = request.getParameter("newUser");
            String aux2 = request.getParameter("newPassword");
            boolean prova = comprobaUser(aux1);
            out.println(prova);
            if(prova) {
                s.setAttribute("codigo", "11");
                response.sendRedirect(request.getContextPath() + "/error.jsp");   
            }
            boolean insertat = afegeixUser(aux1, aux2);
            if(insertat) {
                response.sendRedirect(request.getContextPath() + "/registrarExito.jsp");
            }
        } catch (IOException e) {
            try {
                s.setAttribute("codigo", "5");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } /*catch (SQLException e) {
            try {
                s.setAttribute("codigo", "1");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } catch (ClassNotFoundException e) {
            try {
                s.setAttribute("codigo", "2");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
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

    private boolean afegeixUser(java.lang.String user, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        servicio.WS port = service.getWSPort();
        return port.afegeixUser(user, password);
    }

    private boolean comprobaUser(java.lang.String user) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        servicio.WS port = service.getWSPort();
        return port.comprobaUser(user);
    }

}
