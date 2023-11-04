//package Control.Admin;
//
//import Dao.PatientDao;
//import Model.User;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class UpdatePatient extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("pid"));
//        User user = new PatientDao().getPatientById(id);
//        System.out.println(user.toString());
//        req.setAttribute("patient", user);
//        req.getRequestDispatcher("/WEB-INF/views/admin/update-patient.jsp").forward(req, resp);
//    }
//}
