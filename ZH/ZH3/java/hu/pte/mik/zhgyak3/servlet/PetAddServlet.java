package hu.pte.mik.zhgyak3.servlet;

import hu.pte.mik.zhgyak3.model.Pet;
import hu.pte.mik.zhgyak3.service.PetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Ez fogja elkapni a form POST kérését
@WebServlet("/add")
public class PetAddServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Kiszedjük az adatokat a form-ból a "name" attribútumok alapján
        String name = req.getParameter("name");
        String chipCode = req.getParameter("chipCode");
        String address = req.getParameter("address");
        String species = req.getParameter("species");

        // 2. Létrehozunk egy új Pet objektumot (a 4 paraméteres konstruktort használva, mert ID-nk még nincs)
        Pet newPet = new Pet(name, chipCode, address, species);

        // 3. Elmentjük az állatot a szerviz segítségével
        petService.save(newPet);

        // 4. Visszairányítjuk a felhasználót a listázó oldalra, ahol már látni fogja az új állatot
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}