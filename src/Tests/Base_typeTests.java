package Tests;

import Pizzas.Base_type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base_typeTests {

    @Test
    void get_weight() {
        assertEquals(30, Base_type.DOUGH.get_weight());
        assertEquals(20, Base_type.PITA.get_weight());
    }

    @Test
    void testToString() {
        assertEquals("dough", Base_type.DOUGH.toString());
        assertEquals("pita", Base_type.PITA.toString());
    }
}