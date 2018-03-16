/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zone
 */
public class ProcessServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String searchServlet = "SearchServlet";
    private final String updateServlet = "UpdateServlet";
    private final String deleteServlet = "DeleteMobieServlet";
    private final String loginServlet = "LoginServlet";
    private final String addServlet = "AddProductServlet";
    private final String insertServlet = "InsertServlet";
    private final String nullServlet = "ProcessCookiesServlet";
    private final String Remove = "RemoveItemCartServlet";
    private final String viewCart = "Cart.jsp";
    
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
        String button = request.getParameter("btAction");
        String url = loginPage;
        PrintWriter out = response.getWriter();
        try {
            if (button == null) {
                url = nullServlet;
            }else if (button.equals("Login")) {
                url = loginServlet;
            }else if (button.equals("Search")) {
                url = searchServlet;
            }else if (button.equals("Delete")) {
                url = deleteServlet;
            }else if (button.equals("Update")) {
                url = updateServlet;
            }else if(button.equals("InsertMobie")){
                url = insertServlet;
            }else if(button.equals("Add Product")){
                url = addServlet;
            }else if(button.equals("Remove")){
                url = Remove;
            }else if(button.equals("View")){
                url = viewCart;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
