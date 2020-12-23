package unit_tests;

import pizzas.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product prod1, prod2, prod3, prod4;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prod1 = new Product("кукуруза", 10, 10);
        prod2 = new Product("кукуруза", 10, 10);
        prod3 = new Product("кукуруза", 10, 10);
        prod4 = new Product("фасоль", 22, 11);
    }

    @org.junit.jupiter.api.Test
    void Should_Fail_WhenProductsEquals() {
        assertFalse(prod3.equals(prod4));
    }

    @org.junit.jupiter.api.Test
    void Should_Fail_WhenProductsNotEquals() {
        assertTrue(prod1.equals(prod2));
    }

    @org.junit.jupiter.api.Test
    void Should_Fail_WhenHashCodesAreNotEquals() {
        assertEquals(prod1.hashCode(), prod2.hashCode());
    }

    @org.junit.jupiter.api.Test
    void Should_Fail_WhenHashCodesAreEquals() {
        assertNotEquals(prod3.hashCode(), prod4.hashCode());
    }
}