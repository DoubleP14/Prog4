package hu.pte.mik.prog4.zh2_2026.repository;

import hu.pte.mik.prog4.zh2_2026.entity.RoleEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUserId(Long userId) {
        List<RoleEntity> roles = new ArrayList<>();
        // Feltételezem, hogy van egy kapcsolótáblád (pl. user_role) és egy role táblád
        String sql = "SELECT r.* FROM role r " +
                "INNER JOIN user_role ur ON r.id = ur.role_id " +
                "WHERE ur.user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RoleEntity role = new RoleEntity();
                    role.setId(rs.getLong("id"));
                    role.setCode(rs.getString("code")); // Itt lesz a "user" vagy "dealer"
                    roles.add(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}