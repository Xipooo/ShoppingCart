package ShoppingCart.Domain;

import java.util.List;

public class Basket {

    private List<GroceryItem> groceryItems;

    public Basket(List<GroceryItem> initialGroceryItems) {
        if (initialGroceryItems == null) { throw new IllegalArgumentException("Argument must be a List of Grocery Items."); }
        groceryItems = initialGroceryItems;
    }

    public void Add(List<GroceryItem> newGroceryItems) throws IllegalArgumentException {
        if (newGroceryItems == null) { throw new IllegalArgumentException("Argument must be a List of Grocery Items."); }
        groceryItems.addAll(newGroceryItems);
    }

    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

}
