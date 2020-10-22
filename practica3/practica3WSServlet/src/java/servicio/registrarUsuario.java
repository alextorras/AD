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

import basedatos.callsSQL2;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author admin
 */
@WebServlet(name = "registrarUsuario", urlPatterns = {"/registrarUsuario"})
public class registrarUsuario extends HttpServlet {

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
        callsSQL2 db = null;
        try {
            out = response.getWriter();
            db = new callsSQL2("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            String aux1 = request.getParameter("newUser");
            String aux2 = request.getParameter("newPassword");
            boolean prova = db.existeix(aux1);
            out.println(prova);
            if(prova) {
                s.setAttribute("codigo", "11");
                response.sendRedirect(request.getContextPath() + "/error.jsp");   
            }
            boolean insertat = db.newUser(aux1, aux2);
            if(insertat) {
                response.sendRedirect(request.getContextPath() + "/registrarExito.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(registrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            //Logger.getLogger(registrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
