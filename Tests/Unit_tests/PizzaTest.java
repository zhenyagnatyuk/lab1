package Unit_tests;

import Pizzas.Base_type;
import Pizzas.Pizza;
import Pizzas.Product;
import Pizzas.Size;
import Pizzas.exceptions.MissingBaseTypeException;
import Pizzas.exceptions.NotSuchIndexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaTest {

    private ArrayList<Product> prods,prods2;
    private Size s;
    private Base_type b;
    @BeforeEach
    void setUp() {
        s = Size.MEDIUM;
        b = Base_type.DOUGH;
        Product p = new Product("кукуруза", 10, 10);
        Product p1 = new Product("Огурок", 20, 15);
        Product p2 = new Product("Квашена капуста", 5, 11);

        Product p3 = new Product("Кабачкова ікра", 100, 50);
        Product p4 = new Product("Оселедець", 100, 50);
        Product p5 = new Product("Невідома штука", 100, 50);
        //creating pizza
        prods = new ArrayList<>();
        prods2 = new ArrayList<>();
        prods.add(p);
        prods.add(p1);
        prods.add(p2);
        prods2.add(p3);
        prods2.add(p4);
        prods2.add(p5);

    }

    @Test
    public void Constructor_CertainPriceAndWeight_CreationOfObjectWithParams() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        assertEquals(36, pizza1.getPrice().intValue());
        assertEquals(335, pizza1.getWeight());
    }
    @Test
    void setToppings_CertainPriceAndWeightAndArray_SettingArrayOfProducts() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        pizza1.setToppings(prods2);
        assertArrayEquals(prods2.toArray(), pizza1.getToppings().toArray());
        assertEquals(150, pizza1.getPrice().intValue());
        assertEquals(600, pizza1.getWeight());
    }

    @Test
    void addToppings_CertainPriceAndWeightAndArray_AddingArrayOfProductsToToppings() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        ArrayList<Product> all_prods= new ArrayList<>();
        all_prods.addAll(prods);
        all_prods.addAll(prods2);
        pizza1.addToppings(prods2);
        assertArrayEquals(all_prods.toArray(), pizza1.getToppings().toArray());
        assertEquals(186, pizza1.getPrice().intValue());
        assertEquals(635, pizza1.getWeight());
    }

    @Test
    void addTopping_CertainPriceAndWeightAndArray_AddingSingleProductToToppings() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        Product p3 = new Product("Кабачкова ікра", 100, 50);
        pizza1.addTopping(p3);
        ArrayList<Product> all_prods= new ArrayList<>();
        all_prods.addAll(prods);
        all_prods.add(p3);
        assertArrayEquals(all_prods.toArray(), pizza1.getToppings().toArray());
        assertEquals(86, pizza1.getPrice().intValue());
        assertEquals(435, pizza1.getWeight());
    }

    @Test
    void removeTopping_CertainPriceAndWeightAndArray_RemovingToppingByName() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        pizza1.removeTopping("кукуруза");
        assertEquals(2, pizza1.getToppings().size());
        assertEquals(26, pizza1.getPrice().intValue());
        assertEquals(325, pizza1.getWeight());
    }

    @Test
    void removeTopping_CertainPriceAndWeightAndArraySize_RemovingToppingByIndex() throws NotSuchIndexException {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        pizza1.removeTopping(0);
        assertEquals(2, pizza1.getToppings().size());
        assertEquals(26, pizza1.getPrice().intValue());
        assertEquals(325, pizza1.getWeight());
    }
    @Test
    void Should_ThrowNotSuchIndexException_When_IndexOfToppingNotExists () throws NotSuchIndexException {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        assertThrows(NotSuchIndexException.class, () -> {
            pizza1.removeTopping(100);
        });
    }

    @Test
    void Should_Fail_When_CookingStatusIsIncorrect() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        assertFalse(pizza1.getStatus());
        pizza1.cook();
        assertTrue(pizza1.getStatus());
    }

    @Test
    void get_price_by_size_CertainPrice_OnSpecifiedSize() {
        assertEquals(0, Pizza.get_price_by_size(Size.SMALL));
        assertEquals(30, Pizza.get_price_by_size(Size.MEDIUM));
        assertEquals(50, Pizza.get_price_by_size(Size.LARGE));
        assertEquals(100, Pizza.get_price_by_size(Size.KING));
    }

    @Test
    void ToString_CertainString_GetStringRepresentationOfPizza() {
        Pizza pizza1 = new Pizza("шось", Size.SMALL, prods, b);
        StringBuilder pizza_string = new StringBuilder("Name: ");
        pizza_string.append("шось");
        pizza_string.append("\n");
        pizza_string.append("Pizzas.Size: ");
        pizza_string.append(Size.SMALL.toString());
        pizza_string.append("\n");
        for (Product product : prods) {
            pizza_string.append(product.getName());
            pizza_string.append(" ");
            pizza_string.append(product.getWeight());
            pizza_string.append("\n");
        }
        pizza_string.append("Total weight: ");
        pizza_string.append(335);
        pizza_string.append("\n");
        pizza_string.append("Price: ");
        pizza_string.append(36);
        pizza_string.append("\n");
        assertEquals(pizza_string.toString(), pizza1.toString());
    }

    @Test
    void SetSize_CertainPriceAndWeight_SettingSpecifiedSize() {
        Pizza pizza1 = new Pizza("шось", Size.KING, prods, b);
        assertEquals(136, pizza1.getPrice().intValue());
        assertEquals(1535, pizza1.getWeight());
        pizza1.setSize(Size.LARGE);
        assertEquals(86, pizza1.getPrice().intValue());
        assertEquals(935, pizza1.getWeight());
        pizza1.setSize(Size.MEDIUM);
        assertEquals(66, pizza1.getPrice().intValue());
        assertEquals(635, pizza1.getWeight());
        pizza1.setSize(Size.SMALL);
        assertEquals(36, pizza1.getPrice().intValue());
        assertEquals(335, pizza1.getWeight());
        pizza1.setSize(Size.KING);
        assertEquals(136, pizza1.getPrice().intValue());
        assertEquals(1535, pizza1.getWeight());
    }

    @Test
    void getTheMostExpensiveProduct_CertainProduct_SpecifyingProduct(){
        Pizza pizza1 = new Pizza("шось", Size.KING, prods, b);
        Product testedProd = pizza1.getTheMostExpensiveProduct();
        Product p1 = new Product("Огурок", 20, 15);
        assertEquals(testedProd, p1);
    }

    @Test
    void getMeanWeightOfToppings_MeanWeight_EqualsMeanWeight(){
        Pizza pizza1 = new Pizza("шось", Size.KING, prods, b);
        assertEquals(35.0/3, pizza1.getMeanWeightOfToppings());
    }

    @Test
    void getMarkedProductThatHaveWeightBiggerThanValue_CertainString_EqualsString(){
        Pizza pizza1 = new Pizza("шось", Size.KING, prods, b);
        Map<String, String> res = new HashMap<String, String>();
        res.put("кукуруза", "Підходить");
        res.put("Квашена капуста", "Не підходить");
        res.put("Огурок", "Підходить");
        assertEquals(res, pizza1.getMarkedProductThatHaveWeightBiggerThanValue(10));
    }

    @Test
    void getAllWeights_ArrayOfWeights_EqualsArrays(){
        Pizza pizza1 = new Pizza("шось", Size.KING, prods, b);
        ArrayList<Integer> weights = new ArrayList<>();
        weights.add(10);
        weights.add(20);
        weights.add(5);
        assertEquals(weights, pizza1.getAllWeights());
    }


}