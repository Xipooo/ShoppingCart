package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// As Carrie the customer I want to add items to my shopping basket So that I can pay for them all at once

public class BasketTests {
    @Test
    void GetGroceryItems_ShouldReturnEmptyList_WhenNoGroceryItemsInBasketAndNoGroceryItemsAdded() {
        // GIVEN the application is running and there are no items in the basket
        Basket basket = new Basket(new ArrayList<GroceryItem>());

        // WHEN zero units of soup are added to the basket
        basket.Add(new ArrayList<GroceryItem>());

        // THEN the basket should contain no items
        List<GroceryItem> groceryItems = basket.getGroceryItems();
        assertEquals(0, groceryItems.size());
    }

    @Test
    void GetGroceryItems_ShouldReturnOneItem_WhenOneGroceryItemAddedToBasket() {
        // GIVEN the application is running and there are no items in the basket
        Basket basket = new Basket(new ArrayList<GroceryItem>());

        // WHEN a single bread loaf is added to the basket
        GroceryItem bread = new GroceryItem().setProduct("Bread");
        basket.Add(new ArrayList<GroceryItem>(Arrays.asList(bread)));

        // THEN the basket should contain one item
        List<GroceryItem> groceryItems = basket.getGroceryItems();
        assertEquals(1, groceryItems.size());
    }

    // GIVEN the application is running and there are 2 milk bottles in the basket
    // WHEN 5 apples are added to the basket
    // THEN the basket should contain 7 items

    // GIVEN the application is running and there are no items in the basket
    // WHEN 5 apples and 3 soup tins are added to the basket
    // THEN the basket should contain 8 items
}
