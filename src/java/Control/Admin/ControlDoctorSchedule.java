package Control.Admin;

import Control.Table;
import Dao.DoctorDao;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class ControlDoctorSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        
        if (req.getParameter("_method") != null){
            int year = 0, weekNumber = 0;
            Doctor doctor = null;
            DoctorDao doctorDao = new DoctorDao();
            ArrayList<Doctor> doctors;
            try {
                doctors = doctorDao.getAllDoctorOn();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("doctors", doctors);
            if (req.getParameter("_method").equals("choose_doctor")){
                int id = Integer.parseInt(req.getParameter("doctor_id"));
                url += "_method=choose_doctor-doctor_id=" + id;
                doctor = new DoctorDao().findByIdSchedule(id);
                req.setAttribute("doctor", doctor);
                Calendar calendar = Calendar.getInstance();
                weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
                year = calendar.get(Calendar.YEAR);
            } else if (req.getParameter("_method").equals("get_date")) {
                String week_and_year = req.getParameter("week");
                int id = Integer.parseInt(req.getParameter("doctor_id"));
                url += "_method=get_date-doctor_id=" + id + "-week="+week_and_year;
                doctor = new DoctorDao().findByIdSchedule(id);
                req.setAttribute("doctor", doctor);
                year = Integer.parseInt(week_and_year.split("-W")[0]);
                weekNumber = Integer.parseInt(week_and_year.split("-W")[1]);
            }
            if (year !=0 && weekNumber != 0 && doctor!=null){
                try {
                    req.setAttribute("table", Table.createScheduleTableForDoctorTest(year, weekNumber, doctor.id));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            String current_week = year + "-W" + weekNumber;
            req.setAttribute("current_week", current_week);
        }

        DoctorDao doctorDao = new DoctorDao();
        ArrayList<Doctor> doctors;
        try {
            doctors = doctorDao.getAllDoctorOn();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("url", url);
        req.setAttribute("doctors", doctors);
        req.getRequestDispatcher("/WEB-INF/views/admin/control-doctor-schedule.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = 0, weekNumber = 0;
        Doctor doctor = null;
        DoctorDao doctorDao = new DoctorDao();
        ArrayList<Doctor> doctors;
        try {
            doctors = doctorDao.getAllDoctorOn();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("doctors", doctors);
        if (req.getParameter("_method").equals("choose_doctor")){
            int id = Integer.parseInt(req.getParameter("doctor_id"));
            doctor = new DoctorDao().findById(id);
            req.setAttribute("doctor", doctor);
            Calendar calendar = Calendar.getInstance();
            weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            year = calendar.get(Calendar.YEAR);
        } else if (req.getParameter("_method").equals("get_date")) {
            String week_and_year = req.getParameter("week");
            int id = Integer.parseInt(req.getParameter("doctor_id"));
            doctor = new DoctorDao().findById(id);
            req.setAttribute("doctor", doctor);
            year = Integer.parseInt(week_and_year.split("-W")[0]);
            weekNumber = Integer.parseInt(week_and_year.split("-W")[1]);
        }
        if (year !=0 && weekNumber != 0 && doctor!=null){
            try {
                req.setAttribute("table", Table.createScheduleTableForDoctorTest(year, weekNumber, doctor.id));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        String current_week = year + "-W" + weekNumber;
        req.setAttribute("current_week", current_week);

        req.getRequestDispatcher("/WEB-INF/views/admin/control-doctor-schedule.jsp").forward(req, resp);
    }
}
