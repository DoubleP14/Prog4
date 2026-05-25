package hu.pte.mik.prog4.potzh_2026.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {
    public List<String> findRolesByUsername(String username) {
        List<String> roles = new ArrayList<>();
        String query = "SELECT r.code FROM role r " +
                "JOIN user_role ur ON r.id = ur.role_id " +
                "JOIN user u ON u.id = ur.user_id " +
                "WHERE u.username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("code"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}