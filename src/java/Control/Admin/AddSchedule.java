/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Admin;

import Control.Table;
import Dao.DoctorDao;
import Dao.DoctorScheduleDao;
import Model.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class AddSchedule extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddSchedule</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSchedule at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        int year, weekNumber;
//        if (req.getSession().getAttribute("week_and_year_url") != null) {
//            String current_week = (String) req.getSession().getAttribute("week_and_year_url");
//            String[] arr = current_week.split("-W");
//            year = Integer.parseInt(arr[0]);
//            weekNumber = Integer.parseInt(arr[1]);
//            req.getSession().removeAttribute("week_and_year_url");
//        } else {
//            Calendar calendar = Calendar.getInstance();
//            weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
//            year = calendar.get(Calendar.YEAR);
//        }
//        String current_week = year + "-W" + weekNumber;
//        int doctor_id = ((Doctor) req.getSession().getAttribute("doctor")).getId();
//        if (req.getSession().getAttribute("url_mess") != null){
//            String session_message = (String)req.getSession().getAttribute("url_mess");
//            req.setAttribute(session_message.split("\\|")[0], session_message.split("\\|")[1]);
//            req.getSession().removeAttribute("url_mess");
//        }
//       
//        req.setAttribute("current_week", current_week);
//        try {
//            req.setAttribute("table", Table.createScheduleTableForDoctorTest(year, weekNumber,doctor_id));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        req.getRequestDispatcher("/WEB-INF/views/admin/control-doctor-schedule.jsp").forward(req, resp);
    }
//
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       if (req.getParameter("_method").equals("get_date")) {
            String week_and_year = req.getParameter("week");
            req.getSession().setAttribute("week_and_year_url", week_and_year);
            resp.sendRedirect(req.getContextPath() + "/doctor/schedule");
        } else if (req.getParameter("_method").equals("add_schedule")) {
            String from_date = req.getParameter("from_date");
            String to_date = req.getParameter("to_date");
            float start = Float.parseFloat(req.getParameter("from"));
            float end = Float.parseFloat(req.getParameter("to"));
            HttpSession session = req.getSession(false);
            int doctor_id = (int) req.getSession().getAttribute("id_dt");
            DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao();
            if (end < start || start < 8 || end > 17) {
                req.getSession().setAttribute("url_mess", "error|Thời gian kết thúc phải nhỏ hơn thời gian bắt đầu, thời gian nằm trong khoảng từ 8h đến 17h!");
                resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
            } else {
                if (req.getParameter("to_date").isEmpty()){
                    ArrayList<String> arrayList = generateArrayStringOfTime(from_date, start, end);
                    if (doctorScheduleDao.checkValidTIme(arrayList, doctor_id)){ // check date co valid k
                        if (doctorScheduleDao.addScheduleOneDay(doctor_id, from_date, start, end)) {
                            req.getSession().setAttribute("url_mess", "success|Đăng ký thành công.");
                            resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                        } else {
                            req.getSession().setAttribute("url_mess", "error|Đã có lỗi!");
                            resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                        }
                    } else {
                        req.getSession().setAttribute("url_mess", "error|Bạn đã đặt trùng lịch hoặc lịch của quá khứ!");
                        resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                    }
                } else {
                    ArrayList<String> arrayList = generateArrayStringOfTime(from_date,to_date, start, end);
                    if (doctorScheduleDao.checkValidTIme(arrayList, doctor_id)){ // check date co valid k
                        if (doctorScheduleDao.addManyDays(doctor_id, from_date, to_date, start, end)){
                            req.getSession().setAttribute("url_mess", "success|Thêm thành công!");
                            resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                        } else {
                            req.getSession().setAttribute("url_mess", "error|Đã có lỗi!");
                            resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                        }
                    } else {
                        req.getSession().setAttribute("url_mess", "error|Bạn đã đặt trùng lịch hoặc lịch của quá khứ!");
                        resp.sendRedirect(req.getContextPath() + "/admin/doctor-schedule-control");
                    }

                }
            }
        } else if (req.getParameter("_method").equals("delete_schedule")) {
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
                            req.getSession().setAttribute("url_mess", "success|Xóa thành công.");
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
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
