package Control.Admin;

import Dao.AppointmentDao;
import Dao.DoctorDao;
import Model.Data;
import Model.Doctor;
import Model.Status;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class AdminviewAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int app_id = Integer.parseInt(req.getParameter("app_id"));
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        Doctor doctor = new DoctorDao().findById(doc_id);
        Data data;
        if (req.getSession().getAttribute("session_para")!=null){
            String a = (String)req.getSession().getAttribute("session_para");
            req.setAttribute(a.split("\\|")[0], a.split("\\|")[1]);
            req.getSession().removeAttribute("session_para");
        }
        try {
            data = new AppointmentDao().adminGetDetail(app_id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (data != null){
            req.setAttribute("app", data.object1);
            req.setAttribute("patient", data.object2);
            req.setAttribute("doctor", doctor);
            req.setAttribute("time", data.object3);
            req.getRequestDispatcher("/WEB-INF/views/admin/view-doctor-appointment.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Đã có lỗi xảy ra hoặc đây không phải là cuộc hẹn của bạn!");
            req.getRequestDispatcher("/WEB-INF/views/admin/view-doctor-appointment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        int app_id = Integer.parseInt(req.getParameter("app_id"));
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        AppointmentDao appointmentDao = new AppointmentDao();
        try {
            Status.valueOf(status);
            if (appointmentDao.adminUpdateAppointmentStatus(status, app_id)){
                resp.sendRedirect(req.getContextPath() + "/admin/appointment-detail?app_id=" + app_id + "&doc_id="+doc_id);
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/appointment-detail?app_id=" + app_id + "&doc_id="+doc_id);
            }
        } catch (IllegalArgumentException e){
            resp.sendRedirect(req.getContextPath() + "/admin/appointment-detail?app_id=" + app_id + "&doc_id="+doc_id);
        }
    }
}
