package hu.pte.mik.zhgyak3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

// A "/*" azt jelenti, hogy az alkalmazás összes oldalán és kérésénél lefut!
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // UTF-8 beállítása, hogy a magyar karakterek tökéletesek maradjanak
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Továbbengedjük a kérést a Servlethez/JSP-hez
        chain.doFilter(request, response);
    }
}