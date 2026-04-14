package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.model.Food;
import hu.pte.mik.gyakzh1.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/Food")
public class FoodServlet extends HttpServlet {
    private final FoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. TÖRLÉS FUNKCIÓ (2. feladat)
        String deleteId = req.getParameter("deleteid");
        if (deleteId != null) {
            foodService.delete(Long.parseLong(deleteId));
            // Törlés után érdemes egy redirectet tolni, hogy ne maradjon ott az URL-ben az ID
            resp.sendRedirect(req.getContextPath() + "/Food");
            return; // Nagyon fontos: kilépünk, hogy ne fusson tovább a kód!
        }

        // 2. XML GENERÁLÁS (4. feladat)
        String xmlId = req.getParameter("xmlid");
        if (xmlId != null) {
            Long longXmlId = Long.parseLong(xmlId);
            Food foundFood = foodService.findById(longXmlId);
            if (foundFood != null) {
                // Csak simán meghívjuk! Nem kell Stringbe tenni, mert a Service magától logol!
                foodService.convertToXml(foundFood);
            }
        }

        // 3. LISTÁZÁS (1. feladat)
        List<Food> model = foodService.findAll();
        req.setAttribute("model", model);
        req.getRequestDispatcher("/food.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // KEDVENC MENTÉSE SÜTIBE (3. feladat)
        String insertCookie = req.getParameter("favouriteid");
        if (insertCookie != null) {
            Cookie cookie = new Cookie("favouriteid", URLEncoder.encode(insertCookie, StandardCharsets.UTF_8));
            // Beállíthatjuk a süti élettartamát is (pl. 1 óra)
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
        }

        resp.sendRedirect(req.getContextPath() + "/Food");
    }
}