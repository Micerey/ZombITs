import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Item {
    private static final Map<String, Integer> itemValues = new HashMap<>();

    static {
        // Initialize values of each item to 2
        itemValues.put("potion", 2);
        itemValues.put("energy", 2);
        itemValues.put("medicine", 2);
        itemValues.put("immortality", 2);
        itemValues.put("chuchu_1", 2);
        itemValues.put("chuchu_2", 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print the initial values of each item
        System.out.println("Initial Item Values:");
        printItemValues();

        //loop until answered right
        while (true) {
            System.out.print("Do you want to use an item? (y/n): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                // Ask the user for the item they want to use
                String itemName;
                while (true) {
                    System.out.print("Enter the name of the item you want to use: ");
                    itemName = scanner.nextLine().toLowerCase();

                    // Check if the item exists
                    if (itemValues.containsKey(itemName)) {
                        break; // Exit the loop if the item name is valid
                    } else {
                        System.out.println("Invalid item name. Please try again.");
                    }
                }

                // Decrement the value of the item by 1 if it's greater than 0
                int value = itemValues.get(itemName);
                if (value > 0) {
                    itemValues.put(itemName, value - 1);
                    System.out.println(itemName + " has been used.");
                    // Print the updated values of all items
                    System.out.println("Updated Item Values:");
                    printItemValues();
                } else {
                    System.out.println("You have no more " + itemName + " left.");
                }
            } else if (choice.equalsIgnoreCase("n")) {
                break; // Exit the loop if the user doesn't want to use an item
            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }
        }

        scanner.close();
    }

    // Method to print the values of all items
    private static void printItemValues() {
        for (Map.Entry<String, Integer> entry : itemValues.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
