package ShoppingCart;

public class GroceryItem {

    private String productName;

    public GroceryItem setProduct(String productName) {
        if (productName == "") throw new IllegalArgumentException();
        this.productName = productName;
        return this;
    }

    public Double getRetailPrice() {
        return 0.00;
    }

    public String getProduct() {
        return productName;
    }

}
