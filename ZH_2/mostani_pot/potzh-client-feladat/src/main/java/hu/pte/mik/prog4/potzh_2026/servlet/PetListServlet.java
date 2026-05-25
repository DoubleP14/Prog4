package hu.pte.mik.prog4.potzh_2026.servlet;

import hu.pte.mik.prog4.potzh_2026.client.PetDataService;
import hu.pte.mik.prog4.potzh_2026.client.SoapPetDataService;
import hu.pte.mik.prog4.potzh_2026.client.PetEntity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Ez a végpont fogadja a kéréseket, akár egyből a gyökérkönyvtáron is
@WebServlet(urlPatterns = "")
public class PetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Megmondjuk a böngészőnek, hogy HTML-t küldünk UTF-8 kódolással
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Állatok Listája</title></head>");
        out.println("<body style='font-family: Arial; padding: 20px;'>");
        out.println("<h2>Nyilvántartott Állatok Listája (Webservice-ből)</h2>");

        try {
            // 1. Kapcsolódás a másik alkalmazás SOAP webszolgáltatásához
            PetDataService service = new PetDataService();
            SoapPetDataService port = service.getSoapPetDataServicePort();

            // 2. Adatok lekérdezése
            List<PetEntity> pets = port.getAllPets();

            // 3. HTML Táblázat generálása
            if (pets == null || pets.isEmpty()) {
                out.println("<p>Nincs egyetlen rögzített állat sem.</p>");
            } else {
                out.println("<table border='1' cellpadding='8' style='border-collapse: collapse;'>");
                out.println("<tr style='background-color: #f2f2f2;'>");
                out.println("<th>ID</th><th>Név</th><th>Fajta</th><th>Kor</th><th>Gazda neve</th>");
                out.println("</tr>");

                // Végigmegyünk a listán és sorokat (tr), illetve cellákat (td) generálunk
                for (PetEntity pet : pets) {
                    out.println("<tr>");
                    out.println("<td>" + pet.getId() + "</td>");
                    out.println("<td>" + pet.getPetName() + "</td>");
                    out.println("<td>" + pet.getSpecies() + "</td>");
                    out.println("<td>" + pet.getAge() + "</td>");
                    out.println("<td>" + pet.getOwnerName() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color: red;'>Hiba történt az adatok lekérésekor a webservice-től: " + e.getMessage() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}