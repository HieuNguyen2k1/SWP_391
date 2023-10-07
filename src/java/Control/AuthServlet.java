package Control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public class AuthServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
