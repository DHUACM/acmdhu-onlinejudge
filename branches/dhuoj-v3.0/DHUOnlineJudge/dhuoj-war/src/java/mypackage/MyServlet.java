/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypackage;

import dhuoj.primeserver.buslogic.ContestFacadeRemote;
import dhuoj.primeserver.buslogic.MessageFacadeRemote;
import dhuoj.primeserver.common.form.*;
import dhuoj.primeserver.buslogic.UserFacadeRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class MyServlet extends HttpServlet {
    @EJB
    private UserFacadeRemote userFacadeBean;
    @EJB
    private MessageFacadeRemote messageFacadeBean;
    @EJB
    private ContestFacadeRemote contestFacadeBean;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            SubmitCodeForm scf = new SubmitCodeForm();
            scf.setContestID(100);
            scf.setLanguage(0);
            scf.setLocalJudgeResult(0);
            scf.setPassword("");
            scf.setProblemID(4);
            scf.setSource("i'm testing with yhu.");
            scf.setUserID("yhu");

            long a = java.util.Calendar.getInstance().getTimeInMillis();
//            int slnID = this.contestFacadeBean.submitCode(scf);
            int size = this.contestFacadeBean.getContests(0, 50000000).size();
            long b = java.util.Calendar.getInstance().getTimeInMillis();


            //* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>EJB Result is " + size + " : " + (b-a) + "ms" + "</h1>");
            //out.println("<h1>EJB Result is " + messageFacadeBean.queryMessageStatus(318) + "</h1>");
            out.println("</body>");
            out.println("</html>");
            //*/
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
