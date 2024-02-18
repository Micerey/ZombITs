import java.util.Random;
import java.util.Scanner;

public class Distance_Stamina {
    public static void main(String[] args) {
        // Define the number of locations
        int numLocations = 6;

        // Define the names of locations
        String[] locationNames = { "ICS New Bldg.", "Pavillion", "Covered Court", "Student Park", "Cafeteria",
                "Clinic" };

        // Define the routes
        String[][] routes = {
                { "ICS New Bldg. to Student Park" },
                { "Student Park to ICS New Bldg." },
                { "Student Park to Pavillion" },
                { "Pavillion to Student Park" },
                { "Student Park to Cafeteria" },
                { "Cafeteria to Student Park" },
                { "Student Park to Clinic" },
                { "Clinic to Student Park" },
                { "Clinic to Covered Court" },
                { "Covered Court to Clinic" }
        };

        // Generate random distances for each consecutive pair of locations
        int[][] distances = generateRandomDistances(numLocations);

        // Initialize stamina
        int stamina = 100;

        // Randomly select current location index
        Random random = new Random();
        int currentLocationIndex = random.nextInt(numLocations);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display current location
            System.out.println("Current Location: " + locationNames[currentLocationIndex]);

            // Display possible routes from the current location
            System.out.println("Possible Routes from " + locationNames[currentLocationIndex] + ":");
            for (String[] route : routes) {
                if (route[0].startsWith(locationNames[currentLocationIndex])) {
                    String[] parts = route[0].split(" to ");
                    int distance = distances[getLocationIndex(locationNames, parts[0])][getLocationIndex(locationNames,
                            parts[1])];
                    System.out.println(route[0] + ", Distance: " + distance);
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

            // Get the distance between current location and destination
            int distance = distances[currentLocationIndex][destinationIndex];

            // Update current location index
            currentLocationIndex = destinationIndex;

            // Decrease stamina
            stamina = decreaseStamina(distance, stamina);
            System.out.println("Remaining Stamina: " + stamina);

            // Check if stamina is depleted
            if (stamina == 0) {
                System.out.println("Your stamina is depleted. You cannot travel further.");
                break;
            }

            System.out.println(); // Blank line
        }
    }

    public static int[][] generateRandomDistances(int numLocations) {
        Random random = new Random();
        int[][] distances = new int[numLocations][numLocations];

        for (int i = 0; i < numLocations; i++) {
            for (int j = i + 1; j < numLocations; j++) {
                // Generate random distance between 1 and 10
                int distance = random.nextInt(10) + 1;

                // Set the distance for both directions (i to j and j to i)
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

        return distances;
    }

    public static int decreaseStamina(int distance, int stamina) {
        // Decrease stamina by the distance
        stamina -= distance;
        // Ensure stamina doesn't go below 0
        if (stamina < 0) {
            stamina = 0;
        }
        return stamina;
    }

    public static int getLocationIndex(String[] locationNames, String location) {
        for (int i = 0; i < locationNames.length; i++) {
            if (locationNames[i].equalsIgnoreCase(location)) {
                return i;
            }
        }
        return -1; // Location not found
    }
}
