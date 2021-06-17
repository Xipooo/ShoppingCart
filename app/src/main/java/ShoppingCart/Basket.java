package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<GroceryItem> groceryItems;

    public Basket(ArrayList<GroceryItem> initialGroceryItems) {
        groceryItems = initialGroceryItems;
    }

    public void Add(ArrayList<GroceryItem> newGroceryItems) {
        groceryItems.addAll(newGroceryItems);
    }

    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

}
