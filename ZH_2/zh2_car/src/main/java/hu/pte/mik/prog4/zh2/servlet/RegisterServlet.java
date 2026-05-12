package hu.pte.mik.prog4.zh2.servlet;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.pte.mik.prog4.zh2.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();

    // Ha valaki beírja az URL-t, mutassuk meg neki a formot
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    // Amikor rákattint a gombra, dolgozzuk fel az adatokat
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 1. Ellenőrzés: Foglalt-e már a felhasználónév?
        if (userRepository.findByUsername(username) != null) {
            req.setAttribute("error", "Ez a felhasználónév már foglalt!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // 2. Jelszó elforgatása BCrypt-tel (a feladatlap szerint)
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        // 3. Mentés az adatbázisba (a Repository-ban kap user role-t is)
        userRepository.registerUser(username, hashedPassword);

        // 4. Sikeres regisztráció után átirányítás a bejelentkező felületre
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}