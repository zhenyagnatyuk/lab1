package Pizzas;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Check {
    private ArrayList<Pizza> pizzas;
    private Human buyer;
    private StringBuilder check;
    private BigDecimal total_price;
    public Check() {
        pizzas = new ArrayList<>();
        buyer = new Human();
        check = new StringBuilder();
    }
    public Check(ArrayList<Pizza> p, Human b) {
        pizzas = p;
        buyer = b;
        BigDecimal price = new BigDecimal(0);
        check = new StringBuilder("Buyer: ");
        check.append(buyer.getName());
        check.append(" ");
        check.append(buyer.getSurname());
        check.append("\n");
        for (Pizza pizza : pizzas){
            check.append(pizza.toString());
            price = pizza.getPrice().add(price);
        }
        total_price = price;
        check.append("Total price: ");
        check.append(price);
        check.append("\n");

    }

    public void setBuyer(Human b) {
        this.buyer = b;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }


    public Human getBuyer() {
        return buyer;
    }

    public BigDecimal getTotal_price(){
        return total_price;
    }

    public int getTotalWeightOfProductsInPizzas(){
        return pizzas.stream()
                .flatMap(Pizza -> Pizza.getToppings().stream())
                .collect(Collectors.toList()).stream()
                .reduce(0, (totalWeight, prod) -> totalWeight + prod.getWeight(), Integer::sum);
    }

    public String getMostCommonProductInPizzas(){
        return pizzas.stream()
                .flatMap(Pizza -> Pizza.getToppings().stream())
                .collect(Collectors.toList()).stream().map(Product::getName)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey()
                .describeConstable()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public String toString(){
        return check.toString();
    }

}
