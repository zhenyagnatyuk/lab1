import Pizzas.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class CheckTest {

    @Test
    void testToString() {
        Size s = Size.MEDIUM;
        Base_type b = Base_type.DOUGH;
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
        Human h1 = new Human("Василь","Петренко");
        Check check = new Check(pizza_arr, h1);
        String Final_str = "Buyer: Василь Петренко\n" + "Name: шось\n" + "Pizzas.Size: medium\n" +
        "кукуруза 10\n" + "Огурок 20\n" + "Квашена капуста 5\n" + "Total weight: 635\n" + "Price: 66\n" +
        "Name: шось 2\n" + "Pizzas.Size: medium\n" + "Кабачкова ікра 100\n" + "Оселедець 100\n" +
        "Невідома штука 100\n" + "Total weight: 900\n" + "Price: 180\n" + "Total price: 246\n";
        assertEquals(Final_str, check.toString());
    }
}