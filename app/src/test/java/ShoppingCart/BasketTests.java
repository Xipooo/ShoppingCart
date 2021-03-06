package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import ShoppingCart.Domain.Basket;
import ShoppingCart.Domain.GroceryItem;

// As Carrie the customer I want to add items to my shopping basket So that I can pay for them all at once

public class BasketTests {

    private List<GroceryItem> generateGroceryItems(String groceryProduct, Integer amount) {
       return Stream.generate(() -> new GroceryItem().setProduct(groceryProduct)).limit(amount).collect(Collectors.toList());
    }

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

    @Test
    void GetGroceryItems_ShouldReturn7GroceryItems_WhenBasketInitializedWith2MilkAnd5ApplesAdded() {
        // GIVEN the application is running and there are 2 milk bottles in the basket
        GroceryItem milk1 = new GroceryItem().setProduct("Milk");
        GroceryItem milk2 = new GroceryItem().setProduct("Milk");
        Basket basket = new Basket(new ArrayList<GroceryItem>(Arrays.asList(milk1, milk2)));

        // WHEN 5 apples are added to the basket
        List<GroceryItem> apples = generateGroceryItems("Apple", 5);
        basket.Add(apples);

        // THEN the basket should contain 7 items
        List<GroceryItem> groceryItems = basket.getGroceryItems();
        assertEquals(7, groceryItems.size());
    }

    @Test
    void GetGroceryItems_ShouldReturn8GroceryItems_WhenBasketInitializedWith0ItemsAnd5ApplesAnd3SoupsAdded() {
        // GIVEN the application is running and there are no items in the basket
        Basket basket = new Basket(new ArrayList<GroceryItem>());

        // WHEN 5 apples and 3 soup tins are added to the basket
        basket.Add(generateGroceryItems("Apple", 5));
        basket.Add(generateGroceryItems("Soup", 3));

        // THEN the basket should contain 8 items
        assertEquals(8, basket.getGroceryItems().size());
    }

    @Test
    void Add_ShouldThrowIllegalArgumentException_WhenNullPassed() {
        // GIVEN there are no items in the basket
        Basket basket = new Basket(new ArrayList<GroceryItem>());

        // WHEN a null is attempted to be added
        // THEN the basket should throw an IllegalArgument exception
        assertThrows(IllegalArgumentException.class, () -> basket.Add(null));
    }

    @Test
    void Constructor_ShouldThrowIllegalArgumentException_WhenNullPassed() {
        // GIVEN there is no basket
        // WHEN the basket is created with a null argument
        // THEN the constructor should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Basket(null));
    }
}
