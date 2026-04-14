package hu.pte.mik.zhgyak3.servlet;

import hu.pte.mik.zhgyak3.service.PetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Figyelj rá, hogy ez pontosan megegyezzen a JSP-ben lévő action="..." link végével!
@WebServlet("/delete")
public class PetDeleteServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Kiszedjük az ID-t a formból
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                // Szövegből számmá alakítjuk (Long)
                Long id = Long.parseLong(idParam);

                // 2. Meghívjuk a szerviz törlő metódusát, ahogy a feladat kéri
                petService.deletePet(id);

            } catch (NumberFormatException e) {
                // Ha valaki rossz ID-t küldene be, ne haljon le a szerver
                e.printStackTrace();
            }
        }

        // 3. Visszairányítjuk a felhasználót a listázó oldalra
        // A sendRedirect csinál egy új GET kérést a /list URL-re,
        // így a táblázat újra betöltődik, de a törölt állat már nem lesz ott.
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}