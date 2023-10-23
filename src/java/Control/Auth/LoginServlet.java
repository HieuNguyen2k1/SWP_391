package Control.Auth;

import Dao.AuthDao;
import Model.Doctor;
import Model.User;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("show_login", 1);
        if (request.getSession().getAttribute("login_mess") != null) {
            String login_mess = (String) request.getSession().getAttribute("login_mess");
            request.setAttribute("login_mess", login_mess);
            request.getSession().removeAttribute("login_mess");
        }
//        Boolean.parseBoolean(request.getAttribute("from_filter").) == true ? request.setAttribute("login_mess", "Vui lòng đăng nhập!") :
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int choice = Integer.parseInt(request.getParameter("select"));
        if (choice == 0) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            AuthDao dao = new AuthDao();
            User user = dao.checkLoginUser(email, password);

            if (user != null) {
                if (user.is_verified) {
                    request.getSession().setAttribute("acc", user);
                    request.getSession().setAttribute("login", true);
                    if (user.is_admin()) {
                        request.getSession().setAttribute("admin", true);
                    }
//                    response.sendRedirect("home");
                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("login_mess", "Tài khoản của bạn chưa được xác thực!");
                    request.setAttribute("show_login", 1);
                    request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("login_mess", "Sai email hoặc mật khẩu!");
                request.setAttribute("show_login", 1);
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            }
        } else if (choice == 1) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            AuthDao dao = new AuthDao();
            Doctor doctor = dao.checkLoginDoctor(email, password);
            if (doctor != null) {
                if (doctor.isStatus() == true) {
                    request.getSession().setAttribute("doctor", doctor);
                    request.getSession().setAttribute("login", true);
                    //                    response.sendRedirect("home");
                    response.sendRedirect("index.jsp");
                }else{
                     request.setAttribute("login_mess", "Tài khoản đã bị vô hiệu hóa!");
                request.setAttribute("show_login", 1);
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("login_mess", "Sai email hoặc mật khẩu!");
                request.setAttribute("show_login", 1);
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            }
        }
    }
}
