package Control.Admin;

import Dao.AuthDao;
import Dao.SpecialityDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SpecialityControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SpecialityDao specialityDao = new SpecialityDao();
        req.setAttribute("speciality_list", specialityDao.getAllSpeciality());
        req.getRequestDispatcher("/WEB-INF/views/admin/speciality-control.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("_method").equalsIgnoreCase("delete")){
            this.doDelete(req, resp);
        } else {
            String name = req.getParameter("name");
            SpecialityDao specialityDao = new SpecialityDao();
            boolean check = specialityDao.createSpeciality(name);
            if (check){
                resp.sendRedirect("speciality-control");
            } else {
                req.setAttribute("message", "lỗi rồi.");
                req.getRequestDispatcher("/WEB-INF/views/admin/speciality-control.jsp").forward(req,resp);
            }
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SpecialityDao specialityDao = new SpecialityDao();
        if (specialityDao.deleteSpeciality(Integer.parseInt(req.getParameter("id")))){
            resp.sendRedirect("speciality-control");
        }
    }
}
