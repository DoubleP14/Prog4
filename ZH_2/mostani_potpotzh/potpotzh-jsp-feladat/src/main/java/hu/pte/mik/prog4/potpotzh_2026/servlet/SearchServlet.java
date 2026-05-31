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
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Webservice hívás a kliensen keresztül
        HallgatoDataService service = new HallgatoDataService();
        HallgatoWebService port = service.getHallgatoWebServicePort();

        List<Hallgato> list = port.getHallgatok();

        // Adatok átadása a JSP-nek
        req.setAttribute("hallgatok", list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}