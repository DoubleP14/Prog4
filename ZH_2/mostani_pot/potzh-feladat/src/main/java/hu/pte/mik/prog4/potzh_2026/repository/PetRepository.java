package hu.pte.mik.prog4.potzh_2026.repository;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetRepository extends Repository {

    public List<PetEntity> findAll() {
        List<PetEntity> pets = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pet")) {
            while (rs.next()) {
                pets.add(mapToEntity(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pets;
    }

    public PetEntity findById(Long id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM pet WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapToEntity(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(PetEntity pet) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO pet (pet_name, species, age, owner_name) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getSpecies());
            ps.setInt(3, pet.getAge());
            ps.setString(4, pet.getOwnerName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(PetEntity pet) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE pet SET pet_name=?, species=?, age=?, owner_name=? WHERE id=?")) {
            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getSpecies());
            ps.setInt(3, pet.getAge());
            ps.setString(4, pet.getOwnerName());
            ps.setLong(5, pet.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PetEntity mapToEntity(ResultSet rs) throws SQLException {
        PetEntity pet = new PetEntity();
        pet.setId(rs.getLong("ID"));
        pet.setPetName(rs.getString("pet_name"));
        pet.setSpecies(rs.getString("species"));
        pet.setAge(rs.getInt("age"));
        pet.setOwnerName(rs.getString("owner_name"));
        return pet;
    }
}