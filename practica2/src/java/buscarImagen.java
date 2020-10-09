/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pellax
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {
     private HttpSession lasesion;

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
            throws ServletException, IOException  {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            lasesion = request.getSession(false);
       
            if(lasesion == null)
                //no hay sesión
                
            {
             response.sendRedirect("login.jsp");   
            }
            else
            {
            List<imagenData> resultados = new ArrayList<>();
            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String datac = request.getParameter("datacreation");
            String datas = request.getParameter("datasubida");
            ConsultaBase mibase = new ConsultaBase(titol, descripcio, keywords, autor, datac, datas);
            resultados = mibase.getImageData();
            sendResponse(resultados,out);
            }
            } catch (SQLException e) {

            response.sendRedirect("error.jsp?/codigo=1");
        }
        catch (Exception e) {

            response.sendRedirect("error.jsp?/codigo=3");
        }
        finally
        {
            
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    private void PrintImageData(imagenData im, PrintWriter out) {
            
            String user_aux = lasesion.getAttribute("user").toString();
            
            out.println("<th scope=\"row\">" + im.getId() + "</th>");
            out.println("<td>" + im.getTitol() + "</td>");
            out.println("<td>" + im.getDescripcio() + "</td>");
            out.println("<td>" + im.getKeywords() + "</td>");
            out.println("<td>" + im.getAutor() + "</td>");
            out.println("<td>" + im.getDatac() + "</td>");
            out.println("<td>" + im.getDatas() + "</td>");
            out.println("<td>" + im.getFilename() + "</td>");
            if(im.getAutor().equals(user_aux))
            {
                out.println("<td><a href=\"modificarImagen.jsp\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Modificar Imagen</a>\n" +
"</td>");
            }
            else 
            {
              out.println("<td><a href=\"#\" class=\"btn btn-primary btn-lg disabled\" role=\"button\" aria-pressed=\"true\">Modificar Imagen</a>\n" +
"</td>");
            }
            
            

      
            

        }

    
    private void sendResponse(List<imagenData> resultados,PrintWriter out)
    {
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            out.println("<title> Resultat </title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<H1>Resultat de la cerca</H1></br>");
            out.println("<table>");
            out.println("<thead>");

            out.println("<tr>");
            out.println("<th scope=\"col\">id</th>");
            out.println("<th scope=\"col\">title</th>");
            out.println("<th scope=\"col\">description</th>");
            out.println("<th scope=\"col\">keywords</th>");
            out.println("<th scope=\"col\">author</th>");
            out.println("<th scope=\"col\">creation_date</th>");
            out.println("<th scope=\"col\">storage_date</th>");
            out.println("<th scope=\"col\">filename</th>");
            out.println("<th scope=\"col\">Modificar</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (imagenData i : resultados) {
                out.println("<tr>");
                PrintImageData(i,out);
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
    }
}
