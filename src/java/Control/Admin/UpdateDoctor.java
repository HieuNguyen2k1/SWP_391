package Control.Admin;

import Dao.DoctorDao;
import Dao.SpecialityDao;
import Model.Doctor;
import Model.Speciality;
import Model.Speciality_doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateDoctor extends HttpServlet {
//    public String generateUniqueFileName(String originalFileName) {
//        // Get the file extension from the original filename
//        String extension = "";
//        int dotIndex = originalFileName.lastIndexOf('.');
//        if (dotIndex >= 0 && dotIndex < originalFileName.length() - 1) {
//            extension = originalFileName.substring(dotIndex + 1);
//        }
//
//        // Generate a random UUID as the unique part of the filename
//        String uniquePart = UUID.randomUUID().toString();
//
//        // Combine the unique part and file extension to create the unique filename
//        String uniqueFileName = uniquePart + "." + extension;
//
//        return uniqueFileName;
//    }
//    private String getFileName(Part part) {
//        String contentDisposition = part.getHeader("content-disposition");
//        String[] tokens = contentDisposition.split(";");
//        for (String token : tokens) {
//            if (token.trim().startsWith("filename")) {
//                return token.substring(token.indexOf('=') + 1).trim()
//                        .replace("\"", "");
//            }
//        }
//        return null;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int doc_id = Integer.parseInt(req.getParameter("did"));

        Doctor doctor = new DoctorDao().findById(doc_id);
        SpecialityDao speciality = new SpecialityDao();

        try {
            ArrayList<Speciality_doctor> specialitydoctor = speciality.getAllSpecialityOfDoctorUpdate(doc_id);
            ArrayList<Speciality> listspeciality = speciality.getAllSpecialityUpdate();
            req.setAttribute("SpecialityList", listspeciality);
            req.setAttribute("doctorSpeciality", specialitydoctor);
            req.setAttribute("doctor", doctor);
            req.getRequestDispatcher("/WEB-INF/views/admin/update-doctor.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("update_name");
        String email = request.getParameter("update_email");
        String degree = request.getParameter("update_degree");
        String phone = request.getParameter("update_phone");
        int experience = Integer.parseInt(request.getParameter("update_experience"));
        String dob = request.getParameter("update_dob");
        String address = request.getParameter("update_address");
        boolean gender = Boolean.parseBoolean(request.getParameter("update_gender"));

        String[] selectedOptions = request.getParameterValues("updatecheckbox");
        DoctorDao dd = new DoctorDao();
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();

        SpecialityDao spDao = new SpecialityDao();
        boolean checkMail = false;
        boolean checkPhone = false;

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
                        dd.UpdateDoctor(id, name, email, degree, experience, phone, dob, gender, address);
                        doctorArrayList = dd.getAllDoctorID();
                             spDao.DeleteSpecialityDoctor(id);
                        for (String option : selectedOptions) {
                            spDao.UpdateSpecialityDoctor(id, Integer.parseInt(option));
                        }

                        request.setAttribute("mess", "Cập nhật thành công");
                        response.sendRedirect("doctor-control");

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } else {
            request.setAttribute("mess", "Số điện thoại không hợp lệ!");
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/WEB-INF/views/admin/doctor-control.jsp").forward(request, response);
        }

    }
}
