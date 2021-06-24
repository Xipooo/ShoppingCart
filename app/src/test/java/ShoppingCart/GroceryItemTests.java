package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GroceryItemTests {
    @Test
    void SetProduct_ThrowsIllegalArgumentException_WhenPassedEmptyString() {
        GroceryItem groceryItem = new GroceryItem();

        assertThrows(IllegalArgumentException.class, () -> groceryItem.setProduct(""));
    }

    @Test
    void GetProduct_ReturnsApple_WhenSetProductPassedApple() {
        GroceryItem groceryItem = new GroceryItem();

        groceryItem.setProduct("Apple");

        assertEquals("Apple", groceryItem.getProduct());
    }
    
    @Test
    void GetRetailPrice_Returns0_WhenSetRetailPricePassed0() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setRetailPrice(0.00);

        assertEquals(0.00, groceryItem.getRetailPrice());
    }

    @Test
    void GetRetailPrice_Returns1_WhenSetRetailPricePassed1() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setRetailPrice(1.00);
        assertEquals(1.00, groceryItem.getRetailPrice());
    }

    @Test
    void GetRetailPrice_Returns1_30_WhenSetRetailPricePassed1_30() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setRetailPrice(1.30);
        assertEquals(1.30, groceryItem.getRetailPrice());
    }
}
