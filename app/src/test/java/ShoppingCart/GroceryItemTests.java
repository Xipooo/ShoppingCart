package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(doubles = {0.00, 1.00, 1.30, .65, .80, .10})
    void GetRetailPrice_ReturnsValueSetBySetRetailPrice(double retailPrice) {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setRetailPrice(retailPrice);
        assertEquals(retailPrice, groceryItem.getRetailPrice());
    }

    @Test
    void SetRetailPrice_ThrowsIllegalArgumentException_WhenPassedNegativeValue() {
        GroceryItem groceryItem = new GroceryItem();

        assertThrows(IllegalArgumentException.class, () -> groceryItem.setRetailPrice(-1.00));
    }
}
