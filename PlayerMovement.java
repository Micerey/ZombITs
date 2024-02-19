import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class PlayerMovement {
    private static final double MILES_TO_KM = 1.60934; // Conversion factor from miles to kilometers

    public static void moveWithinMap(Player player) {
        // Define the number of locations
        int numLocations = 6;

        // Define the names of locations
        String[] locationNames = { "ICS New Bldg.", "Pavilion", "Covered Court", "Student Park", "Cafeteria", "Clinic" };

        // Define the routes
        String[][] routes = { { "ICS New Bldg. to Student Park" }, { "Student Park to ICS New Bldg." },
                { "Student Park to Pavilion" }, { "Pavilion to Student Park" }, { "Student Park to Cafeteria" },
                { "Cafeteria to Student Park" }, { "Student Park to Clinic" }, { "Clinic to Student Park" },
                { "Clinic to Covered Court" }, { "Covered Court to Clinic" } };

        // Generate random distances for each consecutive pair of locations
        double[][] distances = generateRandomDistances(numLocations);

        // Randomly select current location index
        Random random = new Random();
        int currentLocationIndex = random.nextInt(numLocations);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display current location
            System.out.println("\nCurrent Location: " + locationNames[currentLocationIndex] + "\n");

            // Sort routes based on distance
            Arrays.sort(routes, Comparator.comparing(route -> {
                String[] parts = route[0].split(" to ");
                return distances[getLocationIndex(locationNames, parts[0])][getLocationIndex(locationNames, parts[1])];
            }));

            // Display possible routes from the current location
            System.out.println("Possible Routes from " + locationNames[currentLocationIndex] + ":");
            for (String[] route : routes) {
                if (route[0].startsWith(locationNames[currentLocationIndex])) {
                    String[] parts = route[0].split(" to ");
                    double distance = distances[getLocationIndex(locationNames, parts[0])][getLocationIndex(locationNames,
                            parts[1])];
                    System.out.printf("%s, Distance: %.0f km\n", route[0], distance);
                }
            }

            // Prompt user to choose a location
            System.out.println("\nEnter the destination location (type 'exit' to quit): ");
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

            // Get the distance between current location and destination
            double distance = distances[currentLocationIndex][destinationIndex];

            // Update current location index
            currentLocationIndex = destinationIndex;

            // Decrease stamina
            player.updateStamina((int) distance);

            System.out.println("Remaining Stamina: " + player.getStamina());

            // Check if stamina is depleted
            if (player.getStamina() == 0) {
                System.out.println("Your stamina is depleted. You cannot travel further.");
                break;
            }

            ZombieEncounter.encounterZombie();

            // Check if the location has an unsolved math problem
            if (!LocationMathProblems.hasSolvedProblem(locationNames[currentLocationIndex])) {
                LocationMathProblems.generateMathProblem(locationNames[currentLocationIndex]);
            }

            System.out.println(); // Blank line
        }
    }

    private static double[][] generateRandomDistances(int numLocations) {
        Random random = new Random();
        double[][] distances = new double[numLocations][numLocations];

        for (int i = 0; i < numLocations; i++) {
            for (int j = i + 1; j < numLocations; j++) {
                // Generate random distance between 1 and 10 (in miles) and convert to km
                double distanceInMiles = random.nextInt(10) + 1;
                double distanceInKm = distanceInMiles * MILES_TO_KM;

                // Cast distance to integer to remove decimals
                int distanceInKmInt = (int) distanceInKm;

                // Set the distance for both directions (i to j and j to i)
                distances[i][j] = distanceInKmInt;
                distances[j][i] = distanceInKmInt;
            }
        }

        return distances;
    }

    private static int decreaseStamina(int distance, int stamina) {
        // Decrease stamina by the distance
        stamina -= distance;
        // Ensure stamina doesn't go below 0
        if (stamina < 0) {
            stamina = 0;
        }
        return stamina;
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
