package Control;

import Dao.AppointmentDao;
import Dao.DoctorDao;
import Model.Appointment;
import Model.Data;
import Model.Review;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MakeAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("session_mess")!= null){
            String session_mess = (String) req.getSession().getAttribute("session_mess");
            req.setAttribute(session_mess.split("\\|")[0], session_mess.split("\\|")[1]);
            req.getSession().removeAttribute("session_mess");
        }
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        AppointmentDao appointmentDao = new AppointmentDao();
        DoctorDao doctordao = new DoctorDao();
        Data data;
        List<Review> review; // Khai báo biến review ở đầu phương thức
        try {
            data = appointmentDao.getAvailableAppointment(doc_id);
//            review = doctordao.getReviewByIdDoctor(doc_id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Appointment> appointments = (ArrayList<Appointment>)data.object1;
        String app_json = "";
        for (int i = 0; i < appointments.size(); i++) {
            if (i == 0){
                app_json += "[";
            }
            if (i == appointments.size() -1){
                app_json += appointments.get(i);
            } else {
                app_json += appointments.get(i) + ", ";
            }
        }
        app_json += "]";
        req.setAttribute("doctor", data.object2);
        req.setAttribute("available", app_json);
//        req.setAttribute("review", review);
        req.getRequestDispatcher("/WEB-INF/views/make-appointment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int doc_id = Integer.parseInt(req.getParameter("doc_id"));
        int patient_id = ((User) req.getSession().getAttribute("acc")).getId();
        int doctor_schedule_id = Integer.parseInt(req.getParameter("doctor_schedule_id"));
        String note = req.getParameter("note");
        AppointmentDao appointmentDao = new AppointmentDao();
        try {
            appointmentDao.addAppointment(patient_id, note, doctor_schedule_id);
            req.getSession().setAttribute("session_mess", "success|Đặt lịch thành công!");
            resp.sendRedirect(req.getContextPath() + "/" + "patient/make-appointment?doc_id=" + doc_id);
        } catch (SQLException | ClassNotFoundException e) {
            req.getSession().setAttribute("session_mess", "error|Đã có lỗi xảy ra!");
            resp.sendRedirect(req.getContextPath() + "/" + "patient/make-appointment?doc_id=" + doc_id);
        }
    }
}
