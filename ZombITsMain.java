import java.util.Random;
import java.util.Scanner;

public class ZombITsMain {
        private static String startingLocation; // Starting location for the player
        private static String currentLocation; // Current location of the player
        private static final String[] locations = { "Pavilion", "ICS New Bldg.", "Student Park", "Cafeteria",
                        "Covered Court", "Clinic" };
        private static final Random random = new Random();
        static int[] locationIndicator = {};

        public static void main(String[] args) {
                Scanner s = new Scanner(System.in);

                // Randomly set the starting location
                startingLocation = locations[random.nextInt(locations.length)];
                currentLocation = startingLocation;

                // Display character animation
                character.displayChar(() -> {
                        // Prompt user to start the game
                        System.out.println();
                        System.out.print("Start the game (1. Yes 2. No): ");
                        int readyChoice = s.nextInt();
                        s.nextLine(); // Consume newline character

                        if (readyChoice == 1) {

                                introStory.introduction();
                                System.out.println("Press enter to continue...");
                                s.nextLine();
                                // Displays the objective
                                introStory.displayIO();

                                // Wait for the user to press enter
                                System.out.println("Press enter to continue...");
                                s.nextLine();

                                // Create instances of ZombITsMain and Player
                                ZombITsMain game = new ZombITsMain();
                                Player player = new Player(3, 0, 100, new Inventory());

                                // Call displayMenu() method with the game and player instances
                                ZombITsMenu.displayMenu(game, player);
                        }
                });

                // Don't close the scanner here
        }

        static void displayCurrentMap() {
                String currentLocation = ZombITsMain.getCurrentLocation();

                switch (currentLocation) {
                        case "Pavilion":
                                locationIndicator = new int[] { 3, 52 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        case "ICS New Bldg.":
                                locationIndicator = new int[] { 13, 13 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        case "Student Park":
                                locationIndicator = new int[] { 13, 51 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        case "Cafeteria":
                                locationIndicator = new int[] { 13, 90 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        case "Covered Court":
                                locationIndicator = new int[] { 22, 13 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        case "Clinic":
                                locationIndicator = new int[] { 22, 51 };
                                gameMap.displayMap(locationIndicator[0], locationIndicator[1]);
                                break;
                        default:
                                System.out.println("Invalid location.");
                }
        }

        // Add a method to update the current location
        public static void moveToLocation(String newLocation) {
                currentLocation = newLocation;
                System.out.println("Moved to " + newLocation);

                displayCurrentMap(); // Display the new map
        }

        // Method to get the starting location
        public static String getStartingLocation() {
                return startingLocation;
        }

        public static String getCurrentLocation() {
                return currentLocation;
        }
}
