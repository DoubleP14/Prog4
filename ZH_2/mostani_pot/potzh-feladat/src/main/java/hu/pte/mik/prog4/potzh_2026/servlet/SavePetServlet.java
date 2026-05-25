package hu.pte.mik.prog4.potzh_2026.servlet;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import hu.pte.mik.prog4.potzh_2026.service.PetService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/save-pet")
public class SavePetServlet extends HttpServlet {

    private final PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            // Adatok kiolvasása a formból
            String petName = req.getParameter("petName");
            String species = req.getParameter("species");
            int age = Integer.parseInt(req.getParameter("age"));
            String ownerName = req.getParameter("ownerName");

            // Entitás létrehozása és feltöltése
            PetEntity pet = new PetEntity();
            pet.setPetName(petName);
            pet.setSpecies(species);
            pet.setAge(age);
            pet.setOwnerName(ownerName);

            // Mentés a service rétegen keresztül
            petService.save(pet);

            // Sikeres mentés után üzenet beállítása és visszairányítás a formra
            req.setAttribute("message", "Sikeres mentés!");
            req.getRequestDispatcher("/save.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Hiba történt a mentés során!");
            req.getRequestDispatcher("/save.jsp").forward(req, resp);
        }
    }
}