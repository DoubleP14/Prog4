package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.model.Phone;
import hu.pte.mik.gyakzh1.service.PhoneService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private final PhoneService phoneService = new PhoneService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Kiolvassuk az adatokat (köztük az új rejtett ID mezőt is!)
        String idString = req.getParameter("id");
        String manufacturer = req.getParameter("manufacturer");
        String type = req.getParameter("type");
        String imei = req.getParameter("imei");

        // 2. Megnézzük, érkezett-e ID. Ha igen, átalakítjuk számmá, különben null marad.
        Long id = (idString != null && !idString.isEmpty()) ? Long.parseLong(idString) : null;

        // 3. Mentünk! (A te zseniális Service-ed innentől mindent elintéz!)
        phoneService.save(id, manufacturer, type, imei);

        // 4. Vissza a listára
        resp.sendRedirect("list");
    }
}