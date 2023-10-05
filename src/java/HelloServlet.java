import Dao.DoctorDao;
import Model.Doctor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DoctorDao doctorDao = new DoctorDao();
//        ArrayList<Doctor> doctorArrayList;
//        try {
//            doctorArrayList = doctorDao.getTop3();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        request.setAttribute("showtop3", doctorArrayList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
