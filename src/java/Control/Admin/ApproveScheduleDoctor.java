/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Control.Table;
import Dao.DoctorScheduleDao;
import Model.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class ApproveScheduleDoctor extends HttpServlet {

    /**
     * Processes requestuests for both HTTP <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param requestuest servlet requestuest
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest requestuest, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApproveScheduleDoctor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveScheduleDoctor at " + requestuest.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param requestuest servlet requestuest
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("doctor_id"));
         String method = request.getParameter("_method") ;
        HttpSession session = request.getSession(false);
         int iddt = (int) request.getSession().getAttribute("id_dt");
        String name = request.getParameter("name");
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        try {
            if (name == null) {
                doctorScheduleDao.ApproveSchedule(id);
                response.sendRedirect(request.getContextPath() + "/admin/doctor-schedule-control?_method=choose_doctor&doctor_id=" + iddt);

                //_method=choose_doctor&doctor_id=15
            } else {
                doctorScheduleDao.YetSchedule(id);
                response.sendRedirect(request.getContextPath() + "/admin/doctor-schedule-control?_method=choose_doctor&doctor_id=" + iddt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param requestuest servlet requestuest
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method") ;
          HttpSession session = request.getSession(false);
         int iddt = (int) request.getSession().getAttribute("id_dt");
        String inputValue = request.getParameter("listcellids");
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        List<String> myList = Arrays.asList(inputValue.split(","));
        List<Integer> intList = new ArrayList<>();
        System.out.println("Danh sách số nguyên: " + myList);
        for (String str : myList) {
            try {
                int intValue = Integer.parseInt(str);
                intList.add(intValue);
                System.out.println("Danh sách số nguyên: " + intList);
            } catch (NumberFormatException e) {
                System.out.println("Chuỗi không hợp lệ: " + str);
            }
        }

        System.out.println("Danh sách số nguyên: " + intList);
        for (int id : intList) {
            try {
                doctorScheduleDao.ApproveSchedule(id);
            } catch (NumberFormatException e) {
                System.out.println("e");
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/doctor-schedule-control?_method="+method+"&doctor_id="+iddt);
//admin/doctor-schedule-control?_method=choose_doctor&doctor_id=15
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
