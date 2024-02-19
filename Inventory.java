import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String itemName) {
        items.put(itemName, items.getOrDefault(itemName, 0) + 1);
    }
    
    // Other methods, if needed
}
