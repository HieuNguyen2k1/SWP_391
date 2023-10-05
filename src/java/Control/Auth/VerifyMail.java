package Control.Auth;

import Dao.PatientDao;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class VerifyMail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getRequestURI().split("/")[3];
        PatientDao userDao = new PatientDao();
        User user= userDao.getPatientByKey(key);
        if (user == null){
            request.setAttribute("message", "Key không tồn tại.");
        } else {
            if (user.is_verified){
                request.setAttribute("message", "Tài khoản này đã được xác thực.");
            } else { // chưa xác thực
                if (userDao.activeById(user.id)){
                    request.setAttribute("message", "Xác thực tài khoản thành công");
                    new PatientDao().resetKey(key);
                } else {
                    request.setAttribute("message", "Xác thực tài khoản thất bại, vui lòng liên hệ admin.");

                }
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/auth/confirm-email.jsp").forward(request, response);
    }
}
