package Filter;

import Model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("acc");
        if (user != null){
            filterChain.doFilter(request, response);
        } else {
            request.getSession().setAttribute("login_mess", "Vui lòng đăng nhập.");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
 
    /**
     *
     * @param fc
     * @throws ServletException
     */
    @Override 
    public void init(FilterConfig fc) throws  ServletException{
        // Khoi tao cac tai nguyen cua filter
    }
    @Override
    public void destroy(){
        //giai phong tai nguyen cua filter
    }
}
