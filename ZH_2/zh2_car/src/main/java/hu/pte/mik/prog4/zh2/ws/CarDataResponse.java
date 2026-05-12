package hu.pte.mik.prog4.zh2.ws;

import java.util.Objects;

public class CarDataResponse {

    public CarDataResponse() {
    }

    public CarDataResponse(String licensePlate, Long kilometer) {
        this.licensePlate = licensePlate;
        this.kilometer = kilometer;
    }

    private String licensePlate;
    private Long kilometer;

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getKilometer() {
        return this.kilometer;
    }

    public void setKilometer(Long kilometer) {
        this.kilometer = kilometer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CarDataResponse carData = (CarDataResponse) o;

        if (!Objects.equals(this.licensePlate, carData.licensePlate))
            return false;
        return Objects.equals(this.kilometer, carData.kilometer);
    }

    @Override
    public int hashCode() {
        int result = this.licensePlate != null ? this.licensePlate.hashCode() : 0;
        result = 31 * result + (this.kilometer != null ? this.kilometer.hashCode() : 0);
        return result;
    }

}
