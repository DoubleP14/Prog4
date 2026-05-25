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

@WebServlet(urlPatterns = "/product/action")
public class ActionServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        Long id = Long.parseLong(idParam);

        if ("markFavorite".equals(action)) {
            Cookie c = new Cookie("favoriteProductId", id.toString());
            c.setMaxAge(60 * 60 * 24);
            c.setPath("/");
            resp.addCookie(c);
        } else if ("removeFavorite".equals(action)) {
            Cookie c = new Cookie("favoriteProductId", "");
            c.setMaxAge(0);
            c.setPath("/");
            resp.addCookie(c);
        } else if ("delete".equals(action)) {
            productService.delete(id);
        } else if ("exportXml".equals(action)) {
            Product p = productService.findById(id);
            if (p != null) {
                productService.convertToXml(p);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}