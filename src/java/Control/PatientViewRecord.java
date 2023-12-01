/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Dao.MedicineDao;
import Dao.NewsDao;
import Dao.PatientDao;
import Dao.PrescriptionDao;
import Dao.RecorDAO;
import Model.Data;
import Model.News;
import Model.Prescription;
import Model.Record;
import Model.User;
import Model.medicine;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class PatientViewRecord extends HttpServlet {

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
            out.println("<title>Servlet PatientViewRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PatientViewRecord at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id_patient = Integer.parseInt(request.getParameter("id_patient"));
            //  Lấy pre
            int id_pre = Integer.parseInt(request.getParameter("id_pre"));
             // Lấy toa
            PrescriptionDao predao = new PrescriptionDao();
            Prescription pre = new Prescription();
            pre = predao.getDetail(id_pre);
            request.setAttribute("pre", pre);
            // Lấy thuốc
            MedicineDao medicinedao = new MedicineDao();
            ArrayList<medicine> medicine = new ArrayList<>();
            medicine = medicinedao.getMedicineDetail(id_pre);
           request.setAttribute("medicine", medicine);
            //===================================
            int app_id = Integer.parseInt(request.getParameter("id_app"));
            RecorDAO recordD = new RecorDAO();
            Record record = new Record();
            record = recordD.getRecord(id_patient, app_id);
            PatientDao p = new PatientDao();
            User patient = new User();
            patient = p.getPatientById(id_patient);
            request.setAttribute("patient", patient);
            request.setAttribute("record", record);

            request.getRequestDispatcher("/WEB-INF/views/viewRecord.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
