package Control;

import Dao.PatientDao;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

public class UserEditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User patient = (User) session.getAttribute("acc");
        req.setAttribute("listinfo", patient);
        req.getRequestDispatcher("/WEB-INF/views/user-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User patient = (User) session.getAttribute("acc");
        int id = patient.getId();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String dob = req.getParameter("DOB");
        boolean gender = false;

        if (req.getParameter("gender").equals("1")) {
            gender = true;
        }

        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        PatientDao patientDao = new PatientDao();

        // Kiểm tra số điện thoại
        if (phone != null && phone.trim().matches("^0\\d{9}$")) {
            // Kiểm tra ngày tháng năm sinh
            LocalDate currentDate = LocalDate.now();
            LocalDate dobDate = LocalDate.parse(dob);

            if (dobDate.isAfter(currentDate)) {
                session.setAttribute("mess", "Ngày tháng năm sinh không hợp lệ!");
                session.setAttribute("status", "error");
                resp.sendRedirect(req.getContextPath() + "/patient/profile");
                return;
            }
        } else {
            session.setAttribute("mess", "Số điện thoại không hợp lệ!");
            session.setAttribute("status", "error");
            resp.sendRedirect(req.getContextPath() + "/patient/profile");
            return; 
        }

        if (patientDao.updateUser(id, name, email, password, dob, gender, address, phone)) {
            User user = patientDao.getPatientById(id);
            session.setAttribute("acc", user);
            session.setAttribute("mess", "Thay đổi thông tin thành công!");
            session.setAttribute("status", "success");
            resp.sendRedirect(req.getContextPath() + "/patient/profile");
        }
    }

}
