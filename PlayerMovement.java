import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

public class PlayerMovement {
    
    private static Scanner scanner = new Scanner(System.in);
    private static String[][] routes = new String[0][0]; // Default initialization
    
    public static String moveWithinMap(ZombITsMain game, Player player, String startingLocation) {
        int numLocations = 6;
        String[] locationNames = { "ICS New Bldg.", "Pavilion", "Covered Court", "Student Park", "Cafeteria",
                "Clinic" };
        String[][] Destination = {
            { "ICS New Bldg. to Student Park" }, { "Student Park to ICS New Bldg." },
            { "ICS New Bldg. to Pavilion" }, { "Pavilion to ICS New Bldg." },
            { "ICS New Bldg. to Covered Court" }, { "Covered Court to ICS New Bldg." },
            { "ICS New Bldg. to Cafeteria" }, { "Cafeteria to ICS New Bldg." },
            { "ICS New Bldg. to Clinic" }, { "Clinic to ICS New Bldg." },
            { "Student Park to Pavilion" }, { "Pavilion to Student Park" },
            { "Student Park to Covered Court" }, { "Covered Court to Student Park" },
            { "Student Park to Cafeteria" }, { "Cafeteria to Student Park" },
            { "Student Park to Clinic" }, { "Clinic to Student Park" },
            { "Pavilion to Covered Court" }, { "Covered Court to Pavilion" },
            { "Pavilion to Cafeteria" }, { "Cafeteria to Pavilion" },
            { "Pavilion to Clinic" }, { "Clinic to Pavilion" },
            { "Covered Court to Cafeteria" }, { "Cafeteria to Covered Court" },
            { "Covered Court to Clinic" }, { "Clinic to Covered Court" },
            { "Cafeteria to Clinic" }, { "Clinic to Cafeteria" }
        };
        String[][] routes_ics = {
            // Routes from ICS to Covered Court
            { "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Student Park", "Student Park to Clinic", "Clinic to Covered Court" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" },
        
            // Routes from ICS to Clinic
            { "ICS New Bldg. to Pavilion", "Pavilion to Student Park", "Student Park to Clinic" },
            { "ICS New Bldg. to Student Park", "Student Park to Clinic" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Student Park", "Student Park to Clinic" },
        
            // Routes from ICS to Student Park
            { "ICS New Bldg. to Student Park" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Student Park" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Student Park" },
        
            // Routes from ICS to Cafeteria
            { "ICS New Bldg. to Student Park", "Student Park to Cafeteria" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Student Park", "Student Park to Cafeteria" },
        
            // Routes from ICS to Pavilion
            { "ICS New Bldg. to Pavilion" },
            { "ICS New Bldg. to Student Park", "Student Park to Pavilion" },
            { "ICS New Bldg. to Student Park", "Student Park to Clinic", "Clinic to Pavilion" }
        };
        

        String[][] routes_sp = {
            // Routes from Student Park to ICS
            { "Student Park to ICS New Bldg." },
            { "Student Park to Pavilion", "Pavilion to ICS New Bldg." },
        
            // Routes from Student Park to Cafeteria
            { "Student Park to Cafeteria" },
        
            // Routes from Student Park to Pavilion
            { "Student Park to Pavilion" },
            { "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion" },
            { "Student Park to Clinic", "Clinic to Pavilion" },
        
            // Routes from Student Park to Clinic
            { "Student Park to Clinic" },
            { "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Clinic" },
            { "Student Park to Pavilion", "Pavilion to Clinic" },
        
            // Routes from Student Park to Covered Court
            { "Student Park to Clinic", "Clinic to Covered Court" },
            { "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" },
            { "Student Park to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" }
        };
        

        String[][] routes_cafeteria = {
            // Routes from Cafeteria to Student Park
            { "Cafeteria to Student Park" },
        
            // Routes from Cafeteria to ICS
            { "Cafeteria to Student Park", "Student Park to ICS New Bldg." },
            { "Cafeteria to Student Park", "Student Park to Clinic", "Clinic to Pavilion", "Pavilion to ICS New Bldg." },
            { "Cafeteria to Pavilion", "Pavilion to ICS New Bldg." },
            { "Cafeteria to Student Park", "Student Park to Pavilion", "Pavilion to Clinic", "Clinic to Student Park", "Student Park to ICS New Bldg." },
            { "Cafeteria to Student Park", "Student Park to Pavilion", "Pavilion to Clinic", "Clinic to Student Park", "Student Park to Pavilion", "Pavilion to ICS New Bldg." },
        
            // Routes from Cafeteria to Pavilion
            { "Cafeteria to Student Park", "Student Park to Pavilion" },
            { "Cafeteria to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion" },
            { "Cafeteria to Student Park", "Student Park to Clinic", "Clinic to Pavilion" },
        
            // Routes from Cafeteria to Clinic
            { "Cafeteria to Student Park", "Student Park to Clinic" },
            { "Cafeteria to Student Park", "Student Park to Pavilion", "Pavilion to Clinic" },
            { "Cafeteria to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Clinic" },
        
            // Routes from Cafeteria to Covered Court
            { "Cafeteria to Student Park", "Student Park to Clinic", "Clinic to Covered Court" },
            { "Cafeteria to Student Park", "Student Park to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" },
            { "Cafeteria to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Clinic", "Clinic to Covered Court" }
        };
        

        String[][] routes_pavilion = {
            // Routes from Pavilion to ICS
            { "Pavilion to ICS New Bldg." },
            { "Pavilion to Student Park", "Student Park to ICS New Bldg." },
            { "Pavilion to Student Park", "Student Park to Clinic", "Clinic to Pavilion", "Pavilion to Pavilion", "Pavilion to ICS New Bldg." },
        
            // Routes from Pavilion to Student Park
            { "Pavilion to Student Park" },
            { "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park" },
            { "Pavilion to Clinic", "Clinic to Student Park" },
        
            // Routes from Pavilion to Cafeteria
            { "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park", "Student Park to Cafeteria" },
            { "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "Pavilion to Clinic", "Clinic to Student Park", "Student Park to Cafeteria" },
            { "Pavilion to Student Park", "Student Park to Clinic", "Clinic to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
        
            // Routes from Pavilion to Clinic
            { "Pavilion to Student Park", "Student Park to Clinic" },
            { "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park", "Student Park to Clinic" },
            { "Pavilion to Clinic" },
            { "Pavilion to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Clinic" },
        
            // Routes from Pavilion to Covered Court
            { "Pavilion to Student Park", "Student Park to Clinic", "Clinic to Covered Court" },
            { "Pavilion to Clinic", "Clinic to Covered Court" },
            { "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park", "Student Park to Clinic", "Clinic to Covered Court" }
        };
        

        String[][] routes_clinic = {
            // Routes from Clinic to Cafeteria
            { "Clinic to Student Park", "Student Park to Cafeteria" },
            { "Clinic to Student Park", "Student Park to Cafeteria" },
            { "Clinic to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "Clinic to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "Clinic to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "Clinic to Pavilion", "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park", "Student Park to Cafeteria" },
        
            // Routes from Clinic to Student Park
            { "Clinic to Pavilion", "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park" },
            { "Clinic to Student Park" },
            { "Clinic to Pavilion", "Pavilion to Student Park" },
        
            // Routes from Clinic to Pavilion
            { "Clinic to Pavilion" },
            { "Clinic to Student Park", "Student Park to Pavilion" },
            { "Clinic to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion" },
        
            // Routes from Clinic to ICS
            { "Clinic to Student Park", "Student Park to ICS New Bldg." },
            { "Clinic to Pavilion", "Pavilion to ICS New Bldg." },
            { "Clinic to Student Park", "Student Park to Pavilion", "Pavilion to ICS New Bldg." },
            { "Clinic to Student Park", "Student Park to ICS New Bldg." }
        };
        

        String[][] routes_covered_court = {
            // Routes from Covered Court to Clinic
            { "Covered Court to Clinic" },
        
            // Routes from Covered Court to Student Park
            { "Covered Court to Clinic", "Clinic to Student Park" },
            { "Covered Court to Clinic", "Clinic to Pavilion", "Pavilion to Student Park" },
            { "Covered Court to Clinic", "Clinic to Pavilion", "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park" },
        
            // Routes from Covered Court to Cafeteria
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to Cafeteria" },
            { "Covered Court to Clinic", "Clinic to Pavilion", "Pavilion to Student Park", "Student Park to Cafeteria" },
            { "Covered Court to Clinic", "Clinic to Pavilion", "Pavilion to ICS New Bldg.", "ICS New Bldg. to Student Park", "Student Park to Cafeteria" },
        
            // Routes from Covered Court to ICS
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to ICS New Bldg." },
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to Pavilion", "Pavilion to ICS New Bldg." },
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to Pavilion", "Pavilion to Student Park", "Student Park to ICS New Bldg." },
        
            // Routes from Covered Court to Pavilion
            { "Covered Court to Clinic", "Clinic to Pavilion" },
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to Pavilion" },
            { "Covered Court to Clinic", "Clinic to Student Park", "Student Park to ICS New Bldg.", "ICS New Bldg. to Pavilion" }
        };
        
        int[][] distances = generateRandomDistances(numLocations, 10, 20);


        String currentLocation = startingLocation;
        int currentLocationIndex = getLocationIndex(locationNames, startingLocation);
        boolean firstMove = true;
        int shortestDistance = Integer.MAX_VALUE;  // Declare shortestDistance here

        while (true) {
            System.out.println("Current Location: " + locationNames[currentLocationIndex]);
            System.out.println("\nDestinations:");
            int destinationCount = 1;
        
            for (String[] route : Destination) {
                if (route[0].startsWith(locationNames[currentLocationIndex])) {
                    String destination = route[0].split(" to ")[1];
                    System.out.println("   " + destinationCount + ". " + destination);
                    destinationCount++;
                }
            }
        
            System.out.print("Type the name of the location you want to move to. (Type 'exit' to quit): ");
            String destinationChoice = scanner.nextLine().trim();
        
            if (destinationChoice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (player.hasEnoughWoodenPlanks(4)) {
                System.out.println("You have collected 4 wooden planks. You are being transported back to ICS New Bldg. to face the final wave of zombies. Press enter to continue...");
                scanner.nextLine(); 
                FinalWave finalWave = new FinalWave();
                finalWave.startFinalWave(player);
        
                break;
            }
        
            String[][] selectedRoutes;
        
            if (currentLocation.equalsIgnoreCase("ICS New Bldg.")) {
                selectedRoutes = routes_ics;
            } else if (currentLocation.equalsIgnoreCase("Student Park")) {
                selectedRoutes = routes_sp;
            } else if (currentLocation.equalsIgnoreCase("Cafeteria")) {
                selectedRoutes = routes_cafeteria;
            } else if (currentLocation.equalsIgnoreCase("Pavilion")) {
                selectedRoutes = routes_pavilion;
            } else if (currentLocation.equalsIgnoreCase("Clinic")) {
                selectedRoutes = routes_clinic;
            } else if (currentLocation.equalsIgnoreCase("Covered Court")) {
                selectedRoutes = routes_covered_court;
            } else {
                System.out.println("Invalid current location.");
                return null;
            }
        
            routes = new String[selectedRoutes.length][];
        
            for (int i = 0; i < selectedRoutes.length; i++) {
                routes[i] = selectedRoutes[i].clone();
            }
        
            Arrays.sort(routes, Comparator.comparing(route -> distances[getLocationIndex(locationNames, route[0].split(" to ")[0])][getLocationIndex(locationNames, route[0].split(" to ")[1])]));
        
            System.out.println("\nShortest Route/s:");
            boolean shortestRouteDisplayed = false;
            String shortestRoute = null;
        
            for (String[] route : routes) {
                String startLocation = route[0].split(" to ")[0];
                String endLocation = route[route.length - 1].split(" to ")[1];
            
                if (startLocation.equalsIgnoreCase(currentLocation) && endLocation.equalsIgnoreCase(destinationChoice)) {
                    int distance = calculateTotalDistance(route, distances, locationNames);
                    String time = calculateTime(distance);
                    String routeString = Arrays.toString(route);
                    System.out.println("   " + routeString + ": " + distance + " meters, Time: " + time);
            
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        shortestRoute = routeString;
                    }
                }
            }
        
            System.out.println("\nTotal Distance of the Shortest Route/s: " + shortestDistance + " meters");
            System.out.println("Time for the Shortest Route: " + calculateTime(shortestDistance));
        
            // Move the message prompt here
            System.out.print("\nConfirm move to " + destinationChoice + "? (Yes / No): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            System.out.println();
        
            if (confirmation.equals("yes")) {
                ZombieEncounter.encounterZombie(player);
                String foundItem = LocationItemsGenerator.generateItem(currentLocation);
                ZombITsMain.displayCurrentMap();
                if (!foundItem.isEmpty()) {
                    System.out.println("You found an item: " + foundItem);
                    player.getInventory().addItem(foundItem);
                }
                if (firstMove) {
                    player.setStamina(player.getStamina() - shortestDistance);
                    firstMove = false;
                } else {
                    player.setStamina(player.getStamina() - (shortestDistance * 2));
                }
                System.out.println("You have " + player.getStamina() + " stamina remaining.");
                if (player.getStamina() <= 0) {
                    System.out.println("You ran out of energy! Game over.");
                    break;
                }

                currentLocation = destinationChoice;
                currentLocationIndex = getLocationIndex(locationNames, currentLocation);
                ZombITsMain.moveToLocation(currentLocation);

            } else if (confirmation.equals("no")) {
                System.out.println("You did not move locations.");
            }
            
        } 
        System.out.println();
    
        return "Game Over";

    }

    static int[][] generateRandomDistances(int numLocations, int minDistance, int maxDistance) {
        int[][] distances = new int[numLocations][numLocations];
        Random random = new Random();
    
        for (int i = 0; i < numLocations; i++) {
            for (int j = 0; j < numLocations; j++) {
                if (i != j) {
                    // Generate a random distance between minDistance and maxDistance (inclusive)
                    distances[i][j] = random.nextInt(maxDistance - minDistance + 1) + minDistance;
    
                    // Ensure the distance is within the desired range
                    distances[i][j] = Math.max(distances[i][j], 10); // Ensure it's at least 10 meters
                }
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
        return -1;
    }

    private static String calculateTime(int distance) {
        if (distance <= 0) {
            distance = 10; // Set a default distance of 10 meters
        }
    
        int walkingSpeed = 300; // meters per hour (assuming average walking speed)
        int timeInSeconds = (distance * 60 * 60) / walkingSpeed; // time in seconds
    
        if (timeInSeconds < 0) {
            return "10 minutes";
        }
    
        int minutes = (timeInSeconds % 3600) / 60; // minutes
        int seconds = timeInSeconds % 60; // seconds
        return String.format("%d minutes %d seconds", minutes, seconds);
    }
    
    private static int calculateTotalDistance(String[] route, int[][] distances, String[] locationNames) {
        int totalDistance = 0;
        boolean nonZeroDistanceEncountered = false;
    
        for (int i = 0; i < route.length - 1; i++) {
            String startLocation = route[i].split(" to ")[0];
            String endLocation = route[i].split(" to ")[1];
            int startLocationIndex = getLocationIndex(locationNames, startLocation);
            int endLocationIndex = getLocationIndex(locationNames, endLocation);
            int distance = distances[startLocationIndex][endLocationIndex];
    
            // Check if the distance is non-negative
            if (distance > 0) {
                nonZeroDistanceEncountered = true;
                totalDistance += distance;
            } else {
                // Handle the case where the distance is negative (or 0) differently if needed
                System.out.println("Warning: Invalid distance encountered for route " + Arrays.toString(route));
            }
        }
    
        // If no non-zero distance was encountered, set a default positive value
        if (!nonZeroDistanceEncountered) {
            totalDistance = Integer.MAX_VALUE - 1;
        }
    
        return totalDistance;
    }    
    
}
