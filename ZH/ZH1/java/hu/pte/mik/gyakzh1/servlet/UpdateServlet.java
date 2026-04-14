package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.model.Phone;
import hu.pte.mik.gyakzh1.service.PhoneService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private final PhoneService phoneService = new PhoneService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            // 1. Lekérjük az adott telefont a Service-ből
            Phone phone = phoneService.findById(Long.parseLong(idString));

            // 2. Rátesszük a tálcára "phone" néven
            req.setAttribute("phone", phone);
        }

        // 3. Továbbítjuk a kérést az űrlapnak (add.jsp)
        // Látod? Itt teljesül a +2 feladat: ugyanazt a JSP-t használjuk!
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}