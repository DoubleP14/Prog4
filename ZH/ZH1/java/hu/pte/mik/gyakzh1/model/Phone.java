package hu.pte.mik.gyakzh1.model;

import java.util.Objects;

public class Phone {

    private Long id;
    private String manufacturer;
    private String type;
    private String imei;

    public Phone(Long id, String manufacturer, String type, String imei) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.imei = imei;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(this.id, phone.id) && Objects.equals(this.manufacturer, phone.manufacturer) && Objects.equals(this.type, phone.type) && Objects.equals(this.imei, phone.imei);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.manufacturer, this.type, this.imei);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + this.id +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", type='" + this.type + '\'' +
                ", imei='" + this.imei + '\'' +
                '}';
    }
}
