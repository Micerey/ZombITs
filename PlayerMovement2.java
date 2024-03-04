import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerMovement {
    private static Scanner scanner = new Scanner(System.in);

    public static String moveWithinMap(ZombITsMain game, Player player, String startingLocation) {
        int numLocations = 6;
        String[] locationNames = { "ICS New Bldg.", "Pavilion", "Covered Court", "Student Park", "Cafeteria", "Clinic" };
        String[][] routes = { { "ICS New Bldg. to Student Park" }, { "Student Park to ICS New Bldg." },
                { "Student Park to Pavilion" }, { "Pavilion to Student Park" }, { "Student Park to Cafeteria" },
                { "Cafeteria to Student Park" }, { "Student Park to Clinic" }, { "Clinic to Student Park" },
                { "Clinic to Covered Court" }, { "Covered Court to Clinic" } };
        int[][] distances = generateRandomDistances(numLocations);

        // Check if the player already has a current location
        String currentLocation = startingLocation;
        int currentLocationIndex = getLocationIndex(locationNames, startingLocation);

        while (true) {
            // Display current location
            System.out.println("Current Location: " + locationNames[currentLocationIndex]);
            System.out.println();

            // Display possible routes from the current location
            System.out.println("Possible Routes from " + locationNames[currentLocationIndex] + ":");
            for (String[] route : routes) {
                if (route[0].startsWith(locationNames[currentLocationIndex])) {
                    String[] parts = route[0].split(" to ");
                    int distance = distances[getLocationIndex(locationNames, parts[0])][getLocationIndex(locationNames,
                            parts[1])];
                    System.out.println(locationNames[getLocationIndex(locationNames, parts[0])] + " ---> " +
                            locationNames[getLocationIndex(locationNames, parts[1])] + ", Distance: " + distance);
                    System.out.println();
                }
            }

            // Check if the player has 4 counts of wooden planks
            if (player.hasEnoughWoodenPlanks(4)) {
                // Ask the user if they want to start the final wave
                System.out.println(
                        "You have 4 wooden planks. Do you want to go back to ICS New Bldg. to start the final wave against the horde of zombies? (1. Yes, 2. No)");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 1 for Yes or 2 for No.");
                    scanner.nextLine(); // Consume invalid input
                    continue; // Restart the loop or handle the situation as needed
                }

                // Rest of your code based on the user's choice
                if (choice == 1) {
                    // Move back to ICS New Bldg. to start the final wave
                    game.moveToLocation("ICS New Bldg.");

                    // Start the final wave against the horde of zombies
                    FinalWave finalWave = new FinalWave();
                    finalWave.startFinalWave(player);

                    // Break out of the loop to end the game or continue based on your logic
                    break;
                } else if (choice == 2) {
                    // Handle the case when the player chooses not to start the final wave
                    // ...
                    break; // Break out of the inner loop
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                }
            }

            // Prompt user to choose a location
            System.out.println("Enter the destination location (type 'exit' to quit): ");
            String destination = scanner.nextLine().trim();

            // Check if the user wants to exit
            if (destination.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            // Consume any remaining input
            scanner.nextLine();

            // Check if the destination location is reachable from the current location (case-insensitive)
            boolean canProceed = false;
            for (String[] route : routes) {
                if (route[0].startsWith(locationNames[currentLocationIndex])
                        && route[0].toLowerCase().endsWith(destination.toLowerCase())) {
                    canProceed = true;
                    break;
                }
            }

            if (!canProceed) {
                System.out.println("You can't proceed to that location from here.");
                continue;
            }

            // Get the index of the destination location
            int destinationIndex = getLocationIndex(locationNames, destination);

            // Get the distance between the current location and destination
            int distance = distances[currentLocationIndex][destinationIndex];

            // Update the current location index
            currentLocationIndex = destinationIndex;

            // Call moveToLocation to update the current location in ZombITsMain
            game.moveToLocation(locationNames[currentLocationIndex]);

            // Set the current location in the player object
            player.setCurrentLocation(locationNames[currentLocationIndex]);

            // Decrease stamina
            player.updateStamina(distance);

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Remaining Stamina: " + player.getStamina());
            System.out.println();

            // Check if stamina is depleted
            if (player.getStamina() == 0) {
                System.out.println("Your stamina is depleted. You cannot travel further.");
                System.out.println(
                        "You have run out of stamina and are forced to stay here overnight. You have lost an item...");

                removeRandomItem(player);
                player.setStamina(100);
                System.out.println("Stamina restored.");

                break;
            }

            ZombieEncounter.encounterZombie(player);

            // Generate an item for the location
            String obtainedItem = LocationItemsGenerator.generateItem(locationNames[currentLocationIndex]);

            // Display the obtained item
            if (obtainedItem != null) {
                System.out.println("You found an item: " + obtainedItem);
                player.getInventory().addItem(obtainedItem); // Add the item to the player's inventory
            } else {
                System.out.println("No item found in this location.");
            }

            System.out.println(); // Blank line

            // Check if the player has lives remaining
            if (player.getLives() == 0) {
                System.out.println("Game over! You have run out of lives.");
                break;
            }
        }
        return locationNames[currentLocationIndex];
    }

    private static int[][] generateRandomDistances(int numLocations) {
        Random random = new Random();
        int[][] distances = new int[numLocations][numLocations];

        for (int i = 0; i < numLocations; i++) {
            for (int j = i + 1; j < numLocations; j++) {
                // Generate random distance between 10 and 20
                int distance = random.nextInt(11) + 10;

                // Set the distance for both directions (i to j and j to i)
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

        return distances;
    }

    private static int getLocationIndex(String[] locationNames, String location) {
        for (int i = 0; i < locationNames.length; i++) {
            if (locationNames[i].equalsIgnoreCase(location)) {
                return i;
            }
        }
        return -1; // Location not found
    }

    private static void removeRandomItem(Player player) {
        Map<String, Integer> items = player.getInventory().getItems();
        if (!items.isEmpty()) {
            List<String> itemList = new ArrayList<>(items.keySet());
            Random random = new Random();
            String removedItem = itemList.get(random.nextInt(itemList.size()));

            // Remove the item from the inventory
            player.getInventory().removeItem(removedItem);

            System.out.println("You lost the item: " + removedItem);
        } else {
            System.out.println("You don't have any items to lose.");
        }
    }
}
