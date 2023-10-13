package Control.Admin;

import Dao.AuthDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.UUID;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        String verify_key = UUID.randomUUID().toString();
//
//        AuthDao dao = new AuthDao();
//        try {
//            dao.createPatients(name,email,password,phone,verify_key,true);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        response.sendRedirect("read-user");
    }
}
