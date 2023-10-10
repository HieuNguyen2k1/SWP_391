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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100  // 100 MB
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
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        Doctor doctor = new DoctorDao().findById(doc_id);
        req.setAttribute("doctor", doctor);
        req.setAttribute("speciality_list", new SpecialityDao().getAllSpeciality());
        req.getRequestDispatcher("/WEB-INF/views/admin/update-doctor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("update_name");
        String email = req.getParameter("update_email");
        String password = req.getParameter("update_password");
        String degree = req.getParameter("update_degree");
        int experience = Integer.parseInt(req.getParameter("update_experience"));
        int speciality_id = Integer.parseInt(req.getParameter("update_speciality_id"));
        String phone = req.getParameter("update_phone");
        String dob = req.getParameter("update_dob");
        boolean gender = false;
        if(req.getParameter("update_gender").equals("1")){
            gender = true;
        }
        String address = req.getParameter("update_address");
//        Part filePart = req.getPart("update_image");
        DoctorDao doctorDao = new DoctorDao();
//
//        if (filePart != null && filePart.getSize() > 0) {
//            String fileName = getFileName(filePart);
//            assert fileName != null;
//            String newFileName = generateUniqueFileName(fileName);
//            String uploadDir = req.getServletContext().getRealPath("/") + "uploads";
//            Path filePath = Paths.get(uploadDir, newFileName);
//            try (InputStream fileContent = filePart.getInputStream()) {
//                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
//            }
//            if (doctorDao.UpdateDoctorImg(id,name,email,password,degree,experience,speciality_id,phone,dob,gender,address, "uploads/" + newFileName)){
//                resp.sendRedirect(req.getContextPath() + "/admin/doctor-control");
//            } else { // thay đổi lỗi
//                req.setAttribute("error", "đã có lỗi xảy ra");
//                int doc_id = Integer.parseInt(req.getParameter("doc_id"));
//                Doctor doctor = new DoctorDao().findById(doc_id);
//                req.setAttribute("doctor", doctor);
//                req.setAttribute("speciality_list", new SpecialityDao().getAllSpeciality());
//                req.getRequestDispatcher("/WEB-INF/views/admin/update-doctor.jsp").forward(req,resp);
//            }
//
//        } else {
            if (doctorDao.UpdateDoctorNoImg(id,name,email,password,degree,experience,speciality_id,phone,dob,gender,address)){
                resp.sendRedirect(req.getContextPath() + "/admin/doctor-control");
            } else { // thay đổi lỗi
                req.setAttribute("error", "đã có lỗi xảy ra");
                int doc_id = Integer.parseInt(req.getParameter("doc_id"));
                Doctor doctor = new DoctorDao().findById(doc_id);
                req.setAttribute("doctor", doctor);
                req.setAttribute("speciality_list", new SpecialityDao().getAllSpeciality());
                req.getRequestDispatcher("/WEB-INF/views/admin/update-doctor.jsp").forward(req,resp);
            }
//        }
    }
}
