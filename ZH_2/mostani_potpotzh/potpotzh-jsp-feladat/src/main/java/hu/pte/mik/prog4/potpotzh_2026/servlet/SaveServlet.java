package hu.pte.mik.prog4.potpotzh_2026.servlet;

import hu.pte.mik.prog4.potpotzh_2026.ws.client.Hallgato;
import hu.pte.mik.prog4.potpotzh_2026.ws.client.HallgatoDataService;
import hu.pte.mik.prog4.potpotzh_2026.ws.client.HallgatoWebService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Hallgato h = new Hallgato();
        h.setHallgatoNev(req.getParameter("nev"));
        h.setSzak(req.getParameter("szak"));
        h.setFelev(Integer.parseInt(req.getParameter("felev")));
        h.setEgyetemiAtlag(Double.parseDouble(req.getParameter("atlag")));

        HallgatoDataService service = new HallgatoDataService();
        HallgatoWebService port = service.getHallgatoWebServicePort();

        port.createHallgato(h);

        // Mentés után visszairányítjuk a listázó oldalra
        resp.sendRedirect("SearchServlet");
    }
}