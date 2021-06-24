package ShoppingCart;

public class GroceryItem {

    public GroceryItem setProduct(String productName) {
        if (productName == "") throw new IllegalArgumentException();
        return this;
    }

    public Double getRetailPrice() {
        return 0.00;
    }

}
