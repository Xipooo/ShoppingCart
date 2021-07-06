package ShoppingCart;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

//As Carrie the customer I want to receive discounts on purchases of apples so that I pay less money

public class AppleDiscountTests { 
    private List<GroceryItem> GenerateGroceryItemList(String productType, Integer count, double retailPrice){
        return Stream.generate(()-> {
            GroceryItem gi = new GroceryItem().setProduct(productType);
            gi.setRetailPrice(retailPrice);
            return gi;
        }).limit(count).collect(Collectors.toList());
    }
    
    // GIVEN the customer placed an apple in the basket, and the date of purchase is between 3 days from today and the end of next month
    @Test
    void foo() {
    Basket basket = mock(Basket.class);
    List<GroceryItem> apples = new ArrayList<GroceryItem>(GenerateGroceryItemList("Apple", 1, 0.10));
    when(basket.getGroceryItems()).thenReturn(apples);
    LocalDate purchaseDate = LocalDate.now().plusDays(6);
    AppleDiscount appleDiscount = new AppleDiscount();


    // WHEN the price of the items is totaled
    // THEN the price of the apple should be 10% less than the normal price
    }
    
    // GIVEN the customer placed 5 apples in the basket, and the date of purchase is between 3 days from today and the end of next month
    // WHEN the price of the items is totaled
    // THEN the total price of the apples should be 10% less than the normal total price of the apples
    
    // GIVEN the customer placed an apple in the basket, and the date of purchase is today
    // WHEN the price of the items is totaled
    // THEN the price of the apple should be the normal price
}
