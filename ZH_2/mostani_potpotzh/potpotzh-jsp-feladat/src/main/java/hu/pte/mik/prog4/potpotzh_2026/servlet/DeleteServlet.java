package hu.pte.mik.prog4.potpotzh_2026.servlet;

import hu.pte.mik.prog4.potpotzh_2026.ws.client.HallgatoDataService;
import hu.pte.mik.prog4.potpotzh_2026.ws.client.HallgatoWebService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        HallgatoDataService service = new HallgatoDataService();
        HallgatoWebService port = service.getHallgatoWebServicePort();

        port.deleteHallgato(id);

        resp.sendRedirect("SearchServlet");
    }
}