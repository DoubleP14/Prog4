package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddFood")
public class AddFoodServlet extends HttpServlet {

    // A Servlet is a Service-t használja!
    private final FoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Ha csak megnyitják az oldalt, továbbítjuk őket a JSP űrlapra
        req.getRequestDispatcher("/addFood.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiolvassuk az űrlap mezőit
        String name = req.getParameter("name");
        String restaurant = req.getParameter("restaurant");
        String price = req.getParameter("price");

        // Létrehozzuk az új ételt az adatbázisban
        foodService.save(restaurant, name, price);

        // Visszairányítjuk a felhasználót a listázó oldalra
        resp.sendRedirect(req.getContextPath() + "/Food");
    }
}