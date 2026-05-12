package hu.pte.mik.prog4.zh2.entity;

import java.util.Objects;

public class UserEntity {

    private Long id;
    private String username;
    private String pass;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(this.id, that.id)) return false;
        if (!Objects.equals(this.username, that.username)) return false;
        return Objects.equals(this.pass, that.pass);
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.username != null ? this.username.hashCode() : 0);
        result = 31 * result + (this.pass != null ? this.pass.hashCode() : 0);
        return result;
    }

}
