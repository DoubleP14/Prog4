package hu.pte.mik.prog4.zh2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// JAVÍTÁS: Bekötjük a /logout útvonalra az annotációval
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Töröljük a HTTP Session-t, ha létezik
        if (req.getSession(false) != null) {
            req.getSession(false).invalidate();
        }

        // 2. Szabványos JAAS kijelentkezés
        req.logout();

        // 3. Átirányítás a context path-re (főoldalra), ahogy a feladat kéri
        resp.sendRedirect(req.getContextPath() + "/");
    }

}