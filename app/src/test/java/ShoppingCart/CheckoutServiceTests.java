package ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class CheckoutServiceTests {
    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count, double retailPrice){
        return Stream.generate(()-> {
            GroceryItem gi = new GroceryItem().setProduct(productType);
            gi.setRetailPrice(retailPrice);
            return gi;
        }).limit(count).collect(Collectors.toList());
    }
    
    // Price a basket containing: 3 tins of soup and 2 loaves of bread, bought
    // today,
    // Expected total cost = 3.15;
    @Test
    void foo() {
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

        double checkoutTotal = checkoutService.getCheckoutTotal();

        assertEquals(3.15, checkoutTotal);
    }

    // Price a basket containing: 6 apples and a bottle of milk, bought today,
    // Expected total cost = 1.90;

    // Price a basket containing: 6 apples and a bottle of milk, bought in 5 days
    // time,
    // Expected total cost = 1.84;

    // Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread,
    // bought in 5 days time,
    // Expected total cost = 1.97.
}
