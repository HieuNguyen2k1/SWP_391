package Control;

import Dao.DoctorDao;
import Dao.SpecialityDao;
import Model.Doctor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

public class DoctorProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Doctor doctor = (Doctor) req.getAttribute("doctor");
        req.setAttribute("doctor", doctor);
//        req.setAttribute("speciality_list", new SpecialityDao().getAllSpeciality());
        req.getRequestDispatcher("/WEB-INF/views/doctor/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        int id = doctor.getId();
        String name = req.getParameter("name");
        String degree = req.getParameter("degree");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("DOB");
        boolean gender = false;

        if (req.getParameter("gender").equals("1")) {
            gender = true;
        }

        String address = req.getParameter("address");
        DoctorDao doctorDao = new DoctorDao();

        // Kiểm tra số điện thoại
        if (phone != null && phone.trim().matches("^0\\d{9}$")) {
            // Kiểm tra ngày tháng năm sinh
            LocalDate currentDate = LocalDate.now();
            LocalDate dobDate = LocalDate.parse(dob);

            if (dobDate.isAfter(currentDate)) {
                session.setAttribute("mess", "Ngày tháng năm sinh không hợp lệ!");
                session.setAttribute("status", "error");
                resp.sendRedirect(req.getContextPath() + "/doctor/profile");
                return;
            }
        } else {
            session.setAttribute("mess", "Số điện thoại không hợp lệ!");
            session.setAttribute("status", "error");
            resp.sendRedirect(req.getContextPath() + "/doctor/profile");
            return;
        }

        if (doctorDao.updateDoctor(name, degree, phone, dob, gender, address, id)) {
            Doctor updatedDoctor = doctorDao.findById(id);
            session.setAttribute("doctor", updatedDoctor);
            session.setAttribute("mess", "Cập nhật thông tin thành công!");
            session.setAttribute("status", "success");
            resp.sendRedirect(req.getContextPath() + "/doctor/profile");
        }
    }

}
