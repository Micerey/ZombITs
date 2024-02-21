import java.util.Random;

public class LocationItemsGenerator {
    private static final String[] ITEMS = { "Empanada", "Sting", "Calculator", "Baseball Bat", "Wooden Planks"};

    public static String generateItem(String location) {
        // Adjust probabilities based on item importance and location
        int index;
        int random = new Random().nextInt(100); // Assuming a range from 0 to 99

        switch (location.toLowerCase()) {
            case "cafeteria":
                // Higher chance for Sting and Empanada in the Cafeteria
                if (random < 40) {
                    index = indexOfItem("Sting");
                } else if (random < 80) {
                    index = indexOfItem("Empanada");
                } else {
                    // Equal chance for other items
                    index = random % (ITEMS.length - 2); // Exclude "Wooden Planks" and "Sting"
                }
                break;

            case "clinic":
                if (random < 75) {
                    index = indexOfItem("Empanada");
                } else {
                    index = random % (ITEMS.length - 2);
                }
                break;
                
            case "covered court":
            case "pavilion":
            case "ics new bldg.":
            case "student park":
                // Higher chance for Wooden Planks in the Covered Court and Pavilion
                if (random < 75) {
                    index = indexOfItem("Wooden Planks");
                } else {
                    // Equal chance for other items
                    index = random % (ITEMS.length - 2); // Exclude "Wooden Planks" and "Sting"
                }
                break;

            default:
                // Default probabilities for other locations
                if (random < 70) {
                    index = indexOfItem("Wooden Planks");
                } else if (random < 30) {
                    index = indexOfItem("Sting");
                } else {
                    // Equal chance for other items
                    index = random % (ITEMS.length - 2); // Exclude "Wooden Planks" and "Sting"
                }
        }

        return ITEMS[index];
    }

    private static int indexOfItem(String item) {
        for (int i = 0; i < ITEMS.length; i++) {
            if (ITEMS[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1; // Item not found
    }
}
