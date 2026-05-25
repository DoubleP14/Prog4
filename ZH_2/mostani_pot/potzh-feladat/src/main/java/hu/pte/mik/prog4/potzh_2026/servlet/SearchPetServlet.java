package hu.pte.mik.prog4.potzh_2026.servlet;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import hu.pte.mik.prog4.potzh_2026.service.PetService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/search-pet")
public class SearchPetServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Validáció 1: Hiányzik az azonosító
        if (idParam == null || idParam.trim().isEmpty()) {
            req.setAttribute("error", "Hiányzik az azonosító!");
            req.getRequestDispatcher("/search.jsp").forward(req, resp);
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            PetEntity pet = petService.findById(id);

            // Validáció 2: Nem található az állat
            if (pet == null) {
                req.setAttribute("error", "Az állat nem található!");
            } else {
                // Siker: Átadjuk az adatot a JSP-nek
                req.setAttribute("pet", pet);

                // Cookie mentése (Csak Stringet tudunk menteni, ezért az ID-t mentjük)
                Cookie lastSearchedCookie = new Cookie("lastSearchedPetId", String.valueOf(pet.getId()));
                lastSearchedCookie.setMaxAge(60 * 60 * 24); // 1 napig éljen
                resp.addCookie(lastSearchedCookie);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Az azonosítónak számnak kell lennie!");
        }

        req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }
}