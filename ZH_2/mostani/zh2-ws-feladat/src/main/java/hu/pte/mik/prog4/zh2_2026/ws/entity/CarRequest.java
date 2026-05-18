package hu.pte.mik.prog4.zh2_2026.ws.entity;

import java.util.Objects;

public class CarRequest {

    private String type;
    private String model;
    private String productionYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarRequest that = (CarRequest) o;
        return Objects.equals(type, that.type) && Objects.equals(model,
                that.model) && Objects.equals(productionYear, that.productionYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, model, productionYear);
    }
}
