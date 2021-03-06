package ShoppingCart.Domain;

import java.time.LocalDate;

public class AppleDiscount implements Discount {

    private Basket basket;
    private LocalDate purchaseDate;

    public AppleDiscount setBasket(Basket basket) {
        this.basket = basket;
        return this;
    }

    public AppleDiscount setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Double getDiscountAmount() {
        if (purchaseDate.isBefore(LocalDate.now().plusDays(3)) || 
            purchaseDate.isAfter(LocalDate.now().plusMonths(1).withDayOfMonth(LocalDate.now().plusMonths(1).lengthOfMonth()))) return 0.00;
        return basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Apple").count() * .01;
    }

}
