import java.util.Scanner;
import java.util.Random;

public class location {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        // array for the locations
        String[] locations = { "Pavilion", "ICS New Building", "Clinic", "Student Park", "Covered Court", "Cafeteria" };

        // randomly select current location and destination
        int currentLocIndx = r.nextInt(locations.length);
        int destinationIndx = r.nextInt(locations.length);

        while (destinationIndx == currentLocIndx) {
            destinationIndx = r.nextInt(destinationIndx);
        }

        // stores the current location and the new destination
        String currentLocation = locations[currentLocIndx];
        String destination = locations[destinationIndx];

        // displays the output
        System.out.println("You are currently at the " + currentLocation + ".");
        System.out.println("You are going to the " + destination + " from the " + currentLocation + ".");

        // call function to display possible ways
        displayPossibleWays(currentLocation, destination);

        s.close();
    }

    public static void displayPossibleWays(String currentLocation, String destination) {
        String[][] possibleWays = {
                { "ICS New Building", "Student Park", "Covered Court" },
                { "Pavilion", "Cafeteria", "Covered Court" },
                { "Pavilion", "Cafeteria", "Clinic" },
                { "Student Park", "Cafeteria", "Covered Court" },
                { "Pavilion", "ICS New Building", "Covered Court" },
                { "Pavilion", "Cafeteria", "ICS New Building" }
        };

        // print possible ways to move to destination
        System.out.println("Possible ways to get to " + destination + ".");

        for (String way : possibleWays[getLocation(currentLocation)]) {
            if (way != currentLocation) {
                System.out.println(currentLocation + " >> " + way + " >> " + destination);
            }
            ;
        }
    }

    // function to get the location
    public static int getLocation(String location) {
        String[] locations = { "Pavilion", "ICS New Building", "Clinic", "Student Park", "Covered Court", "Cafeteria" };
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].equals(location)) {
                return i;
            }
        }
        return -1;
    }
}
