package Control;

import Dao.AppointmentDao;
import Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class PatientCancelAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int app_id = Integer.parseInt(req.getParameter("app_id"));
        try {
            int status = new AppointmentDao().cancelAppointment(app_id);
            if (status == 1){
                req.getSession().setAttribute("session_mess", "success|Xoá thành công");
            } else if (status == 0) {
                req.getSession().setAttribute("session_mess", "error|không thể xoá lịch hẹn của quá khứ!");
            } else if (status == -1) {
                req.getSession().setAttribute("session_mess", "error|không thể xoá lịch đã khám!");
            } else if (status == -2) {
                req.getSession().setAttribute("session_mess", "error|Id cuộc hẹn không đúng!");
            } else if (status == -3) {
                req.getSession().setAttribute("session_mess", "error|Có lỗi ở hệ thống, vui lòng liên hệ admin!");
            } else if (status == 2) {
                req.getSession().setAttribute("session_mess", "error|Xoá cuộc hẹn chưa thành công!");
            }
            resp.sendRedirect(req.getContextPath() + "/patient/view-appointments");
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
