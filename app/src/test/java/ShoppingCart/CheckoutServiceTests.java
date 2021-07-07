package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import ShoppingCart.Domain.AppleDiscount;
import ShoppingCart.Domain.Basket;
import ShoppingCart.Domain.BreadDiscount;
import ShoppingCart.Domain.Discount;
import ShoppingCart.Domain.GroceryItem;
import ShoppingCart.Services.CheckoutService;

public class CheckoutServiceTests {
    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count, double retailPrice){
        return Stream.generate(()-> new GroceryItem().setProduct(productType).setRetailPrice(retailPrice)).limit(count).collect(Collectors.toList());
    }
    
    // Price a basket containing: 3 tins of soup and 2 loaves of bread, bought
    // today,
    // Expected total cost = 3.15;
    @Test
    void GetCheckoutTotal_ShouldReturn3DollarsAnd15Cents_When3SoupsAnd2BreadAreInBasketWithPurchaseDateTodayAndBreadAndAppleDiscountsApplied() {
        Basket customerBasket = new Basket(new ArrayList<GroceryItem>());
        List<GroceryItem> soups = GenerateGroceryItemList("Soup", 3, 0.65);
        List<GroceryItem> breads = GenerateGroceryItemList("Bread", 2, 0.80);
        customerBasket.Add(soups);
        customerBasket.Add(breads);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setBasket(customerBasket);

        List<Discount> discounts = new ArrayList<Discount>();
        discounts.add(new AppleDiscount());
        discounts.add(new BreadDiscount());

        checkoutService.setDiscounts(discounts);
        checkoutService.setPurchaseDate(LocalDate.now());

        double checkoutTotal = checkoutService.getCheckoutTotal();

        assertEquals(3.15, checkoutTotal);
    }

    // Price a basket containing: 6 apples and a bottle of milk, bought today,
    // Expected total cost = 1.90;
    @Test
    void GetCheckoutTotal_ShouldReturn1DollarAnd90Cents_When6ApplesAnd1MilkAreInBasketWithPurchaseDateTodayAndBreadAndAppleDiscountsApplied() {
        Basket customerBasket = new Basket(new ArrayList<GroceryItem>());
        List<GroceryItem> apples = GenerateGroceryItemList("Apple", 6, 0.10);
        List<GroceryItem> milk = GenerateGroceryItemList("Milk", 1, 1.30);
        customerBasket.Add(apples);
        customerBasket.Add(milk);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setBasket(customerBasket);

        List<Discount> discounts = new ArrayList<Discount>();
        discounts.add(new AppleDiscount());
        discounts.add(new BreadDiscount());

        checkoutService.setDiscounts(discounts);
        checkoutService.setPurchaseDate(LocalDate.now());

        double checkoutTotal = checkoutService.getCheckoutTotal();

        assertEquals(1.90, checkoutTotal);
    }

    // Price a basket containing: 6 apples and a bottle of milk, bought in 5 days
    // time,
    // Expected total cost = 1.84;
    @Test
    void GetCheckoutTotal_ShouldReturn1DollarAnd84Cents_When6ApplesAnd1MilkAreInBasketWithPurchaseDate5DaysFromTodayAndBreadAndAppleDiscountsApplied() {
        Basket customerBasket = new Basket(new ArrayList<GroceryItem>());
        List<GroceryItem> apples = GenerateGroceryItemList("Apple", 6, 0.10);
        List<GroceryItem> milk = GenerateGroceryItemList("Milk", 1, 1.30);
        customerBasket.Add(apples);
        customerBasket.Add(milk);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setBasket(customerBasket);

        List<Discount> discounts = new ArrayList<Discount>();
        discounts.add(new AppleDiscount());
        discounts.add(new BreadDiscount());

        checkoutService.setDiscounts(discounts);
        checkoutService.setPurchaseDate(LocalDate.now().plusDays(5));

        double checkoutTotal = checkoutService.getCheckoutTotal();

        assertEquals(1.84, checkoutTotal);
    }

    // Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread,
    // bought in 5 days time,
    // Expected total cost = 1.97.
    @Test
    void GetCheckoutTotal_ShouldReturn1DollarAnd97Cents_When3ApplesAnd2SoupsAnd1BreadAreInBasketWithPurchaseDate5DaysFromTodayAndBreadAndAppleDiscountsApplied(){
        Basket customerBasket = new Basket(new ArrayList<GroceryItem>());
        List<GroceryItem> apples = GenerateGroceryItemList("Apple", 3, 0.10);
        List<GroceryItem> soups = GenerateGroceryItemList("Soup", 2, 0.65);
        List<GroceryItem> bread = GenerateGroceryItemList("Bread", 1, 0.80);
        customerBasket.Add(apples);
        customerBasket.Add(soups);
        customerBasket.Add(bread);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setBasket(customerBasket);

        List<Discount> discounts = new ArrayList<Discount>();
        discounts.add(new AppleDiscount());
        discounts.add(new BreadDiscount());

        checkoutService.setDiscounts(discounts);
        checkoutService.setPurchaseDate(LocalDate.now().plusDays(5));

        double checkoutTotal = checkoutService.getCheckoutTotal();

        assertEquals(1.97, checkoutTotal);
    }
}
