
package Control;

import Dao.PatientDao;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class PatientChangePass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/patient-change-pass.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User patient = (User) session.getAttribute("acc");
        int id = patient.getId();
        PatientDao patientDao = new PatientDao();
        User pass = patientDao.getPatientById(id);
        String oldpass = req.getParameter("old-pass");
        String newpass = req.getParameter("new-pass");
        String confirm = req.getParameter("confirm-pass");
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$";
        if (!newpass.matches(passwordRegex)) {
            req.setAttribute("message", "Mật khẩu phải có ít nhất 8 kí tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số! Vui lòng nhập lại!");
            req.setAttribute("status", "error");
        } else if (!pass.getPassword().equals(oldpass)) {
            req.setAttribute("message", "Mật khẩu cũ không đúng! Vui lòng nhập lại!");
            req.setAttribute("status", "error");
        } else if (!newpass.equals(confirm)) {
            req.setAttribute("message", "Mật khẩu mới không trùng khớp! Vui lòng nhập lại!");
            req.setAttribute("status", "error");
        } else {
            patientDao.updatePassPatient(id, newpass);
            boolean updateResult = patientDao.updatePassPatient(id, newpass);
            if (updateResult) {
                req.setAttribute("message", "Thay đổi thành công!");
                req.setAttribute("status", "success");
            } else {
                req.setAttribute("message", "Lỗi hệ thống! Mời bạn thử lại!");
                req.setAttribute("status", "error");
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/patient-change-pass.jsp").forward(req, resp);
    }
}
