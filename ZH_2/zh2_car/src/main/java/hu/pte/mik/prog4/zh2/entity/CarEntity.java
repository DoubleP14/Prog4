package hu.pte.mik.prog4.zh2.entity;

import java.util.Objects;

public class CarEntity {

    private Long id;
    private String manufacturer;
    private String type;
    private String licensePlate;

    public CarEntity() {
    }

    public CarEntity(Long id, String manufacturer, String type, String licensePlate) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.licensePlate = licensePlate;
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

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + this.id +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", type='" + this.type + '\'' +
                ", licensePlate='" + this.licensePlate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CarEntity car = (CarEntity) o;

        if (!Objects.equals(this.id, car.id)) return false;
        if (!Objects.equals(this.manufacturer, car.manufacturer)) return false;
        if (!Objects.equals(this.type, car.type)) return false;
        return Objects.equals(this.licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.manufacturer != null ? this.manufacturer.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        result = 31 * result + (this.licensePlate != null ? this.licensePlate.hashCode() : 0);
        return result;
    }

}
