package Control;

import Dao.AuthDao;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AuthDao authDao = new AuthDao();
        User user = authDao.checkLoginUser(email, password);
        if (user == null){
            req.setAttribute("message", "Tên đăng nhập hoặc mật khẩu chưa đúng!");
            req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
        } else {
            if (user.is_admin()){
                req.getSession().setAttribute("admin", true);
                req.getSession().setAttribute("acc", user);
                req.getRequestDispatcher("/WEB-INF/views/admin/control.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Không phải tài khoản Admin!");
                req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
            }
        }

    }
}
