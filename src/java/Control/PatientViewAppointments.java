package Control;

import Dao.AppointmentDao;
import Dao.RecorDAO;
import Model.Appointment;
import Model.Record;
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
        String status = req.getParameter("method");
        if (status == null) {
            ArrayList<Appointment> appointments = new AppointmentDao().getAllAppointmentsOfPatient(id);
            req.setAttribute("appointments", appointments);
            if (req.getSession().getAttribute("session_mess") != null) {
                String mess = (String) req.getSession().getAttribute("session_mess");
                req.setAttribute(mess.split("\\|")[0], mess.split("\\|")[1]);
                req.getSession().removeAttribute("session_mess");
            }
        } else if (status.equals("finished")) {
            ArrayList<Appointment> appointments = new AppointmentDao().getAllAppointmentsOfPatientFinish(id);
            req.setAttribute("appointments", appointments);
            
            ArrayList<Record> records = new RecorDAO().getAllAppointmentsOfPatientFinish1(id);
            req.setAttribute("records", records);
            //==============
            System.out.println("=================== record =================");
            for (Record record : records) {
                System.out.println(record);
            }

// Using a traditional for loop
            for (int i = 0; i < records.size(); i++) {
                Record record = records.get(i);
                System.out.println(record);
            }
            //========================
             //==============
            System.out.println("======================================");
            for (Appointment record : appointments) {
                System.out.println(record);
            }

// Using a traditional for loop
            for (int i = 0; i < appointments.size(); i++) {
                Appointment record = appointments.get(i);
                System.out.println(record);
            }
            //========================

            if (req.getSession().getAttribute("session_mess") != null) {
                String mess = (String) req.getSession().getAttribute("session_mess");
                req.setAttribute(mess.split("\\|")[0], mess.split("\\|")[1]);
                req.getSession().removeAttribute("session_mess");
            }
        } else if (status.equals("not-yet")) {
            ArrayList<Appointment> appointments = new AppointmentDao().getAllAppointmentsOfPatientNotYet(id);
            req.setAttribute("appointments", appointments);
            if (req.getSession().getAttribute("session_mess") != null) {
                String mess = (String) req.getSession().getAttribute("session_mess");
                req.setAttribute(mess.split("\\|")[0], mess.split("\\|")[1]);
                req.getSession().removeAttribute("session_mess");
            }

        }

        req.getRequestDispatcher("/WEB-INF/views/patient-view-appointments.jsp").forward(req, resp);

    }
}
