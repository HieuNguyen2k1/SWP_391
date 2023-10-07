package Control.Auth;

import Dao.PatientDao;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");
        System.out.println("name : " + name);
        String confirmpass = request.getParameter("confirm");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String address = request.getParameter("address");
        String verify_key = UUID.randomUUID().toString();
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";

        if (!pass.matches(passwordRegex)) {
            request.setAttribute("mess", "Mật khẩu phải có 8-50 kí tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số!");
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else if (!pass.equals(confirmpass)) {
            request.setAttribute("mess", "Mật khẩu không trùng khớp!");
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } else {
            PatientDao patientDao = new PatientDao();
            boolean checkMail = false;
            boolean checkPhone = false;

            try {
                checkMail = patientDao.emailExist(email);
                checkPhone = patientDao.phoneExist(phone);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (!checkMail) {
                if (!checkPhone) {
                    // Kiểm tra số điện thoại
                    if (phone != null && phone.trim().matches("^0\\d{9}$")) {
                        // Kiểm tra ngày tháng năm sinh
                        LocalDate currentDate = LocalDate.now();
                        LocalDate dobDate = LocalDate.parse(dob);

                        if (dobDate.isAfter(currentDate)) {
                            request.setAttribute("mess", "Ngày tháng năm sinh không hợp lệ!");
                            request.setAttribute("status", "error");
                            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                        } else {
                            int age = currentDate.getYear() - dobDate.getYear();
                            if (age < 1) {
                                request.setAttribute("mess", "Số năm sinh không hợp lệ!");
                                request.setAttribute("status", "error");
                                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                            } else {
                                try {
                                    boolean check = patientDao.createUser(name, email, pass, phone, verify_key, address, dob, gender);
                                    if (check) {
                                        String host = Mail.getHost(request);
                                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                                        executorService.submit(() -> {
                                            try {
                                                Mail.verify_email(email, name, verify_key, host);
                                            } catch (Exception e) {
                                                e.printStackTrace(); // Or log the exception
                                            }
                                        });
                                        executorService.shutdown();
                                        request.setAttribute("mess", "Đăng kí thành công, vui lòng xác nhận tài khoản qua Email của bạn!");
                                        request.setAttribute("status", "success");
                                        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                                    } else {
                                        request.setAttribute("mess", "Đã có lỗi xảy ra, Vui lòng liên hệ với chúng tôi qua Messenger để được hỗ trợ!");
                                        request.setAttribute("status", "error");
                                        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                                    }
                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    } else {
                        request.setAttribute("mess", "Số điện thoại không hợp lệ!");
                        request.setAttribute("status", "error");
                        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Số điện thoại đã tồn tại!");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Email đã tồn tại!");
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            }
        }
    }
}
