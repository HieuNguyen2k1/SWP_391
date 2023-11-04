//
//package Control.Admin;
//
//import Dao.Admin.UpdateUser;
//import Dao.AuthDao;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import Model.User;
//
//import java.io.IOException;
//
//
//@WebServlet(name = "DeleteControl", urlPatterns = {"/DeleteControl"})
//public class DeleteControl extends HttpServlet {
//
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //b1: get sid from jsp
//        int id = Integer.parseInt(request.getParameter("sid"));
//        //b2: pass sid to dao
//        UpdateUser dao = new UpdateUser();
//        dao.deleteUser(id);
//        RequestDispatcher rd = request.getRequestDispatcher("LoadPatients");
//        rd.forward(request,response);
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//}
