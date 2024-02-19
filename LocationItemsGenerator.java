import java.util.Random;

public class LocationItemsGenerator {
    private static final String[] ITEMS = { "Potion", "Energy", "Medicine", "Immortality", "Chuchu_1", "Chuchu_2" };

    public static String generateItem(String location) {
        Random random = new Random();
        // Check if an item is claimable (50% chance)
        if (random.nextBoolean()) {
            // Randomly select an item from the ITEMS array
            int index = random.nextInt(ITEMS.length);
            return ITEMS[index];
        } else {
            return null; // No item to claim
        }
    }
}
