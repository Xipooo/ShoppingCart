package ShoppingCart.Domain;

public class GroceryItem {

    private String productName;
    private double retailPrice;

    public GroceryItem setProduct(String productName) {
        if (productName == "") throw new IllegalArgumentException("Product name must not be an empty string.");
        this.productName = productName;
        return this;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public String getProduct() {
        return productName;
    }

    public GroceryItem setRetailPrice(double retailPrice) {
        if (retailPrice < 0.00) throw new IllegalArgumentException("Retail Price must be greater than 0.");
        this.retailPrice = retailPrice;
        return this;
    }

}
