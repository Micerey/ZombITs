import java.util.Random;

public class Distance {
    public static void main(String[] args) {
        // Define the number of locations
        int numLocations = 6;

        // Generate random distances for each consecutive pair of locations
        int[][] distances = generateRandomDistances(numLocations);

        // Display distances between consecutive locations
        for (int i = 0; i < numLocations - 1; i++) {
            System.out.println(
                    "Distance between location " + (i + 1) + " and location " + (i + 2) + ": " + distances[i][i + 1]);
        }
    }

    public static int[][] generateRandomDistances(int numLocations) {
        Random random = new Random();
        int[][] distances = new int[numLocations][numLocations];

        // Initialize variables to keep track of total distance and remaining distance
        int totalDistance = 0;
        int remainingDistance = 100;

        for (int i = 0; i < numLocations - 1; i++) {
            // Generate random distance between 1 and the remaining distance, ensuring it
            // doesn't exceed 40
            int maxDistance = Math.min(remainingDistance, 40);
            int distance = random.nextInt(maxDistance) + 1;

            // Update remaining distance and total distance
            remainingDistance -= distance;
            totalDistance += distance;

            // Set the distance for both directions (i to i+1 and i+1 to i)
            distances[i][i + 1] = distance;
            distances[i + 1][i] = distance;
        }

        // Adjust the last distance to ensure the total distance does not exceed 100
        distances[numLocations - 1][0] = 100 - totalDistance;
        distances[0][numLocations - 1] = 100 - totalDistance;

        return distances;
    }
}
