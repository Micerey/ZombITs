import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public void useItem(String itemName, Player player) {
        switch (itemName) {
            case "Empanada":
                restoreLives(player);
                break;
            case "Sting":
                restoreEnergy(player);
                break;
            case "Calculator":
                preventLifeLoss(player);
                break;
            case "Baseball Bat":
                preventLifeLoss(player);
                break;
        }

        // Decrease the count of the used item
        if (items.containsKey(itemName)) {
            int itemCount = items.get(itemName);
            if (itemCount > 1) {
                items.put(itemName, itemCount - 1);
            } else {
                items.remove(itemName);
            }
        }
    }

    private void restoreLives(Player player) {
        // Restores lives by 1 point
        player.restoreLives(1);
        System.out.println("Used Empanada: Lives restored by 1 point.");
    }

    private void restoreEnergy(Player player) {
        // Restores energy from 20 to 30 points
        int restoredEnergy = new Random().nextInt(21) + 10;
        player.restoreEnergy(restoredEnergy);
        System.out.println("Used Sting: Energy restored by " + restoredEnergy + " points.");
    }

    private void preventLifeLoss(Player player) {
        // No effect for failed math problems or zombie encounters
        System.out.println("Used " + player.getCurrentItem() + ": Prevented life loss.");
    }

    public void removeItem(String itemName) {
        if (items.containsKey(itemName)) {
            int itemCount = items.get(itemName);
            if (itemCount > 1) {
                items.put(itemName, itemCount - 1);
            } else {
                items.remove(itemName);
            }
        }
    }
    
}
