package hu.pte.mik.prog4.potzh_2026.entity;

import java.util.Objects;

public class PetEntity {

    private Long id;
    private String petName;
    private String species ;
    private int age;
    private String ownerName;

    public PetEntity() {
    }

    public PetEntity(Long id, String petName, String species, int age, String ownerName) {
        this.id = id;
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetEntity petEntity = (PetEntity) o;
        return age == petEntity.age && Objects.equals(id, petEntity.id) && Objects.equals(petName, petEntity.petName) && Objects.equals(species, petEntity.species) && Objects.equals(ownerName, petEntity.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petName, species, age, ownerName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
