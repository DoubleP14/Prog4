package hu.pte.mik.prog4.zh1_2026.model;

import hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Element;
import hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Serializable;

import java.util.Objects;

@ZH12026Serializable
public class Product {

    @ZH12026Element
    private Long id;
    @ZH12026Element
    private String name;
    @ZH12026Element
    private String price;
    @ZH12026Element(name = "SHORT_DESCRIPTION")
    private String description;

    public Product() {
    }

    public Product(Long id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name,
                product.name) && Objects.equals(price, product.price) && Objects.equals(description,
                product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
