package Pizzas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Pizza extends Dish {
    /* Enum which represents size of pizza
    * */
    public enum Size{
        SMALL,
        MEDIUM,
        LARGE,
        KING
    }
    /* Enum which represents base type of pizza
     * */
    public enum Base_type {
        DOUGH,
        PITA
    }

    private String name;
    private Size size;
    private ArrayList<Product> toppings;
    private Base base;
    /* default constructor
     * */
    Pizza () {
        name = "";
        size = null;
        toppings = null;
        base = new Base();
    }
    /* initialization constructor in which calculates total price of pizza and total weight
     * */
    public Pizza(String n, Size s, ArrayList<Product> t, Base_type b_type) {
        name = n;
        size = s;
        toppings = t;
        base = new Base(b_type);
        BigDecimal p = new BigDecimal("0");
        int w = 0;
        Iterator i = t.iterator();
        while (i.hasNext()) {
            Product product = (Product) i.next();
            w += product.getWeight();
            p = product.getPrice().add(p);
        }
        w += base.getWeight();
        if (s == Size.KING){
            p = new BigDecimal("100").add(p);
        } else if(s == Size.LARGE){
            p = new BigDecimal("50").add(p);
        } else if(s == Size.MEDIUM) {
            p = new BigDecimal("30").add(p);
        }
        setPrice(p);
        setWeight(w);
    }
    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
    /* Replaces toppings with new one, also recalculates price and weight
     * */
    public void setToppings(ArrayList<Product> t) {
        this.toppings = t;
        int w = 0;
        BigDecimal p = new BigDecimal("0");
        Iterator i = t.iterator();
        while (i.hasNext()) {
            Product product = (Product) i.next();
            w += product.getWeight();
            p = product.getPrice().add(p);
        }
        setPrice(p);
        setWeight(w);
    }
    /* adds more than one toppings, also recalculates price and weight
     * */
    public void addToppings(ArrayList<Product> t) {
        this.toppings.addAll(t);
        int w = getWeight();
        BigDecimal p = getPrice();
        Iterator i = t.iterator();
        while (i.hasNext()) {
            Product product = (Product) i.next();
            w += product.getWeight();
            p = product.getPrice().add(p);
        }
        this.setPrice(p);
        this.setWeight(w);
    }
    /* adds one topping, also recalculates price and weight
     * */
    public void addTopping(Product prod) {
        this.toppings.add(prod);
        int w = getWeight();
        BigDecimal p = getPrice();
        w += prod.getWeight();
        p = prod.getPrice().add(p);
        this.setPrice(p);
        this.setWeight(w);
    }
    /* removes toppings by name, also recalculates price and weight
     * */
    public void removeTopping(String n){
        Iterator i = toppings.iterator();
        int w = 0;
        BigDecimal p = new BigDecimal(0);
        while (i.hasNext()) {
            Product product = (Product) i.next();
            if(product.getName().equals(n)){
                w += product.getWeight();
                p = product.getPrice().add(p);
                i.remove();
            }
        }
        setPrice(getPrice().subtract(p));
        setWeight(getWeight() - w);
    }
    /* removes topping by index, also recalculates price and weight
     * */
    public void removeTopping(int index){
        if(index < 0 || index > toppings.size()) {
            throw new IllegalArgumentException("No such index");
        }
        int w = toppings.get(index).getWeight();
        BigDecimal p = toppings.get(index).getPrice();
        setPrice(getPrice().subtract(p));
        setWeight(getWeight() - w);
        toppings.remove(index);
    }
    /* changes size of pizza, also recalculates price and weight
     * */
    public void setSize(Size s) {
        BigDecimal p = getPrice();
        if (size == Size.KING){
            p = new BigDecimal("-100").add(p);
        } else if(size == Size.LARGE){
            p = new BigDecimal("-50").add(p);
        } else if(size == Size.MEDIUM) {
            p = new BigDecimal("-30").add(p);
        }
        this.size = s;
        int w = base.calcWeight(base.type);
        setWeight(getWeight() - w);
        base.weight = w;
        if (s == Size.KING){
            p = new BigDecimal("100").add(p);
        } else if(s == Size.LARGE){
            p = new BigDecimal("50").add(p);
        } else if(s == Size.MEDIUM) {
            p = new BigDecimal("30").add(p);
        }
        this.setPrice(p);
    }
    /* Prints pizza to check
     * */
    public void printPizza(){
        System.out.println("Name: " + name);
        System.out.println("Size: " + getSize_str());
        for (Product product : toppings) {
            System.out.println(product.getName() + " " + product.getWeight());
        }
        System.out.println("Total weight: " + getWeight());
        System.out.println("Price: " + getPrice() + "\n");
    }

    public Size getSize() {
        return size;
    }
    public String getSize_str() {
        assert size != null;
        switch (size) {
            case SMALL: return "small";
            case MEDIUM: return "medium";
            case LARGE: return "large";
            case KING: return "king";
        }
        return "";
    }
    public Base getBase() {
        return base;
    }
    public ArrayList<Product> getToppings() {
        return toppings;
    }
    /* class, which represents base, has its type and weight
     * */
    public class Base{
        Base_type type;
        int weight;

        Base () {
            type = null;
            weight = 0;
        }
        Base (Base_type t) {
            type = Base_type.DOUGH;
            weight = calcWeight(t);
        }
        /* replaces base type, also recalculates weight
         * */
        public void setType(Base_type t) {
            type = t;
            int w = Pizza.this.getWeight() - weight;
            weight = calcWeight(t);
            Pizza.this.setWeight(w + weight);
        }
        /* calculates weight based on base type and pizza size
         * */
        int calcWeight(Base_type t) {
            if ((t ==  Base_type.DOUGH) && (Pizza.this.size == Size.SMALL)){
                return 300;
            } else if ((t ==  Base_type.DOUGH) && (Pizza.this.size == Size.MEDIUM)){
                return 450;
            } else if ((t ==  Base_type.DOUGH) && (Pizza.this.size == Size.LARGE)){
                return 600;
            } else if ((t ==  Base_type.DOUGH) && (Pizza.this.size == Size.KING)){
                return 1000;
            } else if ((t ==  Base_type.PITA) && (Pizza.this.size == Size.SMALL)){
                return 200;
            } else if ((t ==  Base_type.PITA) && (Pizza.this.size == Size.MEDIUM)){
                return 300;
            } else if ((t ==  Base_type.PITA) && (Pizza.this.size == Size.LARGE)){
                return 500;
            } else if ((t ==  Base_type.PITA) && (Pizza.this.size == Size.KING)){
                return 800;
            }
            return 0;
        }
        String type_to_str(Base_type t) {
            switch (t) {
                case DOUGH: return "dough";
                case PITA: return "pita";
            }
            return "";
        }
        public String getType_str() {
            assert type != null;
            switch (type) {
                case DOUGH: return "dough";
                case PITA: return "pita";
            }
            return "";
        }
        public Base_type getType(){
            return type;
        }

        int getWeight() {
            return weight;
        }
    }
}
