package ShoppingCart.Domain;

import java.time.LocalDate;

public interface Discount {
    Discount setBasket(Basket basket);

    Discount setPurchaseDate(LocalDate purchaseDate);

    Double getDiscountAmount();
}
