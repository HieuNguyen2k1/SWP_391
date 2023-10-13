package Control.Auth;

import Dao.PatientDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/get-password.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("verify_key");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re-password");
        if (password.equals(re_password)){
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";
            if (password.matches(passwordRegex)){
                if (new PatientDao().getPassword(key, password)){
                    req.setAttribute("success", "Thay đổi mật khẩu thành công.");
                } else {
                    req.setAttribute("error", "Thay đổi mật khẩu không thành công.");
                    new PatientDao().resetKey(key);
                }
            } else {
                req.setAttribute("warning", "Mật khẩu phải có ít nhất 8 kí tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số!");
            }
        } else {
            req.setAttribute("warning", "Mật khẩu không trùng khớp.");
        }
        req.getRequestDispatcher("/WEB-INF/views/auth/get-password.jsp").forward(req, resp);
    }
}
