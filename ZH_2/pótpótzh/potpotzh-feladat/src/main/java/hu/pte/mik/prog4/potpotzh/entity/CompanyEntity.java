package hu.pte.mik.prog4.potpotzh.entity;

public class CompanyEntity {
    private Long id;
    private String name;           // nev
    private Integer foundationYear;// alapitasi_ev
    private String country;        // orszag
    private String knownProduct;   // ismert_termek

    public CompanyEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getFoundationYear() { return foundationYear; }
    public void setFoundationYear(Integer foundationYear) { this.foundationYear = foundationYear; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getKnownProduct() { return knownProduct; }
    public void setKnownProduct(String knownProduct) { this.knownProduct = knownProduct; }
}