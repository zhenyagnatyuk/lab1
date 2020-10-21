package Pizzas;

import Pizzas.exceptions.MissingBaseTypeException;
import Pizzas.exceptions.NotSuchIndexException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Pizza extends Dish implements Cooking{
    private String name;
    private Size size;
    private ArrayList<Product> toppings;
    private Base base;
    private boolean status;

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

    public boolean getStatus() {
        return status;
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
    public void removeTopping(int index) throws NotSuchIndexException {
        if(index < 0 || index > toppings.size()) {
            throw new NotSuchIndexException("No such index");
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
        System.out.println("Pizzas.Size: " + getSize_str());
        for (Product product : toppings) {
            System.out.println(product.getName() + " " + product.getWeight());
        }
        System.out.println("Total weight: " + getWeight());
        System.out.println("Price: " + getPrice() + "\n");
    }
    @Override
    public String toString(){
        StringBuilder pizza_string = new StringBuilder("Name: ");
        pizza_string.append(name);
        pizza_string.append("\n");
        pizza_string.append("Pizzas.Size: ");
        pizza_string.append(getSize_str());
        pizza_string.append("\n");
        for (Product product : toppings) {
            pizza_string.append(product.getName());
            pizza_string.append(" ");
            pizza_string.append(product.getWeight());
            pizza_string.append("\n");
        }
        pizza_string.append("Total weight: ");
        pizza_string.append(getWeight());
        pizza_string.append("\n");
        pizza_string.append("Price: ");
        pizza_string.append(getPrice());
        pizza_string.append("\n");
        return pizza_string.toString();
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

    @Override
    public void cook() {
        if (!status){
            System.out.println("Pizza starts baking");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Pizza has baked");
            status = true;
        } else {
            System.out.println("Pizza already had baked");
        }
    }

    /* class, which represents base, has its type and weight
     * */
    public class Base{
        Base_type type;
        int weight;

        Base () {
            type = Base_type.DOUGH;
            weight = 0;
        }
        Base (Base_type t) throws MissingBaseTypeException {
            if (t == null){
                throw new MissingBaseTypeException("Base type can't be null");
            }
            type = Base_type.DOUGH;
            weight = calcWeight(t);
        }
        /* replaces base type, also recalculates weight
         * */
        public void setType(Base_type t) throws MissingBaseTypeException {
            if (t == null){
                throw new MissingBaseTypeException("Base type can't be null");
            }
            type = t;
            int w = Pizza.this.getWeight() - weight;
            weight = calcWeight(t);
            Pizza.this.setWeight(w + weight);
        }
        /* calculates weight based on base type and pizza size
         * */
        int calcWeight(Base_type t) {
            return Pizza.this.size.get_weight() * t.get_weight();
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
