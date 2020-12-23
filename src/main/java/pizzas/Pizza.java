package pizzas;

import pizzas.exceptions.MissingBaseTypeException;
import pizzas.exceptions.NotSuchIndexException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Pizza extends Dish implements Cooking{
    private String name;
    private Size size;
    private ArrayList<Product> toppings;
    private final Base base;
    private boolean status;
    private static final Logger logger = LogManager.getLogger(Pizza.class);

    /* default constructor
     * */
    public Pizza () {
        name = "";
        size = null;
        toppings = new ArrayList<>();
        base = new Base();
    }
    /* initialization constructor in which calculates total price of pizza and total weight
     * */
    public Pizza(String n, Size s, ArrayList<Product> t, Base_type b_type) {
        name = n;
        size = s;
        toppings = new ArrayList<>(t);
        base = new Base(b_type);
        BigDecimal p = new BigDecimal("0");
        int w = 0;
        for (Product product : t) {
            w += product.getWeight();
            p = product.getPrice().add(p);
        }
        w += base.getWeight();
        p = new BigDecimal(get_price_by_size(s)).add(p);
        setPrice(p);
        setWeight(w);
    }

    public List getAllWeights(){
        return  toppings
                .stream()
                .map(Product::getWeight)
                .collect(Collectors.toList());
    }

    public Product getTheMostExpensiveProduct(){
        return  toppings.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElseThrow(RuntimeException::new);
    }

    public double getMeanWeightOfToppings(){
        return  toppings
                .stream()
                .mapToDouble(Product::getWeight)
                .average()
                .orElse(0);
    }

    public Map<String, List<Product>> getMarkedProductThatHaveWeightBiggerThanValue(int value){
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> neededToppings = toppings
                .stream()
                .filter(e -> e.getWeight() >= value)
                .collect(Collectors.toList());
        map.put("Більше " + value + ": ", neededToppings);
        List<Product> notNeededToppings = toppings
                .stream()
                .filter(e -> e.getWeight() < value)
                .collect(Collectors.toList());
        map.put("Менше " + value + ": ", notNeededToppings);
        map.forEach((k, v) -> logger.trace(k + ": " + v));
        return map;
    }

    public static int get_price_by_size(Size s){
        if (s == Size.KING){
           return 100;
        } else if(s == Size.LARGE){
            return 50;
        } else if(s == Size.MEDIUM) {
            return 30;
        } else
            return 0;
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
        this.toppings = new ArrayList<>(t);
        int w = base.getWeight();
        BigDecimal p = new BigDecimal("0");
        for (Product product : t) {
            w += product.getWeight();
            p = product.getPrice().add(p);
        }
        setPrice(p);
        setWeight(w);
    }
    /* adds more than one toppings, also recalculates price and weight
     * */
    public void addToppings(ArrayList<Product> t) {
        this.toppings.addAll(new ArrayList<>(t));
        int w = getWeight();
        BigDecimal p = getPrice();
        for (Product product : t) {
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
        int prev_w = base.calcWeight(base.type);
        this.size = s;
        int w = base.calcWeight(base.type);
        setWeight(getWeight() - prev_w + w);
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
        logger.trace("Name: " + name);
        logger.trace("Pizzas.Size: " + size.toString());
        for (Product product : toppings) {
            logger.trace(product.getName() + " " + product.getWeight());
        }
        logger.trace("Total weight: " + getWeight());
        logger.trace("Price: " + getPrice() + "\n");
    }
    @Override
    public String toString(){
        StringBuilder pizza_string = new StringBuilder("Name: ");
        pizza_string.append(name);
        pizza_string.append("\n");
        pizza_string.append("Pizzas.Size: ");
        pizza_string.append(size.toString());
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
    public Base getBase() {
        return base;
    }
    public ArrayList<Product> getToppings() {
        return toppings;
    }

    @Override
    public void cook() {
        if (!status){
            logger.trace("Pizza starts baking");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.trace("Pizza has baked");
            status = true;
        } else {
            logger.error("Pizza already had baked");
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
            type = t;
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
        public int calcWeight(Base_type t) {
            return Pizza.this.size.get_weight() * t.get_weight();
        }

        public Base_type getType(){
            return type;
        }

        public int getWeight() {
            return weight;
        }
    }
}
