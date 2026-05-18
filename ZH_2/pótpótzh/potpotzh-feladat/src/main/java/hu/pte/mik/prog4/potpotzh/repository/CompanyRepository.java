package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository extends Repository {

    public List<CompanyEntity> findAll() {
        List<CompanyEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM technologiai_ceg";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CompanyEntity company = new CompanyEntity();
                company.setId(rs.getLong("id"));
                company.setName(rs.getString("nev"));
                company.setFoundationYear(rs.getInt("alapitasi_ev"));
                company.setCountry(rs.getString("orszag"));
                company.setKnownProduct(rs.getString("ismert_termek"));
                list.add(company);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public CompanyEntity findById(Long id) {
        String sql = "SELECT * FROM technologiai_ceg WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CompanyEntity company = new CompanyEntity();
                    company.setId(rs.getLong("id"));
                    company.setName(rs.getString("nev"));
                    company.setFoundationYear(rs.getInt("alapitasi_ev"));
                    company.setCountry(rs.getString("orszag"));
                    company.setKnownProduct(rs.getString("ismert_termek"));
                    return company;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CompanyEntity save(CompanyEntity company) {
        String sql = "INSERT INTO technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, company.getName());
            ps.setInt(2, company.getFoundationYear());
            ps.setString(3, company.getCountry());
            ps.setString(4, company.getKnownProduct());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    company.setId(rs.getLong(1));
                }
            }
            return company;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}