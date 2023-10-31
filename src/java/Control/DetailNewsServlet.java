/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.NewsDao;
import Model.News;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */

public class DetailNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            NewsDao newsDao = new NewsDao();
            String nid = request.getParameter("nid");
            News newsdetail = newsDao.getNewsByID(nid);
            request.setAttribute("detail", newsdetail);
            request.getRequestDispatcher("/WEB-INF/views/newsDetail.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }


}
