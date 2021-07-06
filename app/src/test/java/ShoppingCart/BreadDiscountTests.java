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

import ShoppingCart.Domain.Basket;
import ShoppingCart.Domain.BreadDiscount;
import ShoppingCart.Domain.GroceryItem;

// As Carrie the customer I want to receive discounts on purchases of bread so that I pay less money
public class BreadDiscountTests {

    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count, double retailPrice){
        return Stream.generate(()-> {
            GroceryItem gi = new GroceryItem().setProduct(productType);
            gi.setRetailPrice(retailPrice);
            return gi;
        }).limit(count).collect(Collectors.toList());
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDateToday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");
        soup1.setRetailPrice(0.65);
        soup2.setRetailPrice(0.65);
        bread.setRetailPrice(0.80);

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now();
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the discount amount should be half the price of the bread
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDateYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");
        soup1.setRetailPrice(0.65);
        soup2.setRetailPrice(0.65);
        bread.setRetailPrice(0.80);

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now().minusDays(1);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the discount amount should be half the price of the bread
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfBreadRetailPrice_WhenTwoSoupsAndOneBreadAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is 7 days from yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), soup2 = new GroceryItem().setProduct("Soup"),
                bread = new GroceryItem().setProduct("Bread");
        soup1.setRetailPrice(0.65);
        soup2.setRetailPrice(0.65);
        bread.setRetailPrice(0.80);

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, soup2, bread)));
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the discount amount should be half the price of the bread
        assertEquals(bread.getRetailPrice() / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnFullRetailPrice_WhenOneSoupsAndOneBreadAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 1 tins of soup and one loaf of bread in the basket
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        GroceryItem soup1 = new GroceryItem().setProduct("Soup"), bread = new GroceryItem().setProduct("Bread");
        soup1.setRetailPrice(0.65);
        bread.setRetailPrice(0.80);

        when(basket.getGroceryItems()).thenReturn(new ArrayList<GroceryItem>(Arrays.asList(soup1, bread)));
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        // WHEN the price of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN there should be no discount
        assertEquals(0, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnHalfOfTotalBreadRetailPrice_WhenMultipleSoupsAndBreadsAreInBasketAndPurchaseDate7DaysFromYesterday() {
        // GIVEN the customer places 6 tins of soup and 3 loafs of bread in the basket,
        // and the date of purchase is between yesterday and 7 days after yesterday
        Basket basket = mock(Basket.class);
        List<GroceryItem> soups = new ArrayList<GroceryItem>(GenerateGroceryItemList("Soup", 6, 0.65));
        List<GroceryItem> breads = new ArrayList<GroceryItem>(GenerateGroceryItemList("Bread", 3, 0.80));
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>(soups);
        groceryItems.addAll(breads);
        when(basket.getGroceryItems()).thenReturn(groceryItems);
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);
        Double totalBreadRetail = breads.stream().mapToDouble(bread -> bread.getRetailPrice()).sum();

        // WHEN the prices of the discount is totaled
        Double discountAmount = breadDiscount.getDiscountAmount();

        // THEN the discount amount should be half the total price of all the breads
        assertEquals(totalBreadRetail / 2, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturnZero_WhenOneSoupsAndOneBreadAreInBasketAndPurchaseDate8DaysFromYesterday() {
        // GIVEN the customer places 2 tins of soup and one loaf of bread in the basket,
        // and the date of purchase is 8 days from yesterday
        Basket basket = mock(Basket.class);
        List<GroceryItem> soups = new ArrayList<GroceryItem>(GenerateGroceryItemList("Soup", 2, 0.65));
        List<GroceryItem> breads = new ArrayList<GroceryItem>(GenerateGroceryItemList("Bread", 1, 0.80));
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

    @Test
    void GetDiscountAmount_ShouldReturn40cents_WhenBreadRetailIs80centsAnd2SoupsAnd1BreadPurchasedToday() {
        Basket basket = mock(Basket.class);
        List<GroceryItem> soups = new ArrayList<GroceryItem>(GenerateGroceryItemList("Soup", 2, .65));
        List<GroceryItem> breads = new ArrayList<GroceryItem>(GenerateGroceryItemList("Bread", 1, .80));
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>(soups);
        groceryItems.addAll(breads);
        when(basket.getGroceryItems()).thenReturn(groceryItems);
        LocalDate purchaseDate = LocalDate.now();
        BreadDiscount breadDiscount = new BreadDiscount();
        breadDiscount.setBasket(basket);
        breadDiscount.setPurchaseDate(purchaseDate);

        Double discountAmount = breadDiscount.getDiscountAmount();

        assertEquals(.40, discountAmount);
    }
}
