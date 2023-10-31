/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.NewsDao;
import Model.News;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchNews extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            NewsDao newsDao = new NewsDao();
            String txtSearch = request.getParameter("txt");
            ArrayList<News> searchNews = newsDao.searchNews(txtSearch);
            if (searchNews.isEmpty()) {
                String none = "Không tìm thấy dữ liệu!";
                request.setAttribute("none", none);

            } else {
                request.setAttribute("SearchNews", searchNews);
            }
            request.getRequestDispatcher("/WEB-INF/views/news.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

}
