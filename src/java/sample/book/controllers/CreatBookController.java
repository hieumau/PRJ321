/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.book.controllers;

import sample.book.daos.BookDAO;
import sample.book.dtos.BookDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saost
 */
public class CreatBookController extends HttpServlet {
    private static final String SUCCESS = ManageBookController.class.getSimpleName();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
//            String bookID = request.getParameter("id");
            BookDAO bookDAO = new BookDAO();
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            int total = 0;
            try {
                total = Integer.parseInt(request.getParameter("total"));
            } catch (Exception e){
                total = 0;
            }
            if (request.getParameter("total").isEmpty()) total = 0;
            String publishYearTemp = request.getParameter("publishYear");
            Date publishYear=new SimpleDateFormat("yyyy-MM-dd").parse(publishYearTemp);
            BookDTO book = new BookDTO(0, name, author, publisher, total, total, publishYear);
            bookDAO.creatBook(book);
            request.setAttribute("SUCCESS_MESSAGE", "Add new book successful");

        } catch (Exception e){
            request.setAttribute("ERROR_MESSAGE", "Opp! Something wrong!");
        } finally {
            request.getRequestDispatcher(url).forward(request,response);
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
