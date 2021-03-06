package ShoppingCart.Domain;

import java.time.LocalDate;

public class BreadDiscount implements Discount {

    private LocalDate purchaseDate;
    private Basket basket;

    public BreadDiscount setBasket(Basket basket) {
        this.basket = basket;
        return this;
    }

    public BreadDiscount setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Double getDiscountAmount() {
        if (purchaseDate.isBefore(LocalDate.now().minusDays(1)) || purchaseDate.isAfter(LocalDate.now().plusDays(6))) return 0.0;
        long soupCount = basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Soup").count();
        long breadCount = basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Bread").count();
        if (soupCount < 2) return 0.0;
        if (breadCount < 1) return 0.0;
        long discountsToApply = soupCount / 2 < breadCount ? soupCount / 2 : breadCount;
        return 0.40 * discountsToApply;
    }

}
