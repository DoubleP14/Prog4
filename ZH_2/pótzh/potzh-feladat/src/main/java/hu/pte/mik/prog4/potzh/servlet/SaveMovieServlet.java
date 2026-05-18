package hu.pte.mik.prog4.potzh.servlet;

import hu.pte.mik.prog4.potzh.entity.MovieEntity;
import hu.pte.mik.prog4.potzh.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveMovieServlet extends HttpServlet {
    private final MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/saveMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Átvettük a szaktársadtól: Ékezetek védelme!
        req.setCharacterEncoding("UTF-8");

        var title = req.getParameter("title");
        var directorName = req.getParameter("directorName");
        var releaseYear = req.getParameter("releaseYear");
        var genre = req.getParameter("genre");

        // Használjuk a te saját Entitásodat és Service metódusodat!
        MovieEntity movie = new MovieEntity();
        movie.setTitle(title);
        movie.setDirectorName(directorName);
        movie.setReleaseYear(Integer.parseInt(releaseYear));
        movie.setGenre(genre);

        this.movieService.createMovie(movie); // A te metódusneved!

        // Sikeres mentés után irány a lista
        resp.sendRedirect("movie-list");
    }
}