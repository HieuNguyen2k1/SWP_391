package Control;

import Dao.AppointmentDao;
import Model.Data;
import Model.Doctor;
import Model.Status;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DoctorViewAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int app_id = Integer.parseInt(req.getParameter("app_id"));
        Doctor doctor= (Doctor) req.getSession().getAttribute("doctor");
        if (req.getSession().getAttribute("session_para")!=null){
            String a = (String)req.getSession().getAttribute("session_para");
            req.setAttribute(a.split("\\|")[0], a.split("\\|")[1]);
            req.getSession().removeAttribute("session_para");
        }
      
        Data data;
        try {
            data = new AppointmentDao().getDetail(app_id, doctor.id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (data != null){
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
        Doctor doctor= (Doctor) req.getSession().getAttribute("doctor");
        AppointmentDao appointmentDao = new AppointmentDao();
        try {
            Status.valueOf(status);
            if (appointmentDao.updateAppointmentStatus(status, app_id, doctor.id)){
//                req.getSession().setAttribute("session_para", "success|Thay đổi thành công!");
//                resp.sendRedirect(req.getContextPath() + "/doctor/appointment-detail?app_id=" + app_id);
                resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
            } else {
                req.getSession().setAttribute("session_para", "error|Thay đổi Thất bại!");
                resp.sendRedirect(req.getContextPath() + "/doctor/appointment-detail?app_id=" + app_id);
            }
        } catch (IllegalArgumentException e){
            req.getSession().setAttribute("session_para", "success|Thay đổi Thất bại!");
            resp.sendRedirect(req.getContextPath() + "/doctor/appointment-detail?app_id=" + app_id);
        }
    }
}
