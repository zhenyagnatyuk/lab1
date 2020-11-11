
import Pizzas.*;
import Pizzas.exceptions.MissingBaseTypeException;
import Pizzas.exceptions.NotSuchIndexException;

import java.util.ArrayList;
import java.util.Iterator;


public class Main {

    public static void main(String[] args) {
	    Size s = Size.MEDIUM;
        Base_type b = Base_type.DOUGH;
        //enum toString method
        System.out.println(b.toString());

        ArrayList<Check> checks = new ArrayList<>();
        Product p = new Product("кукуруза", 10, 10);
        Product p1 = new Product("Огурок", 20, 15);
        Product p2 = new Product("Квашена капуста", 5, 11);

        Product p3 = new Product("Кабачкова ікра", 100, 50);
        Product p4 = new Product("Оселедець", 100, 50);
        Product p5 = new Product("Невідома штука", 100, 50);
        //creating pizza
        ArrayList<Product> prods = new ArrayList<>();
        ArrayList<Product> prods2 = new ArrayList<>();
        prods.add(p);
        prods.add(p1);
        prods.add(p2);
        prods2.add(p3);
        prods2.add(p4);
        prods2.add(p5);
        ArrayList<Pizza> pizza_arr = new ArrayList<>();
        Pizza pizza1 = new Pizza("шось", s, prods, b);
        Pizza pizza2 = new Pizza("шось 2", s, prods2, b);
        pizza_arr.add(pizza1);
        pizza_arr.add(pizza2);
        //checking cook method
        pizza1.cook();
        pizza2.cook();
        //creating human and adding to check
        Human h1 = new Human("John 'Залізний шлунок'","Wick");
        Check check = new Check(pizza_arr, h1);
        checks.add(check);

        s = Size.KING;
        b = Base_type.PITA;
        Product p6 = new Product("сир", 14, 100);
        Product p7 = new Product("Помідор", 30, 15);
        Product p8 = new Product("Перець", 25, 11);

        Product p9 = new Product("Салямі", 30, 35);
        Product p10 = new Product("Філе", 30, 60);
        Product p11 = new Product("Кетчуп", 100, 10);
        prods = new ArrayList<>();
        prods2 = new ArrayList<>();
        prods.add(p6);
        prods.add(p7);
        prods.add(p8);
        prods2.add(p9);
        prods2.add(p10);
        prods2.add(p11);
        pizza_arr = new ArrayList<>();
        pizza1 = new Pizza("fodja", s, prods, b);
        pizza2 = new Pizza("Palermo", s, prods2, b);
        try {
            pizza2.removeTopping(0);
        } catch (NotSuchIndexException e) {
            System.out.println(e.getMessage());
        }
        pizza2.removeTopping("Салямі");
        pizza_arr.add(pizza1);
        pizza_arr.add(pizza2);
        h1 = new Human("Василь","Петренко");
        check = new Check(pizza_arr, h1);
        checks.add(check);

        Iterator i = checks.iterator();
        while (i.hasNext()) {
            check = (Check) i.next();
            System.out.println(check.toString()+ "\n");
        }
        //String task
        System.out.println("String task");
        System.out.println(pizza1.toString().split(String.valueOf('\n'))[0] +
                "\nready: " + pizza1.getStatus()+ "\n");
        pizza1.cook();
        System.out.println(pizza1.toString().split(String.valueOf('\n'))[0] +
                "\nready: " + pizza1.getStatus()+ "\n\n\n\n");

        //checking exception
        System.out.println("Exception task");
        try {
            pizza2.removeTopping(100);
        } catch (NotSuchIndexException e) {
            System.out.println(e.getMessage()+ "\n");
        }
        try {
            pizza2.getBase().setType(Base_type.PITA);
        } catch (MissingBaseTypeException e) {
            System.out.println(e.getMessage()+ "\n");
        }
        try {
            pizza2.getBase().setType(null);
        } catch (MissingBaseTypeException e) {
            System.out.println(e.getMessage() + "\n\n\n\n");
        }

        //equals and hashCode task
        System.out.println("Equals and hashCode task");
        Human human1 = new Human("Василь","Петренко");
        Human human2 = new Human("Василь","Петренко");
        // false because equals now checking the links
        System.out.println(human1.equals(human2));
        System.out.println((human1.hashCode() == human2.hashCode()) + "\n");

        Product prod1 = new Product("кукуруза", 10, 10);
        Product prod2 = new Product("кукуруза", 10, 10);
        System.out.println(prod1.equals(prod2));
        System.out.println((prod1.hashCode() == prod2.hashCode()) + "\n");


    }
}
