package Control.Admin;

import Dao.Admin.UpdateUser;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ControlPatientAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateUser dao = new UpdateUser();
        List<User> list = dao.getPatientsList();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/admin/control-patient-appointment.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
