package hu.pte.mik.prog4.potpotzh.entity;

public class UserEntity {
    private Long id;
    private String username; // felhasznalonev
    private String password; // jelszo

    public UserEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}