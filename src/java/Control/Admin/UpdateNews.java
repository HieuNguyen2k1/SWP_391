/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Dao.NewsDao;
import Model.News;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
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

public class UpdateNews extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNews at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
       
        String news_id = request.getParameter("news_id");
        NewsDao newsDao = new NewsDao();
        try{
            News newsid = newsDao.getNewsByID(news_id);
            request.setAttribute("news", newsid);
        }catch(Exception e){            
        }
        request.getRequestDispatcher("/WEB-INF/views/admin/update-news.jsp").forward(request, response);
        
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
        int id = Integer.parseInt(request.getParameter("newsid"));
        int day = Integer.parseInt(request.getParameter("update_day"));
        String month = request.getParameter("update_month");
        String title = request.getParameter("update_title");
        String scriptS = request.getParameter("update_scriptshort");
        String scriptF = request.getParameter("update_scriptfull");
        Part filePart = request.getPart("update_image");
        NewsDao newsDao = new NewsDao();
        if(filePart != null && filePart.getSize() >0){
            String fileName = getFileName(filePart);
            assert fileName != null;
            String newFileName = generateUniqueFileName(fileName);
            String uploadDir = request.getServletContext().getRealPath("/") + "uploadsNews";
            Path filePath = Paths.get(uploadDir, newFileName);
            try( InputStream fileContent = filePart.getInputStream()){
                Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            if(newsDao.UpdateNewsImg(id, day, month, title, scriptS, scriptF, "uploadsNews/" + newFileName)){
                response.sendRedirect(request.getContextPath()+ "/admin/news-control");
            }else { // thay đổi lỗi
                request.setAttribute("error", "đã có lỗi xảy ra");
                String news_id = request.getParameter("news_id");

                try {
                    News newsArray = newsDao.getNewsByID(news_id);
                    request.setAttribute("news", newsArray);
                } catch (Exception e) {
                }               
            }            
        }else{
            if(newsDao.UpdateNewsNoImg(id, day, month, title, scriptS, scriptF)){
                response.sendRedirect(request.getContextPath() + "/admin/news-control");
            }else { // thay đổi lỗi
                request.setAttribute("error", "đã có lỗi xảy ra");
                String news_id = request.getParameter("news_id");

                try {
                    News newsArray = newsDao.getNewsByID(news_id);
                    request.setAttribute("news", newsArray);
                } catch (Exception e) {
                }               
            } 
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}