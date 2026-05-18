package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.RoleEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUserId(Long userId) {
        List<RoleEntity> roles = new ArrayList<>();

        // INNER JOIN a magyar 'szerepkor' és 'felhasznalo_szerepkor' táblákkal
        String sql = "SELECT sz.* FROM szerepkor sz " +
                "INNER JOIN felhasznalo_szerepkor fsz ON sz.id = fsz.szerepkor_id " +
                "WHERE fsz.felhasznalo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RoleEntity role = new RoleEntity();
                    role.setId(rs.getLong("id"));
                    role.setCode(rs.getString("kod"));
                    role.setDescription(rs.getString("leiras"));
                    roles.add(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}