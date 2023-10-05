package Control.Auth;

import Dao.PatientDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        PatientDao patientDao = new PatientDao();
        if (patientDao.checkEmailTel(email, tel)){
            String uuid = UUID.randomUUID().toString();
            if (patientDao.updateKey(uuid, email, tel)){
                String host = Mail.getHost(req);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.submit(() -> {
                    Mail.forgot_password(email, uuid, host);
                });
                executorService.shutdown();
                req.setAttribute("success", "Vui lòng kiểm tra email!");
            }
        } else {
            req.setAttribute("warning", "Email hoặc số điện thoại không đúng!");
        }
        req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
    }
}
