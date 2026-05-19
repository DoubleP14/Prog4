package hu.pte.mik.prog4.zh2_2026.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Ez az annotáció mondja meg a Tomcatnek, hogy ez a Servlet figyeli a /logout címet
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. A Tomcat (JAAS) hivatalos kijelentkeztetése
        try {
            req.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        // 2. A teljes HTTP Session (munkamenet) megsemmisítése, hogy ne maradjon bejelentkezve a háttérben
        if (req.getSession(false) != null) {
            req.getSession().invalidate();
        }

        // 3. Visszairányítás a gyökér URL-re (ami a mi index.jsp-nken keresztül a loginra fog dobni)
        resp.sendRedirect(req.getContextPath() + "/");
    }
}