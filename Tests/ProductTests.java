
import Pizzas.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductTests {
    private Product prod1, prod2, prod3, prod4;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prod1 = new Product("кукуруза", 10, 10);
        prod2 = new Product("кукуруза", 10, 10);
        prod3 = new Product("кукуруза", 10, 10);
        prod4 = new Product("фасоль", 22, 11);
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertTrue(prod1.equals(prod2));
        assertFalse(prod3.equals(prod4));
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        assertTrue(prod1.equals(prod2));
        assertFalse(prod3.equals(prod4));
    }
}