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
            // Add more cases for other items if needed
        }
        // Decrease the count of the used item
        items.put(itemName, items.get(itemName) - 1);
    }

    private void restoreLives(Player player) {
        // Restores lives by 1 point
        player.restoreLives(1);
        System.out.println("Used Empanada: Lives restored by 1 point.");
    }

    private void restoreEnergy(Player player) {
        // Restores energy from 10 to 20 points
        int restoredEnergy = new Random().nextInt(11) + 10;
        player.restoreEnergy(restoredEnergy);
        System.out.println("Used Sting: Energy restored by " + restoredEnergy + " points.");
    }

    private void preventLifeLoss(Player player) {
        // No effect for failed math problems or zombie encounters
        System.out.println("Used " + player.getCurrentItem() + ": Prevented life loss.");
    }
}
