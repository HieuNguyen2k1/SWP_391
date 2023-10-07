package Filter;

import Model.Doctor;
import Model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoctorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Doctor doctor= (Doctor) request.getSession().getAttribute("doctor");
        if (doctor!=null){
            filterChain.doFilter(request, response);
        } else {
            request.getSession().setAttribute("login_mess", "Vui lòng đăng nhập.");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
      @Override 
    public void init(FilterConfig fc) throws  ServletException{
        
    }
    @Override
    public void destroy(){
        
    }
}
