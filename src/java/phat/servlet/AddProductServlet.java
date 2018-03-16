/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phat.cart.CartObj;
/**
 *
 * @author zone
 */
public class AddProductServlet extends HttpServlet {
    private static String ErrPage = "ErrPage.html";
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
        String urlRewriting = ErrPage;
        String Min = request.getParameter("Min");
        System.out.println(Min);
        String Max = request.getParameter("Max");
        System.out.println(Max);
             try{
                // 1.den lay gio
                HttpSession session =  request.getSession();
                //2. Lay cai gio 
                CartObj cart = (CartObj)session.getAttribute("CART");
                 
                if(cart == null){
                    cart = new CartObj();
                }
//                3.bo do vao gio
                String title = request.getParameter("MobiName");
                cart.addItemToCart(title);
//                4.Cat nhat scope
                session.setAttribute("CART", cart);
                session.setAttribute("Min", Min);
                session.setAttribute("Max", Max);
//                5.di cho tiep
                urlRewriting ="ProcessServlet"
                        + "?btAction=Search"
                        + "&Min="
                        + Min
                        +"&Max="
                        + Max;
            }finally{
                response.sendRedirect(urlRewriting);
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
