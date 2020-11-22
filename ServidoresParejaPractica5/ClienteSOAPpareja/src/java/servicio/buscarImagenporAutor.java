/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import pr3.Pr3_Service;

/**
 *
 * @author tuxis
 */
@WebServlet(name = "buscarImagenporAutor", urlPatterns = {"/buscarImagenporAutor"})
public class buscarImagenporAutor extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/pr3/pr3.wsdl")
    private Pr3_Service service_1;

    
     private HttpSession lasesion;
     String user_aux;

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
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
          
            /* TODO output your page here. You may use following sample code. */
            lasesion = request.getSession();
            user_aux = (String) lasesion.getAttribute("user");

            if(user_aux.equals(null)) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }

            //database = new callsSQL("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            List<imagen> resultados = null;
            //resultados = null;
            String autor = request.getParameter("autor");
            
            
            resultados = searchbyAuthor(autor);
            //out.println("<td><a href=\"menu.jsp\" style=\"float: right\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>");
            if (resultados != null)
            {
            sendResponse(resultados,out);
            }
            else{
                lasesion.setAttribute("codigo", "3");
                response.sendRedirect(request.getContextPath()+ "/error.jsp");
            }
            
       
        } catch (IOException e) {
            lasesion.setAttribute("codigo", "6");
            try {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            } catch (IOException ex) {
                out.println("<html>No se ha redireccionado correctamente</html>");                
            }
        
        
        finally
        {
            
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
    private void PrintImageData(imagen im, PrintWriter out) {
        lasesion.setAttribute("idImage", im.getId());
        lasesion.setAttribute("name", im.getFilename());
        out.println("<th scope=\"row\" value=\"" + im.getId() + "\"> " + im.getId() + " </th>");
        out.println("<td name=\"titol\" value=\"" + im.getTitol() + "\">" + im.getTitol() + "</td>");
        out.println("<td name=\"descripcio\" value=\"" + im.getDescripcio() + "\"> " + im.getDescripcio() + " </td>");
        out.println("<td name=\"keywords\" value=\"" + im.getKeywords() + "\">" + im.getKeywords() + "</td>");
        out.println("<td name=\"autor\" value=\"" + im.getAutor() + "\">" + im.getAutor() + "</td>");
        out.println("<td name=\"datac\" value=\"" + im.getDatac() + "\"> " + im.getDatac() + "</td>");
        out.println("<td name=\"datas\" value=\"" + im.getDatas() + "\"> " + im.getDatas() + "</td>");
        out.println("<td name=\"nom\" value=\"" + im.getFilename() +"\"> " + im.getFilename() +"</td>");
        out.println("<form action=\"modificarImagen.jsp\" method=\"POST\">");
         
                            
        out.println("<input type=\"hidden\" name=\"titol\" value =\""+im.getTitol()+"\">");
        out.println("<input type=\"hidden\" name=\"descripcio\" value =\""+im.getDescripcio()+"\">");
        out.println("<input type=\"hidden\" name=\"datac\" value =\""+im.getDatac()+"\">");
        out.println("<input type=\"hidden\" name=\"keywords\" value =\""+im.getKeywords()+"\">");
        out.println("<input type=\"hidden\" name=\"autor\" value =\""+im.getAutor()+"\">");
        out.println("<input type=\"hidden\" name=\"id\" value =\""+im.getId()+"\">");
        out.println("<input type=\"hidden\" name=\"filename\" value =\""+im.getFilename()+"\">");
                           
                
                      
        
        if(im.getAutor().equals(user_aux)) {
        out.println("<td><button type=\"submit\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Modificar Imagen</a>\n" + "</td>");
        }
        else {
          out.println("<td><a class=\"btn btn-primary btn-lg disabled\" role=\"button\" aria-pressed=\"true\">Modificar Imagen</a>\n" + "</td>");
        }                  
        out.println("</form>");        
        out.println("<form action=\"eliminarImagen.jsp\" method=\"POST\">"); 
                            
        out.println("<input type=\"hidden\" name=\"titol\" value =\""+im.getTitol()+"\">");
        out.println("<input type=\"hidden\" name=\"descripcio\" value =\""+im.getDescripcio()+"\">");
        out.println("<input type=\"hidden\" name=\"datac\" value =\""+im.getDatac()+"\">");
        out.println("<input type=\"hidden\" name=\"keywords\" value =\""+im.getKeywords()+"\">");
        out.println("<input type=\"hidden\" name=\"autor\" value =\""+im.getAutor()+"\">");
        out.println("<input type=\"hidden\" name=\"id\" value =\""+im.getId()+"\">");
        out.println("<input type=\"hidden\" name=\"filename\" value =\""+im.getFilename()+"\">");
        
        if(im.getAutor().equals(user_aux))
        {
            out.println("<td><button type=\"submit\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Eliminar Imagen</a>\n" + "</td>");
        }
        else 
        {

          out.println("<td><a class=\"btn btn-primary btn-lg disabled\" role=\"button\" aria-pressed=\"true\">Eliminar Imagen</a>\n" + "</td>");
        }      
        out.println("</form>");
        }

    
    private void sendResponse(List<imagen> resultados,PrintWriter out)
    {    
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            out.println("<title> Resultat </title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<H1>Resultado de la búsqueda</H1></br>");
            out.println("<td><a href=\"menu.jsp\" style=\"float: right\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\">Menú</a>\n" + "</td>");
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
            out.println("<th scope=\"col\">Eliminar</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (imagen i : resultados) {
                out.println("<tr>");
                PrintImageData(i,out);
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
    }

    private java.util.List<pr3.Imagen> searchbyAuthor(java.lang.String autor) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        pr3.Pr3 port = service_1.getPr3Port();
        return port.searchbyAuthor(autor);
    }

   

}
