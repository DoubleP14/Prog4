package hu.pte.mik.prog4.potpotzh.servlet;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.service.CompanyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveCompanyServlet extends HttpServlet {

    private final CompanyService companyService = new CompanyService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Adatok kinyerése a formból
        String name = req.getParameter("name");
        String foundationYearStr = req.getParameter("foundationYear");
        String country = req.getParameter("country");
        String knownProduct = req.getParameter("knownProduct");

        // Entitás felépítése
        CompanyEntity company = new CompanyEntity();
        company.setName(name);
        company.setCountry(country);
        company.setKnownProduct(knownProduct);

        try {
            company.setFoundationYear(Integer.parseInt(foundationYearStr));
        } catch (NumberFormatException e) {
            company.setFoundationYear(0); // Hibás számformátum esetén
        }

        // Mentés a Service-en keresztül
        companyService.save(company);

        // Sikeres mentés után visszairányítjuk a listázó oldalra
        resp.sendRedirect(req.getContextPath() + "/company-list");
    }
}