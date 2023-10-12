package Control.Admin;

import Dao.DoctorScheduleDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteDoctorSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String url = req.getParameter("url");
        url = url.replace("-", "&");
        url = url.replace("&W", "-W");
        System.out.println(url);
        if (new DoctorScheduleDao().deleteSchedule(id)){
            resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control?"+url);
        }
    }
}
