package hu.pte.mik.gyakzh1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Megkapjuk az ID-t az URL-ből (pl. ?id=2)
        String idString = req.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            // 2. Létrehozzuk a sütit!
            // Az első paraméter a süti NEVE, a második az ÉRTÉKE (maga az ID)
            Cookie favoriteCookie = new Cookie("favoritePhoneId", idString);

            // 3. Beállítjuk, meddig éljen a süti másodpercben (pl. 1 óra = 60 * 60)
            favoriteCookie.setMaxAge(60 * 60);

            // 4. Rátesszük a sütit a válaszra, hogy a böngésző elmentse
            resp.addCookie(favoriteCookie);
        }

        // 5. Visszadobjuk a felhasználót a listázó oldalra
        resp.sendRedirect("list");
    }
}