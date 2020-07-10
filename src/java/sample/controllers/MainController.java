/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import sample.account.controllers.CreatUserAccountController;
import sample.book.controllers.ViewLibraryController;
import sample.cart.controllers.AddToCartController;
import sample.cart.controllers.CheckOutController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author saost
 */
public class MainController extends HttpServlet {
    private static final String ERROR = "invalid.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String MANAGE_ACCOUNT = "ManageAccountController";
    private static final String UPDATE_ACCOUNT_INFO = "UpdateAccountInfoController";
    private static final String CREAT_USER_ACCOUNT = CreatUserAccountController.class.getSimpleName();
    private static final String ADD_TO_CART = AddToCartController.class.getSimpleName();
    private static final String VIEW_LIBRARY = ViewLibraryController.class.getSimpleName();
    private static final String VIEW_CART = "cart.jsp";
    private static final String CHECK_OUT = CheckOutController.class.getSimpleName();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("btnAction");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("ManageAccount")) {
                url = MANAGE_ACCOUNT;
            } else if (action.equals("Update account info")) {
                url = UPDATE_ACCOUNT_INFO;
            } else if (action.equals("Creat user account")) {
                url = CREAT_USER_ACCOUNT;
            } else if (action.equals("Add to cart")){
                url = ADD_TO_CART;
            } else if (action.equals("View library")){
                url = VIEW_LIBRARY;
            } else if (action.equals("View cart")){
                url = VIEW_CART;
            } else if (action.equals("Check out")){
                url = CHECK_OUT;
            }

        } catch (Exception e) {
            log("ERROR AT MAINCONTROLLER" + e.toString());
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
