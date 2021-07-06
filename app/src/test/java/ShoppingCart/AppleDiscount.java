package ShoppingCart;

import java.time.LocalDate;

public class AppleDiscount {

    private Basket basket;

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
    }

    public Double getDiscountAmount() {
        return basket.getGroceryItems().stream().filter(item -> item.getProduct() == "Apple").count() * .01;
    }

}
