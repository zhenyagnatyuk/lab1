package Tests;

import Pizzas.Base_type;
import Pizzas.Pizza;
import Pizzas.Product;
import Pizzas.Size;
import Pizzas.exceptions.MissingBaseTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BaseTests {
    private ArrayList<Product> prods;
    private Base_type b;
    Pizza pizza1;
    @BeforeEach
    void setUp() {
        Product p = new Product("кукуруза", 10, 10);
        Product p1 = new Product("Огурок", 20, 15);
        Product p2 = new Product("Квашена капуста", 5, 11);
        //creating pizza
        prods = new ArrayList<>();
        prods.add(p);
        prods.add(p1);
        prods.add(p2);
        pizza1 = new Pizza("шось", Size.SMALL, prods, Base_type.DOUGH);
    }

    @Test
    public void testConstructorWithParams() throws MissingBaseTypeException {
        assertThrows(MissingBaseTypeException.class, () -> {
            new Pizza("шось", Size.SMALL, prods, null);
        });
        Pizza pizza =  new Pizza("шось", Size.SMALL, prods, Base_type.DOUGH);
        assertEquals(300, pizza.getBase().getWeight());
    }

    @Test
    public void calcWeight(){
        assertEquals(300, pizza1.getBase().calcWeight(Base_type.DOUGH));
        assertEquals(200, pizza1.getBase().calcWeight(Base_type.PITA));
    }

    @Test
    public void setType() throws MissingBaseTypeException {
        assertThrows(MissingBaseTypeException.class, () -> {
            pizza1.getBase().setType(null);
        });
        pizza1.getBase().setType(Base_type.PITA);
        assertEquals(Base_type.PITA, pizza1.getBase().getType());
        pizza1.getBase().setType(Base_type.DOUGH);
        assertEquals(Base_type.DOUGH, pizza1.getBase().getType());
    }

}
