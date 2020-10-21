package Pizzas;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private int weight;
    private BigDecimal price;

    public Product() {
        name = "";
        weight = 0;
        price = new BigDecimal("0");
    }

    public Product(String n, int w, int p){
        name = n;
        weight = w;
        price = new BigDecimal(p);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return weight == product.weight &&
                Objects.equals(name, product.name) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price);
    }
}
