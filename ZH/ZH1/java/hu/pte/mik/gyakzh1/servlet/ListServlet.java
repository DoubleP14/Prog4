package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.model.Phone;
import hu.pte.mik.gyakzh1.service.PhoneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// Ez mondja meg a Tomcatnek, hogy ez a Servlet a "/list" URL-en legyen elérhető
@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private final PhoneService phoneService = new PhoneService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lekérjük a telefonokat a Service-ből
        List<Phone> phones = phoneService.findAll();

        // 2. Berakjuk a kérésbe "phones" néven, hogy a JSP lássa
        req.setAttribute("phones", phones);

        // 3. Továbbítjuk a kérést a list.jsp fájlnak
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
