package hu.pte.mik.gyakzh1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class FoodFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 1. Karakterkódolás beállítása (A PDF bevezetője kéri a magyar karakterek miatt!)
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        // 2. Csak akkor vizsgálódunk, ha kifejezetten az Új Étel (/AddFood) URL-re jön POST kérés
        if (req.getRequestURI().endsWith("/AddFood") && req.getMethod().equalsIgnoreCase("POST")) {

            // Lekérjük a beküldött étel nevét az űrlapról
            String foodName = req.getParameter("name");

            // 3. Blokkolás: Ha a név null (nem küldték el) VAGY üres (csak szóközöket tartalmaz a trim() után)
            if (foodName == null || foodName.trim().isEmpty()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Az étel neve nem lehet üres!");
                return; // Kilépünk a filterből, NEM engedjük tovább a láncot!
            }
        }

        // 4. Minden más kérést (vagy ha a név rendben ki volt töltve) szépen átengedünk
        super.doFilter(req, res, chain);
    }
}