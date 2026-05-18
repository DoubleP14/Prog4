package hu.pte.mik.prog4.potpotzh.entity;

public class RoleEntity {
    private Long id;
    private String code;        // kod (pl. "viewer")
    private String description; // leiras

    public RoleEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}