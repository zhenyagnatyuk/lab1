package Integrated_tests;

import Pizzas.Base_type;
import Pizzas.Pizza;
import Pizzas.Product;
import Pizzas.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IntegratedPizzaTests {
    Product p;
    ArrayList<Product> prods;
    @BeforeEach
    void setUp() {
        prods = new ArrayList<>();
        p = Mockito.mock(Product.class);
        p.setWeight(10);
        p.setPrice(new BigDecimal(100));
        Mockito.when(p.getWeight()).thenReturn(10);
        Mockito.when(p.getPrice()).thenReturn(new BigDecimal(100));
    }

    @Test
    public void Constructor_CertainWeight_WithParams() {
        prods.add(p);
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, Base_type.DOUGH);
        assertEquals(pizza1.getToppings().stream().mapToInt(Product::getWeight).sum(), 10);
        Mockito.verify(p, times(2)).getWeight();
        Mockito.verify(p, times(1)).getPrice();
    }

    @Test
    public void addToppings_CertainWeight_AddingArrayOfProducts() {
        Pizza pizza = new Pizza();
        prods.add(p);
        prods.add(p);
        pizza.addToppings(prods);
        assertEquals(pizza.getToppings().stream().mapToInt(Product::getWeight).sum(), 20);
        Mockito.verify(p, times(4)).getWeight();
        Mockito.verify(p, times(2)).getPrice();
    }

    @Test
    public void addToppings_CertainWeight_Adding2Products() {
        Pizza pizza = new Pizza();
        pizza.addTopping(p);
        pizza.addTopping(p);
        assertEquals(pizza.getToppings().stream().mapToInt(Product::getWeight).sum(), 20);
        Mockito.verify(p, times(4)).getWeight();
        Mockito.verify(p, times(2)).getPrice();
    }
    @Test
    void setToppings_CertainWeight_SettingArrayOfProducts() {
        Pizza pizza = new Pizza();
        prods.add(p);
        prods.add(p);
        pizza.setToppings(prods);
        assertEquals(pizza.getToppings().stream().mapToInt(Product::getWeight).sum(), 20);
        Mockito.verify(p, times(4)).getWeight();
        Mockito.verify(p, times(2)).getPrice();
    }

}
