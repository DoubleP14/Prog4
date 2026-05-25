package hu.pte.mik.prog4.zh1_2026.servlet;

import hu.pte.mik.prog4.zh1_2026.model.Product;
import hu.pte.mik.prog4.zh1_2026.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"", "/products"})
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.findAll());

        // Kedvenc azonosítása cookie-ból (Ne hívjunk servicet a tagből szabály miatt)
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("favoriteProductId".equals(c.getName())) {
                    try {
                        Product fav = productService.findById(Long.parseLong(c.getValue()));
                        req.setAttribute("favoriteProduct", fav);
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
    }
}