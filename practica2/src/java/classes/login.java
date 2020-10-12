package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dani
 */
@WebServlet(name="login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    callsSQL database = null;
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
        HttpSession s = request.getSession();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");        
            out.println("</head>");
            out.println("<body>");

            String user = (String) request.getParameter("usuario");
            String password = (String) request.getParameter("password");
            if(user.equals(null) || password.equals(null)) out.println("<html>Hola</html>");
            boolean comprova = database.login(user, password);

            s.setAttribute("user", user);
                      
            if(!comprova) {
                s.setAttribute("codigo", "4");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/menu.jsp");
            }     
            
        } catch(SQLException e) {
            try {
                s.setAttribute("codigo", "1");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } catch(ClassNotFoundException e) {
            try {
                s.setAttribute("codigo", "2");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
        } catch(IOException e) {
            try {
                s.setAttribute("codigo", "5");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");
            }
            
        }
        
        finally {
            try {
                database.cerrarConexion();
            } catch(SQLException e) {
                s.setAttribute("codigo", "1");
                e.getErrorCode();
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

}
