package Control;

import Dao.AppointmentDao;
import Dao.DoctorDao;
import Dao.PrescriptionDao;
import Model.Appointment;
import Model.Data;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientViewOneAppointments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("app_id"));
        // Lấy pre
        PrescriptionDao pre = new PrescriptionDao();
        int id_pre = 0;
        try {
            id_pre = pre.getIdPre(id);

        } catch (SQLException ex) {
            Logger.getLogger(PatientViewOneAppointments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientViewOneAppointments.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("id_pre", id_pre);
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        Doctor doctor = new DoctorDao().findById(doc_id);

        try {
            Data data = new AppointmentDao().getDetail(id, doc_id);
            req.setAttribute("app", data.object1);
            req.setAttribute("patient", data.object2);
            req.setAttribute("doctor", doctor);
            req.setAttribute("time", data.object3);
            req.getRequestDispatcher("/WEB-INF/views/patient-view-appointment.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
