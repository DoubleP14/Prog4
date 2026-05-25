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

@WebServlet(urlPatterns = "/recent-pet")
public class RecentPetServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastSearchedId = null;

        // 1. Megkeressük a megfelelő sütit a kérésben
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lastSearchedPetId".equals(cookie.getName())) {
                    lastSearchedId = cookie.getValue();
                    break;
                }
            }
        }

        // 2. Ha nincs süti, hibaüzenetet küldünk
        if (lastSearchedId == null) {
            req.setAttribute("message", "Még nem történt keresés.");
        } else {
            // 3. Ha van süti, lekérdezzük a DB-ből az ID alapján
            PetEntity recentPet = petService.findById(Long.parseLong(lastSearchedId));

            if (recentPet != null) {
                // Átadjuk a JSP-nek (amit a recent.tag fog felhasználni!)
                req.setAttribute("recentPet", recentPet);
            } else {
                req.setAttribute("message", "A korábban keresett állat már nem található az adatbázisban.");
            }
        }

        // Visszairányítjuk a kereső oldalra
        req.getRequestDispatcher("/search.jsp").forward(req, resp);
    }
}