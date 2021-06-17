package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

// As Carrie the customer I want to receive discounts on purchases of bread so that I pay less money
public class BreadDiscountTests {
    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDateToday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now();
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the items is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of the bread should be half the normal price
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDateYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now().minusDays(1);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the items is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of the bread should be half the normal price
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
    // and the date of purchase is 7 days from yesterday
    // WHEN the price of the items is totaled
    // THEN the price of the bread should be half the normal price

    // GIVEN the customer places 1 tins of soup and one load of bread in the basket
    // and the date of purchase is between yesterday and 7 days after yesterday
    // WHEN the price of the items is totaled
    // THEN the price of the bread should be the normal price

    // GIVEN the customer places 6 tins of soup and 3 loafs of bread in the basket,
    // and the date of purchase is between yesterday and 7 days after yesterday
    // WHEN the prices of the items is totaled
    // THEN the price of each loaf of bread should be half the normal price

    // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
    // and the date of purchase is 8 days from today
    // WHEN the price of the items is totaled
    // THEN the price of the bread should be the normal price
}
