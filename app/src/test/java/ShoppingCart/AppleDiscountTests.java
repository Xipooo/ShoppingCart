package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import jdk.vm.ci.meta.Local;

//As Carrie the customer I want to receive discounts on purchases of apples so that I pay less money

public class AppleDiscountTests {
    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count, double retailPrice) {
        return Stream.generate(() -> {
            GroceryItem gi = new GroceryItem().setProduct(productType);
            gi.setRetailPrice(retailPrice);
            return gi;
        }).limit(count).collect(Collectors.toList());
    }

    @Test
    void GetDiscountAmount_ShouldReturn10PercentOfRetailPrice_WhenOneAppleIsInTheBasket() {
        // GIVEN the customer placed an apple in the basket, and the date of purchase is
        // between 3 days from today and the end of next month
        Basket basket = mock(Basket.class);
        List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 1, 0.10));
        when(basket.getGroceryItems()).thenReturn(apples);
        LocalDate purchaseDate = LocalDate.now().plusDays(6);
        AppleDiscount appleDiscount = new AppleDiscount();
        appleDiscount.setBasket(basket);
        appleDiscount.setPurchaseDate(purchaseDate);
        Double totalAppleRetail = apples.stream().mapToDouble(apple -> apple.getRetailPrice()).sum();

        // WHEN the discount of the items is totaled
        Double discountAmount = appleDiscount.getDiscountAmount();

        // THEN the discount of the apple should be 10% of the normal price
        assertEquals(Math.round((totalAppleRetail * .10) * 100) / 100.00, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturn10PercentOfRetailPrice_When5ApplesAreInTheBasket() {
        // GIVEN the customer placed 5 apples in the basket, and the date of purchase is
        // 3 days from today
        Basket basket = mock(Basket.class);
        List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 5, 0.10));
        when(basket.getGroceryItems()).thenReturn(apples);
        LocalDate purchaseDate = LocalDate.now().plusDays(3);
        AppleDiscount appleDiscount = new AppleDiscount();
        appleDiscount.setBasket(basket);
        appleDiscount.setPurchaseDate(purchaseDate);
        Double totalAppleRetail = apples.stream().mapToDouble(apple -> apple.getRetailPrice()).sum();

        // WHEN the discount of the items is totaled
        Double discountAmount = appleDiscount.getDiscountAmount();

        // THEN the total discount of the apples should be 10% of the normal total
        // price of the apples
        assertEquals(Math.round((totalAppleRetail * .10) * 100) / 100.00, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturn10PercentOfRetailPrice_When5ApplesAreInTheBasketAndPurchaseDateEndOfNextMonth() {
        // GIVEN the customer placed 5 apples in the basket, and the date of purchase is
        // the last day of the end of next month
        Basket basket = mock(Basket.class);
        List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 5, 0.10));
        when(basket.getGroceryItems()).thenReturn(apples);
        LocalDate purchaseDate = LocalDate.now().plusMonths(1)
                .withDayOfMonth(LocalDate.now().plusMonths(1).lengthOfMonth());
        AppleDiscount appleDiscount = new AppleDiscount();
        appleDiscount.setBasket(basket);
        appleDiscount.setPurchaseDate(purchaseDate);
        Double totalAppleRetail = apples.stream().mapToDouble(apple -> apple.getRetailPrice()).sum();

        // WHEN the discount of the items is totaled
        Double discountAmount = appleDiscount.getDiscountAmount();

        // THEN the total discount of the apples should be 10% of the normal total
        // price of the apples
        assertEquals(Math.round((totalAppleRetail * .10) * 100) / 100.00, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturn0Discount_When1AppleisInTheBasketAndPurchaseDate2DaysFromToday() {
        // GIVEN the customer placed an apple in the basket, and the date of purchase is
        // 2 days from today
        Basket basket = mock(Basket.class);
        List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 1, 0.10));
        when(basket.getGroceryItems()).thenReturn(apples);
        LocalDate purchaseDate = LocalDate.now().plusDays(2);
        AppleDiscount appleDiscount = new AppleDiscount();
        appleDiscount.setBasket(basket);
        appleDiscount.setPurchaseDate(purchaseDate);

        // WHEN the discount of the items is totaled
        Double discountAmount = appleDiscount.getDiscountAmount();

        // THEN the discount of the apple should be zero
        assertEquals(0.00, discountAmount);
    }

    @Test
    void GetDiscountAmount_ShouldReturn0Discount_When1AppleisInTheBasketAndPurchaseDate1DayAfterNextMonth() {
        // GIVEN the customer placed an apple in the basket, and the date of purchase is
        // the first day after next month
        Basket basket = mock(Basket.class);
        List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 1, 0.10));
        when(basket.getGroceryItems()).thenReturn(apples);
        LocalDate purchaseDate = LocalDate.now().plusMonths(2).withDayOfMonth(1);
        AppleDiscount appleDiscount = new AppleDiscount();
        appleDiscount.setBasket(basket);
        appleDiscount.setPurchaseDate(purchaseDate);

        // WHEN the discount of the items is totaled
        Double discountAmount = appleDiscount.getDiscountAmount();

        // THEN the discount of the apple should be zero
        assertEquals(0.00, discountAmount);
    }

    // GIVEN the customer placed no apples in the basket, and the date of purchase
    // is three days from today
    // WHEN the discount of the items is totaled
    // THEN the discount of the apples should be zero
}
