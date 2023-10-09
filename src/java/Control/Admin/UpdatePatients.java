/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Admin;

import Dao.Admin.UpdateUser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;

import java.io.IOException;

/**
 *
 * @author FPTSHOP
 */

//@WebServlet(name = "UpdateControl", urlPatterns = {"/update"})
public class UpdatePatients extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        int user = Integer.parseInt(request.getParameter("pid"));
        UpdateUser dao = new UpdateUser();
        User list = dao.getPatientById(user);
        request.setAttribute("x", list);
        request.getRequestDispatcher("/WEB-INF/views/admin/update-patients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        boolean gender = false;
        if(request.getParameter("gender").equals("1")){
            gender = true;
        }
        UpdateUser dao = new UpdateUser();
        dao.updatePatients(id, name, email, password, phone,dob,address,gender);
        response.sendRedirect(request.getContextPath() + "/admin/patients-control");
    }

}
