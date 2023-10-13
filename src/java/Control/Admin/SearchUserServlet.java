package Control.Admin;//package control.admin.user;
//
//import dao.admin.AccountDAO;
//import model.User;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.ArrayList;
//
//@WebServlet(name = "SearchUserServlet", value = "/SearchUserServlet")
//public class SearchUserServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String fullname = request.getParameter("fullname");
//        ArrayList<User> result = new AccountDAO().seachUser(fullname);
//        request.setAttribute("list", result);
//        request.setAttribute("action", "search");
//        request.getRequestDispatcher("adminManageCustomer.jsp").forward(request, response);
//    }
//}
