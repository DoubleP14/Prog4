package hu.pte.mik.prog4.potpotzh_2026.servlet;

import hu.pte.mik.prog4.potpotzh_2026.entity.Hallgato;
import hu.pte.mik.prog4.potpotzh_2026.service.HallgatoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list")
public class HallgatoListServlet extends HttpServlet {
    private HallgatoService service = new HallgatoService(); // Ne felejtsd el a service réteget létrehozni!

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<Hallgato> hallgatok = service.getAllHallgatok(); // Ez hívja a repo.findAllOrderByName() metódusát

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><head><title>Hallgatók Listája</title></head><body>");
            out.println("<h1>Hallgatók (ABC sorrendben)</h1>");
            out.println("<table border='1'><tr><th>ID</th><th>Név</th><th>Szak</th><th>Félév</th><th>Átlag</th></tr>");

            for (Hallgato h : hallgatok) {
                out.println("<tr>");
                out.println("<td>" + h.getId() + "</td>");
                out.println("<td>" + h.getHallgatoNev() + "</td>");
                out.println("<td>" + h.getSzak() + "</td>");
                out.println("<td>" + h.getFelev() + "</td>");
                out.println("<td>" + h.getEgyetemiAtlag() + "</td>");
                out.println("</tr>");
            }

            out.println("</table></body></html>");
        }
    }
}