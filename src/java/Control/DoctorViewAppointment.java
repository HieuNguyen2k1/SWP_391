package Control;

import Dao.AppointmentDao;
import Dao.PatientDao;
import Dao.PrescriptionDao;
import Model.Data;
import Model.Doctor;
import Model.Status;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorViewAppointment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int app_id = Integer.parseInt(req.getParameter("app_id"));

        req.setAttribute("id_appointment", app_id);
        AppointmentDao app = new AppointmentDao();
        PrescriptionDao pre = new PrescriptionDao();
        try {
            if(pre.getIdPre(app_id)!=0){
            int id_pre = pre.getIdPre(app_id);
            req.setAttribute("id_pre", id_pre);
            }else{
                req.setAttribute("id_pre", 0);
            }
            int id_patient = app.getIdPatient(app_id);
            req.setAttribute("id_patient", id_patient);

        } catch (SQLException ex) {
            Logger.getLogger(DoctorViewAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoctorViewAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id_p = 0;
        Doctor doctor = (Doctor) req.getSession().getAttribute("doctor");

        if (req.getSession().getAttribute("session_para") != null) {
            String a = (String) req.getSession().getAttribute("session_para");
            req.setAttribute(a.split("\\|")[0], a.split("\\|")[1]);
            req.getSession().removeAttribute("session_para");
        }

        Data data;
        try {
            data = new AppointmentDao().getDetail(app_id, doctor.id);
            req.setAttribute("id_doctor", doctor.id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String hi = req.getParameter("update");
        if (hi == null) {
            String Update = "null";
            req.setAttribute("Update", Update);
        } else {
            String Update = "update";
            req.setAttribute("Update", Update);
        }

        if (data != null) {

            req.setAttribute("app", data.object1);
            req.setAttribute("patient", data.object2);
            req.setAttribute("time", data.object3);
            req.getRequestDispatcher("/WEB-INF/views/doctor/doctor-view-appointment.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Đã có lỗi xảy ra hoặc đây không phải là cuộc hẹn của bạn!");
            req.getRequestDispatcher("/WEB-INF/views/doctor/doctor-view-appointment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        int app_id = Integer.parseInt(req.getParameter("app_id"));
        Doctor doctor = (Doctor) req.getSession().getAttribute("doctor");
        AppointmentDao appointmentDao = new AppointmentDao();
        try {
            Status.valueOf(status);
            if (appointmentDao.updateAppointmentStatus(status, app_id, doctor.id)) {
//                req.getSession().setAttribute("session_para", "success|Thay đổi thành công!");
                req.getSession().setAttribute("session_para", "success|Cập nhật thành công!");
//                resp.sendRedirect(req.getContextPath() + "/doctor/appointment-details?app_id=" + app_id);
                resp.sendRedirect(req.getContextPath() + "/doctor/appointment-details?app_id=" + app_id);
            } else {
                req.getSession().setAttribute("session_para", "error|Thay đổi Thất bại!");
                resp.sendRedirect(req.getContextPath() + "/doctor/appointment-details?app_id=" + app_id);
            }
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("session_para", "success|Thay đổi Thất bại!");
            resp.sendRedirect(req.getContextPath() + "/doctor/appointment-details?app_id=" + app_id);
        }
    }
}
