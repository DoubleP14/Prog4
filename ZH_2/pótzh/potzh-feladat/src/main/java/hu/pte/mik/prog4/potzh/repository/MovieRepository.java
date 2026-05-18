package hu.pte.mik.prog4.potzh.repository;

import hu.pte.mik.prog4.potzh.entity.MovieEntity;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository extends Repository {

    public List<MovieEntity> findAll() {
        List<MovieEntity> movies = new ArrayList<>();
        String sql = "SELECT id, title, director_name, release_year, genre FROM movie";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("Hiba a filmek lekérdezésekor", e);
        }
        return movies;
    }

    public MovieEntity findById(Long id) {
        String sql = "SELECT id, title, director_name, release_year, genre FROM movie WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMovie(rs);
                }
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("Hiba a film lekérdezésekor", e);
        }
        return null;
    }

    public MovieEntity create(MovieEntity movie) {
        String sql = "INSERT INTO movie (title, director_name, release_year, genre) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirectorName());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setString(4, movie.getGenre());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    movie.setId(rs.getLong(1));
                }
            }
            return movie;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("Hiba a film mentésekor", e);
        }
    }

    public MovieEntity update(MovieEntity movie) {
        String sql = "UPDATE movie SET title = ?, director_name = ?, release_year = ?, genre = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirectorName());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setString(4, movie.getGenre());
            stmt.setLong(5, movie.getId());

            stmt.executeUpdate();
            return movie;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException("Hiba a film frissítésekor", e);
        }
    }

    // Segédmetódus a sorok beolvasásához (Itt fizetődik ki az előbb megírt Konstruktor!)
    private MovieEntity mapResultSetToMovie(ResultSet rs) throws SQLException {
        return new MovieEntity(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("director_name"),
                rs.getInt("release_year"),
                rs.getString("genre")
        );
    }
}