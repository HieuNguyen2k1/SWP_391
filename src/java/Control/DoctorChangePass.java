/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.DoctorDao;
import Model.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class DoctorChangePass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/doctor/change-pass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        int id = doctor.getId();
        DoctorDao doctorDao = new DoctorDao();
        Doctor pass = doctorDao.findById(id);

        String oldpass = req.getParameter("old-pass");
        String newpass = req.getParameter("new-pass");
        String confirm = req.getParameter("confirm-pass");
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";

        if (!newpass.matches(passwordRegex)) {
            req.setAttribute("message", "Mật khẩu mới phải có 8-50 kí tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số!");
            req.setAttribute("status", "error");
        } else if (!pass.getPassword().equals(oldpass)) {
            req.setAttribute("message", "Mật khẩu cũ không đúng!");
            req.setAttribute("status", "error");
        } else if (!newpass.equals(confirm)) {
            req.setAttribute("message", "Mật khẩu không trùng khớp!");
            req.setAttribute("status", "error");
        } else {
            boolean updateResult = doctorDao.updatePassDoctor(id, newpass);
            if (updateResult) {
                req.setAttribute("message", "Thay đổi hoàn tất!");
                req.setAttribute("status", "success");
            } else {
                req.setAttribute("message", "Lỗi hệ thống! Mời bạn thử lại!");
                req.setAttribute("status", "error");
            }
        }

        req.getRequestDispatcher("/WEB-INF/views/doctor/change-pass.jsp").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
