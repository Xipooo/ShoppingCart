package ShoppingCart;

import java.util.List;

public class Basket {

    private List<GroceryItem> groceryItems;

    public Basket(List<GroceryItem> initialGroceryItems) {
        groceryItems = initialGroceryItems;
    }

    public void Add(List<GroceryItem> apples) {
        groceryItems.addAll(apples);
    }

    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

}
