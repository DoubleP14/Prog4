package hu.pte.mik.prog4.potpotzh_2026.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.logout(); // Kijelentkezteti a JAAS usert
        } catch (ServletException e) {
            e.printStackTrace();
        }
        req.getSession().invalidate(); // Munkamenet törlése

        // Visszadob a listázó oldalra, ami azonnal újra a login képernyőre irányít
        resp.sendRedirect("SearchServlet");
    }
}