package hu.pte.mik.zhgyak3.servlet;

import hu.pte.mik.zhgyak3.model.Pet;
import hu.pte.mik.zhgyak3.service.PetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class PetListServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lekérjük az összes állatot
        List<Pet> pets = petService.findAll();

        // 2. Betesszük a request-be, hogy a JSP lássa
        req.setAttribute("pets", pets);

        // 3. Továbbítjuk a kérést a list.jsp-nek
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}