package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    // Logger hozzáadása
    private static final Logger LOGGER = Logger.getLogger(UserRepository.class);

    public UserEntity findByUsername(String username) {
        // Nagybetűs SQL konvenció
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT ID, username, password FROM user WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // A te letisztult vezérlési logikád (early return)
            if (rs.next()) {
                UserEntity user = new UserEntity();
                // A szaktársad biztonságos, név alapú kiolvasása
                user.setId(rs.getLong("ID"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null; // Nincs szükség else ágra!

            // A te multi-catch blokkod + Logger
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a felhasználó lekérdezésekor: " + ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}