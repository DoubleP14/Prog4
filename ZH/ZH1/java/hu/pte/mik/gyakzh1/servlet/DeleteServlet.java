package hu.pte.mik.gyakzh1.servlet;

import hu.pte.mik.gyakzh1.service.PhoneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private final PhoneService phoneService = new PhoneService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Olvassuk ki a böngésző URL-jéből a küldött számot (az "id" paramétert)
        String idString = req.getParameter("id");

        // 2. Ha kaptunk ID-t, töröljük a telefont
        if (idString != null && !idString.isEmpty()) {
            // Mivel a weboldalról minden szövegként (String) érkezik, át kell alakítanunk Long számmá!
            Long id = Long.parseLong(idString);
            phoneService.delete(id);
        }

        // 3. Az akció után "dobjuk vissza" a felhasználót a listázó oldalra!
        resp.sendRedirect("list");
    }
}
