package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    private final static Logger LOGGER = Logger.getLogger(RoleRepository.class);

    public List<RoleEntity> findRolesByUser(UserEntity userEntity) {
        // A TE optimalizált SQL lekérdezésed (nincs felesleges user JOIN!)
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT r.ID, r.code, r.description FROM role r INNER JOIN user_role ur ON r.ID = ur.role_id WHERE ur.user_id = ?")) {

            stmt.setLong(1, userEntity.getId());
            List<RoleEntity> roles = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();

            // while ciklus, ahogy helyesen írtad!
            while (rs.next()) {
                RoleEntity role = new RoleEntity();
                // A szaktársad oszlopnév-alapú biztonságos kiolvasása
                role.setId(rs.getLong("ID"));
                role.setCode(rs.getString("code"));
                role.setDescription(rs.getString("description"));
                roles.add(role);
            }
            return roles;

            // A TE letisztult multi-catch blokkod + a Logger
        } catch (SQLException | NamingException ex) {
            LOGGER.error("Hiba a Role-ok lekérdezésekor: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}