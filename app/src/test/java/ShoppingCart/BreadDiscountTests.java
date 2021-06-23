package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

// As Carrie the customer I want to receive discounts on purchases of bread so that I pay less money
public class BreadDiscountTests {

    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count){
        return new ArrayList<GroceryItem>(Stream.generate(() -> new GroceryItem().setProduct(productType)).limit(count).collect(Collectors.toList()));
    }

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

        // WHEN the price of the discount is totaled
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

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of the bread should be half the normal price
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is 7 days from yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of the bread should be half the normal price
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnFullRetailPrice_WhenOneSoupsAndOneBreadAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 1 tins of soup and one loaf of bread in the basket
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), bread = new GroceryItem().setProduct("Bread");

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, bread)));
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of the bread should be the normal price
        assertEquals(bread.getRetailPrice(), discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfOfTotalBreadRetailPrice_WhenMultipleSoupsAndBreadsAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 6 tins of soup and 3 loafs of bread in the basket,
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        List<GroceryItem> soups = new ArrayList<GroceryItem>(GenerateGroceryItemList("Soup", 6));
        List<GroceryItem> breads = new ArrayList<GroceryItem>(GenerateGroceryItemList("Bread", 3));
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>(soups);
        groceryItems.addAll(breads);
        when(basket.getGroceryItems()).thenReturn(groceryItems);
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the prices of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the price of each loaf of bread should be half the normal price
        assertEquals((breads.get(0).getRetailPrice() * 3) / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnZero_WhenOneSoupsAndOneBreadAreInBasketAndPurchaseDate8DaysFromYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is 8 days from yesterday
        Basket basket = mock(Basket.class);
        List<GroceryItem> soups = new ArrayList<GroceryItem>(GenerateGroceryItemList("Soup", 2));
        List<GroceryItem> breads = new ArrayList<GroceryItem>(GenerateGroceryItemList("Bread", 1));
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>(soups);
        groceryItems.addAll(breads);
        when(basket.getGroceryItems()).thenReturn(groceryItems);
        LocalDate purchaseDate = LocalDate.now().plusDays(7);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN there should be no discount
        assertEquals(0, discountAmount);
    }
}
