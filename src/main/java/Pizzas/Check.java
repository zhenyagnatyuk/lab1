package Pizzas;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Check {
    private ArrayList<Pizza> pizzas;
    private Human buyer;
    private StringBuilder check;
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
        check.append("Total price: ");
        check.append(price);
        check.append("\n");

    }

    @Override
    public String toString(){
        return check.toString();
    }

}
