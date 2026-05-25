package hu.pte.mik.prog4.zh1_2026.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/product/create")
public class ProductValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            String description = req.getParameter("description");
            if (description == null || description.trim().length() < 10) {
                resp.sendError(422, "Unprocessable Entity: A leírásnak legalább 10 karakternek kell lennie!");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}