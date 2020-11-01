package Tests;

import Pizzas.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeTests {

    @Test
    void get_weight() {
        assertEquals(10, Size.SMALL.get_weight());
        assertEquals(20, Size.MEDIUM.get_weight());
        assertEquals(30, Size.LARGE.get_weight());
        assertEquals(50, Size.KING.get_weight());
    }

    @Test
    void testToString() {
        assertEquals("small", Size.SMALL.toString());
        assertEquals("medium", Size.MEDIUM.toString());
        assertEquals("large", Size.LARGE.toString());
        assertEquals("king", Size.KING.toString());
    }
}