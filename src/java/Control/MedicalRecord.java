/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.AppointmentDao;
import Dao.MedicineDao;
import Dao.PatientDao;
import Dao.PrescriptionDao;
import Dao.RecorDAO;
import Model.Data;
import Model.Prescription;

import Model.Record;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author HP
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class MedicalRecord extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MedicalRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MedicalRecord at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String generateUniqueFileName(String originalFileName) {

        /*nếu originalFileName là "file.txt", biến extension sẽ được gán giá trị "txt". 
        Nếu không có dấu chấm trong tên tệp, biến extension sẽ vẫn giữ giá trị rỗng.*/
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < originalFileName.length() - 1) {
            extension = originalFileName.substring(dotIndex + 1);
        }
        // Tạo UUID ngẫu nhiên làm phần duy nhất của filename
        String uniquePart = UUID.randomUUID().toString();

        // Kết hợp uniquePart và file extension để tạo unique filename
        String uniqueFileName = uniquePart + "." + extension;

        return uniqueFileName;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        int id_patient = Integer.parseInt(request.getParameter("id_patient"));
        int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
        if (request.getParameter("id_pre") != null) {
            int id_pre = Integer.parseInt(request.getParameter("id_pre"));
            request.setAttribute("id_pre", id_pre);
        }
        request.setAttribute("id_doctor", id_doctor);
        int id_app = Integer.parseInt(request.getParameter("id_app"));

        Data data;
        try {
            data = new AppointmentDao().getDetail(id_app, id_doctor);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (data != null) {
            request.setAttribute("app", data.object1);
            request.setAttribute("patient", data.object2);
            request.setAttribute("time", data.object3);

        } else {
            request.setAttribute("error", "Đã có lỗi xảy ra hoặc đây không phải là cuộc hẹn của bạn!");
            request.getRequestDispatcher("/WEB-INF/views/doctor/CreateRecordMedical.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/views/doctor/CreateRecordMedical.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy id để truy xuất
        int id_patient = Integer.parseInt(request.getParameter("id_patient"));
        int id_appointment = Integer.parseInt(request.getParameter("id_appointment"));
        int id_doctor = Integer.parseInt(request.getParameter("id_doctor"));
        //======================================================================
        //           Lấy thông tin bệnh án
        String chuandoan = request.getParameter("chuandoan");
        String time = request.getParameter("time");
        Part imagePart = request.getPart("imageUpload");
        Part filePart = request.getPart("fileUpload");
        String height = request.getParameter("height");
        String group_blood = request.getParameter("group_blood");
        int weight = Integer.parseInt(request.getParameter("weight"));

//        String fileFileName = getFileName(filePart);
        HttpSession session = request.getSession();
        session.setAttribute("time", time);
        session.setAttribute("chuandoan", chuandoan);
        session.setAttribute("imagePart", imagePart);
        session.setAttribute("filePart", filePart);
        session.setAttribute("id_patient", id_patient);
        session.setAttribute("id_appointment", id_appointment);
        session.setAttribute("height", height);
        session.setAttribute("weight", weight);
        // input file
        InputStream fileContent1 = null;
        String imageFileName1 = getFileName(filePart);
        if (!imageFileName1.equals("")) {
            fileContent1 = filePart.getInputStream();
        }
        //================================================
        //      Tạo một đơn thuốc
        if (!request.getParameter("ngaydung").equals("")) {
            int ngaydung = Integer.parseInt(request.getParameter("ngaydung"));
            PrescriptionDao prescription = new PrescriptionDao();
            boolean create = prescription.CreatePrescription(id_patient, id_appointment, id_doctor, ngaydung);
            //   Lấy id prescription

            Prescription select = new Prescription();
            select = prescription.getIdPrescription(id_appointment, id_patient);

            //       Lấy thông tin thuốc
            String tenthuoc1 = request.getParameter("tenthuoc1");
            String lieuluong1 = request.getParameter("lieuluong1");

            String tenthuoc2 = request.getParameter("tenthuoc2");
            String lieuluong2 = request.getParameter("lieuluong2");

            String tenthuoc3 = request.getParameter("tenthuoc3");
            String lieuluong3 = request.getParameter("lieuluong3");

            String tenthuoc4 = request.getParameter("tenthuoc4");
            String lieuluong4 = request.getParameter("lieuluong4");

            String tenthuoc5 = request.getParameter("tenthuoc5");
            String lieuluong5 = request.getParameter("lieuluong5");

            String tenthuoc6 = request.getParameter("tenthuoc6");
            String lieuluong6 = request.getParameter("lieuluong6");

            String tenthuoc7 = request.getParameter("tenthuoc7");
            String lieuluong7 = request.getParameter("lieuluong7");

            MedicineDao medicine = new MedicineDao();
            //          Cập nhật thuốc vào bệnh án
            int id_pre = select.getId();
            request.setAttribute("id_pre", id_pre);
            if (!tenthuoc1.equals("") && !lieuluong1.equals("")) {
                boolean createMe = medicine.CreateMedicine(tenthuoc1, lieuluong1, select.getId());

            }
            if (!tenthuoc2.equals("") && !lieuluong2.equals("")) {
                boolean createMe = medicine.CreateMedicine(tenthuoc2, lieuluong2, select.getId());

            }
            if (!tenthuoc3.equals("") && !lieuluong3.equals("")) {
                boolean createMe = medicine.CreateMedicine(tenthuoc3, lieuluong3, select.getId());

            }
            if (!tenthuoc4.equals("") && !lieuluong4.equals("")) {
                boolean createMe = medicine.CreateMedicine(tenthuoc4, lieuluong4, select.getId());

            }
            if (!tenthuoc5.equals("") && !lieuluong5.equals("")) {
                boolean createMe = medicine.CreateMedicine(tenthuoc5, lieuluong5, select.getId());

            }
            if (!tenthuoc6.equals("") && !lieuluong6.equals("")) {

                boolean createMe = medicine.CreateMedicine(tenthuoc6, lieuluong6, select.getId());
            }
            if (!tenthuoc7.equals("") && !lieuluong7.equals("")) {

                boolean createMe = medicine.CreateMedicine(tenthuoc7, lieuluong7, select.getId());

            }
            //================================================
            // input file hình ảnh
            String imageFileName = getFileName(imagePart);
            //  file ảnh rỗng
            if (imageFileName.equals("")) {
                String newFileName = null;
                PatientDao patient = new PatientDao();
                boolean check1 = patient.UpdateBloodPatient(id_patient, group_blood);
                RecorDAO record = new RecorDAO();
                boolean check = record.createRecord(time, chuandoan, id_patient, id_appointment, newFileName, fileContent1, height, weight);
                request.setAttribute("mess", "successfull");
                request.setAttribute("status", "finished");

                response.sendRedirect(request.getContextPath() + "/doctor/appointment-details?app_id=" + id_appointment + "&id_pe=" + id_pre + "&update=1");

            } else {
                //  file ảnh 
                String newFileName = generateUniqueFileName(imageFileName);

                String uploadDir = request.getServletContext().getRealPath("/") + "imageRecord";
                Path filePath = Paths.get(uploadDir, newFileName);
                try ( InputStream fileContent = imagePart.getInputStream()) {
                    Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                PatientDao patient = new PatientDao();
                boolean check1 = patient.UpdateBloodPatient(id_patient, group_blood);
                RecorDAO record = new RecorDAO();
                boolean check = record.createRecord(time, chuandoan, id_patient, id_appointment, "imageRecord/" + newFileName, fileContent1, height, weight);
                request.setAttribute("mess", "successfull");
                request.setAttribute("status", "finished");
                response.sendRedirect(request.getContextPath() + "/doctor/appointment-details?app_id=" + id_appointment + "&id_pe=" + id_pre + "&update=1");

            }
        } else {
            int id_pre =0;
             request.setAttribute("id_pre", id_pre);

             //================================================
            // input file hình ảnh
            String imageFileName = getFileName(imagePart);
            //  file ảnh rỗng
            if (imageFileName.equals("")) {
                String newFileName = null;
                PatientDao patient = new PatientDao();
                boolean check1 = patient.UpdateBloodPatient(id_patient, group_blood);
                RecorDAO record = new RecorDAO();
                boolean check = record.createRecord(time, chuandoan, id_patient, id_appointment, newFileName, fileContent1, height, weight);
                request.setAttribute("mess", "successfull");
                request.setAttribute("status", "finished");

                response.sendRedirect(request.getContextPath() + "/doctor/appointment-details?app_id=" + id_appointment + "&id_pe=" + id_pre + "&update=1");

            } else {
                //  file ảnh 
                String newFileName = generateUniqueFileName(imageFileName);

                String uploadDir = request.getServletContext().getRealPath("/") + "imageRecord";
                Path filePath = Paths.get(uploadDir, newFileName);
                try ( InputStream fileContent = imagePart.getInputStream()) {
                    Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                PatientDao patient = new PatientDao();
                boolean check1 = patient.UpdateBloodPatient(id_patient, group_blood);
                RecorDAO record = new RecorDAO();
                boolean check = record.createRecord(time, chuandoan, id_patient, id_appointment, "imageRecord/" + newFileName, fileContent1, height, weight);
                request.setAttribute("mess", "successfull");
                request.setAttribute("status", "finished");
                response.sendRedirect(request.getContextPath() + "/doctor/appointment-details?app_id=" + id_appointment + "&id_pe=" + id_pre + "&update=1");

            }
        }

//        assert fileFileName != null;
//        String newfileFileName = generateUniqueFileName(fileFileName);
//        String uploadDir1 = request.getServletContext().getRealPath("/") + "fileRecord";
//        Path filePath1 = Paths.get(uploadDir1, newfileFileName);
//        try ( InputStream fileContent = filePart.getInputStream()) {
//            Files.copy(fileContent, filePath1, StandardCopyOption.REPLACE_EXISTING);
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
