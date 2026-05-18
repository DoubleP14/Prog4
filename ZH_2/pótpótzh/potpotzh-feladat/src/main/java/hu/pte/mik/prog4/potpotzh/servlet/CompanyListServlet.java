package hu.pte.mik.prog4.potpotzh.servlet;

import hu.pte.mik.prog4.potpotzh.service.CompanyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CompanyListServlet extends HttpServlet {

    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lekérjük a cégeket és betesszük a request-be
        req.setAttribute("companies", companyService.findAll());

        // Továbbítjuk a JSP oldalnak
        req.getRequestDispatcher("/companyList.jsp").forward(req, resp);
    }
}