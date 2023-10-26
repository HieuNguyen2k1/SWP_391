package Control;

import Dao.DoctorScheduleDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        if (doctorScheduleDao.deleteSchedule(id)){
            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
        }
    }
}
