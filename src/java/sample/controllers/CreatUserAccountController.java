/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import sample.daos.UserDAO;
import sample.dtos.UserDTO;
import sample.dtos.UserErrorDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saost
 */
public class CreatUserAccountController extends HttpServlet {
    private static final String ERROR = "creat_user_account.jsp";
    private static final String SUCCESS = "login.jsp";
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
        String url = ERROR;
        UserErrorDTO userError = new UserErrorDTO();

        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String passwordRepeat = request.getParameter("passwordRepeat");
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String roleID = request.getParameter("roleID");
            boolean check = true;
            UserDAO dao = new UserDAO();

            if (dao.isExitsUserID(id)){
                userError.setUserIDError("Username is exits!");
                check = false;
            }
            if (password.length() < 4){
                userError.setPasswordError("Password length at least 4 character!");
                check = false;
            }

            if (!password.equals(passwordRepeat)){
                userError.setPasswordRepeatError("Password not match!");
                check = false;
            }

            if (check){
                UserDTO user = new UserDTO(id, password,fullName, roleID, gender, phone, address);
                dao.creatUser(user);
                url = SUCCESS;
            } else {
                request.setAttribute("USER_ERROR", userError);
            }


        } catch (Exception e){

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
