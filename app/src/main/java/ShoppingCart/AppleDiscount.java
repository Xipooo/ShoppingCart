package ShoppingCart;

import java.time.LocalDate;

public class AppleDiscount {

    private Basket basket;
    private LocalDate purchaseDate;

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getDiscountAmount() {
        if (purchaseDate.isBefore(LocalDate.now().plusDays(3)) || 
            purchaseDate.isAfter(LocalDate.now().plusMonths(1).withDayOfMonth(LocalDate.now().plusMonths(1).lengthOfMonth()))) return 0.00;
        return basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Apple").count() * .01;
    }

}
