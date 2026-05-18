package hu.pte.mik.prog4.zh2_2026.entity;

import java.util.Objects;

public class CarEntity {
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

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    private Long id;
    private String type;
    private String model;
    private String productionYear;
    private String listPrice;

    public CarEntity(Long id, String type, String model, String productionYear, String listPrice) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.productionYear = productionYear;
        this.listPrice = listPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(id, carEntity.id) && Objects.equals(type,
                carEntity.type) && Objects.equals(model, carEntity.model) && Objects.equals(
                productionYear, carEntity.productionYear) && Objects.equals(listPrice, carEntity.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, model, productionYear, listPrice);
    }
}
