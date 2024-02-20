import java.util.Scanner;
import java.util.Random;

public class PlayerMovement {
    public static String moveWithinMap(ZombITsMain game, Player player, String startingLocation) {
        int numLocations = 6;
        String[] locationNames = { "ICS New Bldg.", "Pavilion", "Covered Court", "Student Park", "Cafeteria",
                "Clinic" };
        String[][] routes = { { "ICS New Bldg. to Student Park" }, { "Student Park to ICS New Bldg." },
                { "Student Park to Pavilion" }, { "Pavilion to Student Park" }, { "Student Park to Cafeteria" },
                { "Cafeteria to Student Park" }, { "Student Park to Clinic" }, { "Clinic to Student Park" },
                { "Clinic to Covered Court" }, { "Covered Court to Clinic" } };
        int[][] distances = generateRandomDistances(numLocations);

        Scanner scanner = new Scanner(System.in);

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

            // Prompt user to choose a location
            System.out.println("Enter the destination location (type 'exit' to quit): ");
            String destination = scanner.nextLine().trim();

            // Check if the user wants to exit
            if (destination.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            // Check if the destination location is reachable from the current location
            boolean canProceed = false;
            for (String[] route : routes) {
                if (route[0].startsWith(locationNames[currentLocationIndex]) && route[0].endsWith(destination)) {
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
                break;
            }

            ZombieEncounter.encounterZombie(player);

            // Check if the location has an unsolved math problem
            if (!LocationMathProblems.hasSolvedProblem(locationNames[currentLocationIndex])) {
                LocationMathProblems.generateMathProblem(locationNames[currentLocationIndex], player);

                // Generate an item for the location
                String obtainedItem = LocationItemsGenerator.generateItem(locationNames[currentLocationIndex]);

                // Display the obtained item
                if (obtainedItem != null) {
                    System.out.println("You found an item: " + obtainedItem);
                    player.getInventory().addItem(obtainedItem); // Add the item to the player's inventory
                } else {
                    System.out.println("No item found in this location.");
                }
            }

            System.out.println(); // Blank line1

            System.out.println();

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
}
