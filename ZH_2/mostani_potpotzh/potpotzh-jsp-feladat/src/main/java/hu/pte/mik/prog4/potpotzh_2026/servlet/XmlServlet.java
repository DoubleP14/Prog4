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

@WebServlet("/XmlServlet")
public class XmlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        HallgatoDataService service = new HallgatoDataService();
        HallgatoWebService port = service.getHallgatoWebServicePort();

        Hallgato h = port.getHallgatoById(id);

        if (h != null) {
            System.out.println("====== XML GENERÁLÁS ======");
            System.out.println("<hallgato>");
            System.out.println("  <id>" + h.getId() + "</id>");
            System.out.println("  <hallgato_nev>" + h.getHallgatoNev() + "</hallgato_nev>");
            System.out.println("  <szak>" + h.getSzak() + "</szak>");
            System.out.println("  <felev>" + h.getFelev() + "</felev>");
            System.out.println("  <egyetemi_atlag>" + h.getEgyetemiAtlag() + "</egyetemi_atlag>");
            System.out.println("</hallgato>");
            System.out.println("===========================");
        }

        resp.sendRedirect("SearchServlet");
    }
}
