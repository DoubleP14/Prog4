package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepository.class);
    private static final String FIND_BY_USER_QUERY = "SELECT r.id, r.code, r.description FROM role r JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = ?";

    public List<RoleEntity> findRolesByUser(UserEntity userEntity) {
        try (var con = this.getConnection()) {
            var stmt = con.prepareStatement(FIND_BY_USER_QUERY);
            stmt.setLong(1, userEntity.getId());

            var rs = stmt.executeQuery();
            List<RoleEntity> roles = new ArrayList<>();
            while (rs.next()) {
                var role = new RoleEntity();
                role.setId(rs.getLong("id"));
                role.setCode(rs.getString("code"));
                role.setDescription(rs.getString("description"));
                roles.add(role);
            }

            return roles;
        } catch (SQLException e) {
            LOGGER.error("Some data access error happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Some serious shit happened! " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
