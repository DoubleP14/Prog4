package hu.pte.mik.prog4.zh2_2026.ws.entity;

import java.util.Objects;

public class CarResponse {

    private Long soldCount;
    private Long estimatedPrice;
    private String fuelType;

    public Long getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Long soldCount) {
        this.soldCount = soldCount;
    }

    public Long getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Long estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarResponse that = (CarResponse) o;
        return Objects.equals(soldCount, that.soldCount) && Objects.equals(estimatedPrice,
                that.estimatedPrice) && Objects.equals(fuelType, that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soldCount, estimatedPrice, fuelType);
    }
}
