package hu.pte.mik.prog4.potpotzh.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // JAAS kijelentkezés
        req.logout();

        // A munkamenet (session) megsemmisítése
        req.getSession().invalidate();

        // Visszairányítás a kezdőlapra (vagy a listára, ami majd újra kéri a logint)
        resp.sendRedirect(req.getContextPath() + "/company-list");
    }
}