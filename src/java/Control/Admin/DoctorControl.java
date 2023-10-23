package Control.Admin;

import Dao.DoctorDao;
import Dao.SpecialityDao;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class DoctorControl extends HttpServlet {

    public String generateUniqueFileName(String originalFileName) {
        // Get the file extension from the original filename
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < originalFileName.length() - 1) {
            extension = originalFileName.substring(dotIndex + 1);
        }

        // Generate a random UUID as the unique part of the filename
        String uniquePart = UUID.randomUUID().toString();

        // Combine the unique part and file extension to create the unique filename
        String uniqueFileName = uniquePart + "." + extension;

        return uniqueFileName;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        DoctorDao doctorDao = new DoctorDao();
        SpecialityDao specialityDao = new SpecialityDao();
        ArrayList<Doctor> doctorArrayList;
        try {
            doctorArrayList = doctorDao.getAllDoctor();
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("doctor_list", doctorArrayList);
        req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
        req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        DoctorDao doctorDao = new DoctorDao();
        SpecialityDao specialityDao = new SpecialityDao();
        ArrayList<Doctor> doctorArrayList;
        try {
            doctorArrayList = doctorDao.getAllDoctor();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (req.getParameter("_method").equals("DELETE")) {
            this.doDelete(req, resp);
        } else {
            boolean gender = false;
            if (req.getParameter("gender").equals("1")) {
                gender = true;
            }
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String degree = req.getParameter("degree");
            int experience = Integer.parseInt(req.getParameter("experience"));
            int speciality_id = Integer.parseInt(req.getParameter("speciality_id"));
            String phone = req.getParameter("phone");
            String dob = req.getParameter("dob");

            String address = req.getParameter("address");
//
//            Part filePart = req.getPart("image");
//            String fileName = getFileName(filePart);
//            assert fileName != null;
//            String newFileName = generateUniqueFileName(fileName);
//            String uploadDir = req.getServletContext().getRealPath("/") + "uploads";
//            Path filePath = Paths.get(uploadDir, newFileName);
//            try ( InputStream fileContent = filePart.getInputStream()) {
//                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
//            }

            // Kiểm tra mật khẩu
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";
            if (!password.matches(passwordRegex)) {
                req.setAttribute("message", "Mật khẩu phải có từ 8 đến 50 ký tự, bao gồm ít nhất một chữ hoa, một chữ thường và một số!");
                req.setAttribute("status", "error");
                req.setAttribute("doctor_list", doctorArrayList);
                req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
                req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
                return;
            }
            if (experience < 3) {
                req.setAttribute("message", "Kinh nghiệm không đủ. Yêu cầu ít nhất 3 năm kinh nghiệm.");
                req.setAttribute("status", "error");
                req.setAttribute("doctor_list", doctorArrayList);
                req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
                req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
                return;
            }
            // Kiểm tra ngày tháng năm sinh
            LocalDate currentDate = LocalDate.now();
            LocalDate dobDate = LocalDate.parse(dob);
            int age = Period.between(dobDate, currentDate).getYears();

            if (age < 27) {
                req.setAttribute("message", "Ngày tháng năm sinh không hợp lệ! Phải đủ 23 tuổi trở lên.");
                req.setAttribute("status", "error");
                req.setAttribute("doctor_list", doctorArrayList);
                req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
                req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
                return;
            }

            // Kiểm tra số điện thoại
            if (phone == null || !phone.trim().matches("^0\\d{9}$")) {
                req.setAttribute("message", "Số điện thoại không hợp lệ!");
                req.setAttribute("status", "error");
                req.setAttribute("doctor_list", doctorArrayList);
                req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
                req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
                return;
            }
            boolean check = doctorDao.createDoctornoImage(name, email, password, degree, experience, speciality_id,phone, dob, gender, address);
//            boolean check = doctorDao.createDoctor(name, email, password, degree, experience, speciality_id, "uploads/" + newFileName, phone, dob, gender, address);
            if (check) {
                resp.sendRedirect("doctor-control");
            } else {
                req.setAttribute("message", "Có lỗi");
                req.setAttribute("status", "error");
                req.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(req, resp);
            }
        }
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DoctorDao doctorDao = new DoctorDao();
//        if (doctorDao.deleteDoctor(Integer.parseInt(req.getParameter("id")))) {
//            resp.sendRedirect("doctor-control");
//        }
//    }
}
