/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;


import Dao.RecorDAO;

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
@MultipartConfig
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
        RecorDAO record = new RecorDAO();
        ArrayList<Record> newsArrayList;
        try {
            newsArrayList = record.getAllRecord();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("news_list", newsArrayList);
        request.getRequestDispatcher("/WEB-INF/views/admin/news-control.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_patient = Integer.parseInt(request.getParameter("id_patient"));
        int id_oppoinment = Integer.parseInt(request.getParameter("id_oppoinment"));
        String chuandoan = request.getParameter("chuandoan");
        String time = request.getParameter("time");
        Part imagePart = request.getPart("imageUpload");
        Part filePart = request.getPart("fileUpload");

        // Lấy tên của hình ảnh và tệp tin
        String imageFileName = getFileName(imagePart);
        String fileFileName = getFileName(filePart);
        HttpSession session = request.getSession();
        session.setAttribute("time", time);
        session.setAttribute("chuandoan", chuandoan);
        session.setAttribute("imagePart", imagePart);
        session.setAttribute("filePart", filePart);
        session.setAttribute("id_patient", id_patient);
        session.setAttribute("id_oppoinment", id_oppoinment);
      

        assert imageFileName != null;
        String newFileName = generateUniqueFileName(imageFileName);
        String uploadDir = request.getServletContext().getRealPath("/") + "imageRecord";
        Path filePath = Paths.get(uploadDir, newFileName);
        try ( InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        assert fileFileName != null;
        String newfileFileName = generateUniqueFileName(fileFileName);
        String uploadDir1 = request.getServletContext().getRealPath("/") + "fileRecord";
        Path filePath1 = Paths.get(uploadDir, newFileName);
        try ( InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        RecorDAO record = new RecorDAO();
        boolean check = record.createRecord(time, chuandoan, id_patient,id_oppoinment, "imageRecord/" + newFileName,"fileRecord/"+newfileFileName);

        response.sendRedirect("result.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
