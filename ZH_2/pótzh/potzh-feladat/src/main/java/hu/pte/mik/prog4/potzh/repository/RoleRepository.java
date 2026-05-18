package hu.pte.mik.prog4.potzh.repository;

import hu.pte.mik.prog4.potzh.entity.RoleEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUserId(Long userId) {
        List<RoleEntity> roles = new ArrayList<>();
        // Ez a lekérdezés feltételezi, hogy a szerepkörök táblájában van egy user_id oszlop
        String sql = "SELECT r.* FROM role r INNER JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RoleEntity role = new RoleEntity();
                    role.setId(rs.getLong("id"));

                    // JAVÍTVA: A setCode-ot használjuk!
                    // (Feltételezve, hogy az adatbázisban is 'code' az oszlop neve. Ha más, írd át az rs.getString() belsejét!)
                    role.setCode(rs.getString("code"));

                    // Opcionális: a leírást is betölthetjük
                    role.setDescription(rs.getString("description"));

                    roles.add(role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}