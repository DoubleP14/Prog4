package hu.pte.mik.prog4.zh2.entity;

import java.util.Objects;

public class RoleEntity {

    private Long id;
    private String code;
    private String description;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (!Objects.equals(this.id, that.id)) return false;
        if (!Objects.equals(this.code, that.code)) return false;
        return Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.code != null ? this.code.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }
}
