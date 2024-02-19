import java.util.Map;

public class PlayerInformation {
    public static void displayPlayerInfo(Player player) {
        System.out.println("Player Information:");
        System.out.println("Lives: " + player.getLives());
        System.out.println("Progress: " + player.getProgress());
        System.out.println("Stamina: " + player.getStamina());
        System.out.println("Inventory:");
        displayInventory(player.getInventory());
    }

    private static void displayInventory(Inventory inventory) {
        for (Map.Entry<String, Integer> entry : inventory.getItems().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
