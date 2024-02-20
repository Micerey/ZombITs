import java.util.Random;

public class LocationItemsGenerator {
    private static final String[] ITEMS = { "Empanada", "Sting", "Calculator", "Baseball Bat", "Building Materials"};

    public static String generateItem(String location) {
        // Remove the random check to guarantee an item
        int index = new Random().nextInt(ITEMS.length);
        return ITEMS[index];
    }
}
