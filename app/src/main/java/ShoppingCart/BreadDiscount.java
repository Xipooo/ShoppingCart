package ShoppingCart;

import java.time.LocalDate;

public class BreadDiscount {

    private LocalDate purchaseDate;
    private Basket basket;

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getDiscountAmount() {
        if (purchaseDate.isBefore(LocalDate.now().minusDays(1)) || purchaseDate.isAfter(LocalDate.now().plusDays(6))) return 0.0;
        long soupCount = basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Soup").count();
        long breadCount = basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Bread").count();
        if (soupCount < 2) return 0.0;
        if (breadCount < 1) return 0.0;
        long discountsToApply = soupCount / 2 > breadCount ? soupCount / 2 : breadCount;
        return 0.40 * discountsToApply;
    }

}
