package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserRepository extends Repository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public UserEntity findByUsername(String username) {
        try (var con = this.getConnection()) {
            var stmt = con.prepareStatement("SELECT * FROM user WHERE username = ?");
            stmt.setString(1, username);

            var resultSet = stmt.executeQuery();

            // JAVÍTÁS: Exception helyett null visszaadása, hogy a login szépen bukjon el
            if (!resultSet.next()) {
                return null;
            }

            var user = new UserEntity();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));

            // JAVÍTÁS: Az adatbázisban a mező neve szinte biztosan "password", nem "pass"
            user.setPass(resultSet.getString("password"));

            return user;
        } catch (SQLException e) {
            LOGGER.error("Data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Something happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 11. Feladat: Új felhasználó mentése és 'user' jogosultság kiosztása
    public void registerUser(String username, String hashedPassword) {
        try (var con = this.getConnection()) {
            // 1. Felhasználó beszúrása (visszakérjük a generált ID-t)
            var stmt = con.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();

            // 2. Generált ID lekérése
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long userId = rs.getLong(1);
                // 3. 'user' jogosultság hozzárendelése (a role táblából kikeresve a 'user' ID-ját)
                var roleStmt = con.prepareStatement("INSERT INTO user_role (user_id, role_id) SELECT ?, id FROM role WHERE code = 'user'");
                roleStmt.setLong(1, userId);
                roleStmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Adatbázis hiba regisztrációkor! " + e.getMessage(), e);
            throw new RuntimeException("Adatbázis hiba regisztrációkor", e);
        } catch (NamingException e) {
            LOGGER.error("Naming hiba regisztrációkor! " + e.getMessage(), e);
            throw new RuntimeException("Naming hiba regisztrációkor", e);
        }
    }
}