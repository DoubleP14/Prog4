package hu.pte.mik.prog4.potzh.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kijelentkezteti a JAAS usert
        req.logout();

        // Törli a HTTP munkamenetet
        req.getSession().invalidate();

        // Visszairányít a kezdőlapra
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}