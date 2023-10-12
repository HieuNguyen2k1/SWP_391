package Control;

import Dao.DoctorScheduleDao;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year, weekNumber;
        if (req.getSession().getAttribute("week_and_year_url") != null) {
            String current_week = (String) req.getSession().getAttribute("week_and_year_url");
            String[] arr = current_week.split("-W");
            year = Integer.parseInt(arr[0]);
            weekNumber = Integer.parseInt(arr[1]);
            req.getSession().removeAttribute("week_and_year_url");
        } else {
            Calendar calendar = Calendar.getInstance();
            weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            year = calendar.get(Calendar.YEAR);
        }
        String current_week = year + "-W" + weekNumber;
        int doctor_id = ((Doctor) req.getSession().getAttribute("doctor")).getId();
        if (req.getSession().getAttribute("url_mess") != null){
            String session_message = (String)req.getSession().getAttribute("url_mess");
            req.setAttribute(session_message.split("\\|")[0], session_message.split("\\|")[1]);
            req.getSession().removeAttribute("url_mess");
        }
        /*String[] dateArray = getDateOfWeek(year, weekNumber);
        String current_week = year + "-W" + weekNumber;
        if (req.getSession().getAttribute("url_mess") != null) {
            String url_mess = (String) req.getSession().getAttribute("url_mess");
            req.setAttribute(url_mess.split("\\|")[0], url_mess.split("\\|")[1]);
            req.getSession().removeAttribute("url_mess");
        }
        String first_date_week = dateArray[1];
        String last_date_week = dateArray[7];
        int doctor_id = ((Doctor) req.getSession().getAttribute("doctor")).getId();
        DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
        ArrayList<DoctorSchedule> doctorScheduleArrayList = doctorScheduleDao.getScheduleOfWeek(doctor_id, first_date_week, last_date_week);

        String[][] table = new String[11][8];
        table[0] = new String[]{"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        table[1] = dateArray;
        for (int i = 2; i < 11; i++) {
            table[i][0] = (i + 6) + "h -> " + (i + 7) + "h";
        }
        if (doctorScheduleArrayList == null){
            req.setAttribute("table", table);
        } else {
            for (int i = 2; i < 11; i++) {
                for (int j = 1; j < 8; j++) {
                    int table_date = Integer.parseInt(table[1][j].split("-")[2]);
                    for (int k = 0; k < doctorScheduleArrayList.size(); k++) {
                        int start_time = Integer.parseInt(doctorScheduleArrayList.get(k).start.split(" ")[1].split(":")[0]);
                        int end_time = Integer.parseInt(doctorScheduleArrayList.get(k).end.split(" ")[1].split(":")[0]);
                        int date = Integer.parseInt(doctorScheduleArrayList.get(k).start.split(" ")[0].split("-")[2]);
                        if (table_date == date){
                            if ((i + 6) >= start_time && (i + 7) <= end_time) {
                                table[i][j] = "bận";
                            }
                        }

                    }
                }
            }
        }*/
        req.setAttribute("current_week", current_week);
        try {
            req.setAttribute("table", Table.createScheduleTableForDoctorTest(year, weekNumber,doctor_id));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/views/doctor/schedule.jsp").forward(req, resp);
    }
    public ArrayList<String> generateArrayStringOfTime(String date, float start, float end){
        ArrayList<String> arrayList = new ArrayList<>();
        for (float i = start; i < end; i += 0.5){
            arrayList.add(date + " " + DoctorScheduleDao.generateTime(i));
        }
        System.out.println(arrayList);
        return arrayList;
    }
    public ArrayList<String> generateArrayStringOfTime(String from_date, String to_date, float start, float end){
        ArrayList<String> arrayList = new ArrayList<>();
        List<LocalDate> datesInRange = DoctorScheduleDao.getDatesInRange(from_date, to_date);
        for (LocalDate date : datesInRange) {
            for (float i = start; i < end; i += 0.5) {
                arrayList.add(date + " " + DoctorScheduleDao.generateTime(i));
            }
        }
        System.out.println(arrayList);
        return arrayList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("_method").equals("get_date")) {
            String week_and_year = req.getParameter("week");
            req.getSession().setAttribute("week_and_year_url", week_and_year);
            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
        } else if (req.getParameter("_method").equals("add_schedule")) {
            String from_date = req.getParameter("from_date");
            String to_date = req.getParameter("to_date");
            float start = Float.parseFloat(req.getParameter("from"));
            float end = Float.parseFloat(req.getParameter("to"));
            int doctor_id = ((Doctor) req.getSession().getAttribute("doctor")).getId();
            DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
            if (end < start || start < 8 || end > 17) {
                req.getSession().setAttribute("url_mess", "error|Thời gian kết thúc phải nhỏ hơn thời gian bắt đầu, thời gian nằm trong khoảng từ 8h đến 17h!");
                resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
            } else {
                if (req.getParameter("to_date").isEmpty()){
                    ArrayList<String> arrayList = generateArrayStringOfTime(from_date, start, end);
                    if (doctorScheduleDao.checkValidTIme(arrayList, doctor_id)){ // check date co valid k
                        if (doctorScheduleDao.addScheduleOneDay(doctor_id, from_date, start, end)) {
                            req.getSession().setAttribute("url_mess", "success|Thêm thành công!");
                            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                        } else {
                            req.getSession().setAttribute("url_mess", "error|Đã có lỗi!");
                            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                        }
                    } else {
                        req.getSession().setAttribute("url_mess", "error|Bạn đã đặt trùng lịch hoặc lịch của quá khứ!");
                        resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                    }
                } else {
                    ArrayList<String> arrayList = generateArrayStringOfTime(from_date,to_date, start, end);
                    if (doctorScheduleDao.checkValidTIme(arrayList, doctor_id)){ // check date co valid k
                        if (doctorScheduleDao.addManyDays(doctor_id, from_date, to_date, start, end)){
                            req.getSession().setAttribute("url_mess", "success|Thêm thành công!");
                            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                        } else {
                            req.getSession().setAttribute("url_mess", "error|Đã có lỗi!");
                            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                        }
                    } else {
                        req.getSession().setAttribute("url_mess", "error|Bạn đã đặt trùng lịch hoặc lịch của quá khứ!");
                        resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
                    }

                }
            }
        }
    }
}
