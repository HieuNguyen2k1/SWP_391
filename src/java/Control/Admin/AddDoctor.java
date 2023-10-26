/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Control.Auth.Mail;
import Dao.DoctorDao;

import Dao.SpecialityDao;
import Model.Doctor;
import Model.Speciality_doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class AddDoctor extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddDoctor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDoctor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String degree = request.getParameter("degree");
        String phone = request.getParameter("phone");
        int experience = Integer.parseInt(request.getParameter("experience"));
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        boolean status = true;
        String[] selectedOptions = request.getParameterValues("myCheckbox");
        DoctorDao dd = new DoctorDao();
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();

        SpecialityDao spDao = new SpecialityDao();
        boolean checkMail = false;
        boolean checkPhone = false;

        try {
            checkMail = dd.emailExist(email);
            checkPhone = dd.phoneExist(phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (!checkMail) {
            if (!checkPhone) {
                // Kiểm tra số điện thoại
                if (phone != null && phone.trim().matches("^0\\d{9}$")) {
                    // Kiểm tra ngày tháng năm sinh
                    LocalDate currentDate = LocalDate.now();
                    LocalDate dobDate = LocalDate.parse(dob);

                    if (dobDate.isAfter(currentDate)) {
                        request.setAttribute("mess", "Ngày tháng năm sinh không hợp lệ!");
                        request.setAttribute("status", "error");
                        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                    } else {
                        int age = currentDate.getYear() - dobDate.getYear();
                        if (age < 1) {
                            request.setAttribute("mess", "Số năm sinh không hợp lệ!");
                            request.setAttribute("status", "error");
                            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                        } else {
                            try {
                                dd.createDoctor(name, email, password, degree, experience, phone, dob, gender, address,status);
                                doctorArrayList = dd.getAllDoctorID();
                                for (Doctor dt : doctorArrayList) {
                                    if (dt.getEmail().equals(email)) {
                                        if (selectedOptions != null) {
                                            for (String option : selectedOptions) {
                                                spDao.createSpecialityDoctor(dt.getId(), Integer.parseInt(option));
                                            }
                                        }
                                        continue;
                                    }

                                }
                                response.sendRedirect("doctor-control");

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                } else {
                    request.setAttribute("mess", "Số tháng năm sinh không hợp lệ!");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Số điện thoại đã tồn tại!");
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Email đã tồn tại!");
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(request, response);
        }

        
    }
}



