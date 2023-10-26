package Control.Admin;

import Dao.Admin.UpdateUser;
import Dao.AuthDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadPatients extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        UpdateUser dao = new UpdateUser();
        List<User> list = dao.getPatientsList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/views/admin/patient-control.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        boolean gender = true;
        UpdateUser dao1 = new UpdateUser();
        if (request.getParameter("gender").equals("0")) {
            gender = false;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String verify_key = UUID.randomUUID().toString();
        AuthDao dao = new AuthDao();

        // Kiểm tra số điện thoại
        if (phone == null || !phone.trim().matches("^0\\d{9}$")) {
            request.setAttribute("mess", "Số điện thoại không hợp lệ!");
            request.setAttribute("status", "error");
            List<User> list = dao1.getPatientsList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/views/admin/patient-control.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";
        if (!password.matches(passwordRegex)) {
            request.setAttribute("mess", "Mật khẩu phải có từ 8 đến 50 ký tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số!");
            request.setAttribute("status", "error");
            List<User> list = dao1.getPatientsList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/views/admin/patient-control.jsp").forward(request, response);
            return;
        }

        // Kiểm tra ngày tháng năm sinh
        LocalDate currentDate = LocalDate.now();
        LocalDate dobDate = LocalDate.parse(dob);

        if (dobDate.isAfter(currentDate)) {
            request.setAttribute("mess", "Ngày tháng năm sinh không hợp lệ!");
            request.setAttribute("status", "error");
            List<User> list = dao1.getPatientsList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/views/admin/patient-control.jsp").forward(request, response);
            return;
        }

        try {
            dao.createPatients(name, email, password, phone, dob, gender, address, verify_key);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadPatients.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/admin/patients-control");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
