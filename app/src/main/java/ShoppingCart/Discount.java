package ShoppingCart;

import java.time.LocalDate;

public interface Discount {
    void setBasket(Basket basket);

    void setPurchaseDate(LocalDate purchaseDate);

    Double getDiscountAmount();
}
