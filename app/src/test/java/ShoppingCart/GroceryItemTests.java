package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GroceryItemTests {
    @Test
    void SetProduct_ThrowsIllegalArgumentException_WhenPassedEmptyString() {
        GroceryItem groceryItem = new GroceryItem();

        assertThrows(IllegalArgumentException.class, () -> groceryItem.setProduct(""));
    }
}
