package integrated_tests;
import pizzas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CheckIT {
    private ArrayList<Product> prods;
    private ArrayList<Pizza> pizza_arr;
    private Human h1;
    private Pizza pizza1;
    @BeforeEach
    public void setUp(){
        pizza_arr = new ArrayList<>();
        pizza1 = Mockito.mock(Pizza.class);
        pizza_arr.add(pizza1);
        pizza_arr.add(pizza1);
        h1 = Mockito.mock(Human.class);
        Mockito.when(h1.getName()).thenReturn("Василь");
        Mockito.when(h1.getSurname()).thenReturn("Петренко");
        Mockito.when(pizza1.getPrice()).thenReturn(new BigDecimal(100));
    }

    @Spy
    private final Check spy_check = new Check();

    @Spy
    private final Human spy_buyer = new Human();

    @Test
    void setBuyer_CertainStrings_SettingBuyer(){
        spy_buyer.setName("John");
        spy_buyer.setSurname("Wick");

        spy_check.setBuyer(spy_buyer);

        assertEquals("John", spy_check.getBuyer().getName());
        assertEquals("Wick", spy_check.getBuyer().getSurname());
    }

    @Test
    void Constructor_CertainPrice_WithParams(){
        Check check = new Check(pizza_arr, h1);
        assertEquals(new BigDecimal(200), check.getTotal_price());
        Mockito.verify(pizza1, times(2)).getPrice();
        Mockito.verify(h1, times(1)).getSurname();
        Mockito.verify(h1, times(1)).getName();
    }
}
