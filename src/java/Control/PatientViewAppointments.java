package Control;

import Dao.AppointmentDao;
import Model.Appointment;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class PatientViewAppointments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("acc")).id;
        ArrayList<Appointment> appointments = new AppointmentDao().getAllAppointmentsOfPatient(id);
        req.setAttribute("appointments", appointments);
        if(req.getSession().getAttribute("session_mess") != null){
            String mess = (String) req.getSession().getAttribute("session_mess");
            req.setAttribute(mess.split("\\|")[0], mess.split("\\|")[1]);
            req.getSession().removeAttribute("session_mess");
        }
        req.getRequestDispatcher("/WEB-INF/views/patient-view-appointments.jsp").forward(req,resp);
    }
}
