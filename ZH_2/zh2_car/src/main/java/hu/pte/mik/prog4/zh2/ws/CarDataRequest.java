package hu.pte.mik.prog4.zh2.ws;

import java.util.Objects;

public class CarDataRequest {

    private String licensePlate;

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CarDataRequest that = (CarDataRequest) o;

        return Objects.equals(this.licensePlate, that.licensePlate);
    }

    @Override
    public int hashCode() {
        return this.licensePlate != null ? this.licensePlate.hashCode() : 0;
    }

}
