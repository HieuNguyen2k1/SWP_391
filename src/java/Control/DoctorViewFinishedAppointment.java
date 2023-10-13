package Control;

import Dao.AppointmentDao;
import Model.Appointment;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class DoctorViewFinishedAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Doctor doctor = (Doctor) req.getSession().getAttribute("doctor");
        ArrayList<Appointment> appointments = new AppointmentDao().getAllAppointmentOfDoctor(doctor.id);
        req.setAttribute("appointments", appointments);
        System.out.println(appointments);
        req.getRequestDispatcher("/WEB-INF/views/doctor/view-all-finished-appointment.jsp").forward(req, resp);
    }
}
