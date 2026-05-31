package hu.pte.mik.prog4.potpotzh_2026.entity;

import java.util.Objects;

public class Hallgato {

    private Integer id;
    private String hallgatoNev;
    private String szak;
    private Integer felev;
    private Double egyetemiAtlag;

    public Hallgato() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hallgato hallgato = (Hallgato) o;
        return Objects.equals(id, hallgato.id) && Objects.equals(hallgatoNev, hallgato.hallgatoNev) && Objects.equals(szak, hallgato.szak) && Objects.equals(felev, hallgato.felev) && Objects.equals(egyetemiAtlag, hallgato.egyetemiAtlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hallgatoNev, szak, felev, egyetemiAtlag);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHallgatoNev() {
        return hallgatoNev;
    }

    public void setHallgatoNev(String hallgatoNev) {
        this.hallgatoNev = hallgatoNev;
    }

    public String getSzak() {
        return szak;
    }

    public void setSzak(String szak) {
        this.szak = szak;
    }

    public Integer getFelev() {
        return felev;
    }

    public void setFelev(Integer felev) {
        this.felev = felev;
    }

    public Double getEgyetemiAtlag() {
        return egyetemiAtlag;
    }

    public void setEgyetemiAtlag(Double egyetemiAtlag) {
        this.egyetemiAtlag = egyetemiAtlag;
    }

    public Hallgato(Integer id, String hallgatoNev, String szak, Integer felev, Double egyetemiAtlag) {
        this.id = id;
        this.hallgatoNev = hallgatoNev;
        this.szak = szak;
        this.felev = felev;
        this.egyetemiAtlag = egyetemiAtlag;
    }
}
