/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Control.Auth.Mail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contactEmail")
public class ContactFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form data from request parameters
        response.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("full_name");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String question = request.getParameter("question");

        // Send the contact inquiry as an email using the Mail class
        boolean emailSent = Mail.sendContactEmail(fullName, phoneNumber, email, address, question);

        if (emailSent) {
            request.setAttribute("Message", "Your inquiry has been sent successfully.");
            // Email sent successfully, you can redirect to a confirmation page
            request.getRequestDispatcher("/WEB-INF/views/confirmContact.jsp").forward(request, response);
        } else {
            // Handle email sending failure, e.g., show an error message
            request.setAttribute("errorMessage", "Email sending failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/errorContact.jsp").forward(request, response);
        }
    }
}
