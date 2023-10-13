/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.PatientDao;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReviewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int rate = Integer.parseInt(request.getParameter("rate"));
        String cmt = request.getParameter("cmt");
        int doctorid = Integer.parseInt(request.getParameter("iddoctor"));
        int patient_id = ((User) request.getSession().getAttribute("acc")).getId();
        PatientDao patientdao = new PatientDao();
        patientdao.insertReview(cmt, rate, doctorid, patient_id);
        request.setAttribute("Đã gửi bình luận", "message");
        response.sendRedirect(request.getContextPath() + "/patient/view-appointments");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
