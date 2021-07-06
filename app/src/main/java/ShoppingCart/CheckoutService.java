package ShoppingCart;

import java.time.LocalDate;
import java.util.List;

public class CheckoutService {

    private Basket basket;
    private List<Discount> discounts;
    private LocalDate purchaseDate;

    public void setBasket(Basket customerBasket) {
        this.basket = customerBasket;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public double getCheckoutTotal() {
        double totalRetail = basket.getGroceryItems().stream().mapToDouble(groceryItem -> groceryItem.getRetailPrice()).sum();
        double discountAmount = discounts.stream().mapToDouble(
            discount -> {
                discount.setBasket(basket);
                discount.setPurchaseDate(purchaseDate);
                return discount.getDiscountAmount();
            }
        ).sum();
        return Math.round((totalRetail - discountAmount) * 100) / 100.00;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
