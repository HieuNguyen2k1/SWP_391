/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Dao.NewsDao;
import Model.News;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class NewsControl extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDao newsDao = new NewsDao();
        ArrayList<News> newsArrayList;
        try {
            newsArrayList = newsDao.getAllNews();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("news_list", newsArrayList);
        request.getRequestDispatcher("/WEB-INF/views/admin/news-control.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("_method").equals("DELETE")) {
            this.doDelete(request, response);
        } else {
            int day = Integer.parseInt(request.getParameter("day"));
            String month = request.getParameter("month");
            String title = request.getParameter("title");
            String scriptshort = request.getParameter("scriptshort");
            String scriptfull = request.getParameter("scriptfull");
            Part filePart = request.getPart("image");

            String fileName = getFileName(filePart);
            assert fileName != null;
            String newFileName = generateUniqueFileName(fileName);
            String uploadDir = request.getServletContext().getRealPath("/") + "uploadNews";
            Path filePath = Paths.get(uploadDir, newFileName);
            try ( InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            NewsDao newsDao = new NewsDao();
            boolean check = newsDao.createNews(day, month, title, scriptshort, scriptfull, "uploadNews/" + newFileName);
            if (check) {
                response.sendRedirect("news-control");
            } else {
                request.setAttribute("message", "Error");
                request.getRequestDispatcher("/WEB-INF/views/admin/news-control.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsDao newsDao = new NewsDao();
        int id = Integer.parseInt(request.getParameter("id"));
        newsDao.deleteNews(id);
        response.sendRedirect("news-control");
    }

}
