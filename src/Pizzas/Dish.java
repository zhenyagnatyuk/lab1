package Pizzas;

import java.math.BigDecimal;

abstract class Dish {
    private BigDecimal price;
    private int weight;

    Dish(){
        price = new BigDecimal("0");
        weight = 0;
    }

    public Dish(BigDecimal p, int w){
        price = p;
        weight = w;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
    public BigDecimal getPrice() {
        return price;
    }


}
