package Pizzas;


import java.math.BigDecimal;
import java.util.ArrayList;

public class Check {
    private ArrayList<Pizza> pizzas;
    private Human buyer;
    public Check() {
        pizzas = new ArrayList<>();
        buyer = new Human();
    }
    public Check(ArrayList<Pizza> p, Human b) {
        pizzas = p;
        buyer = b;
    }
    public void printCheck() {
        BigDecimal price = new BigDecimal(0);
        for (Pizza pizza : pizzas){
            pizza.printPizza();
            price = pizza.getPrice().add(price);
        }
        System.out.println("Buyer: " + buyer.getName() + " " + buyer.getSurname());
        System.out.println("Total price: " + price + "\n\n\n");
    }

}
