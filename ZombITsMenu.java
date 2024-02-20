import java.util.Scanner;

public class ZombITsMenu {
    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the ZombITs Game! Are you ready to test your wit and luck to survive?");

        // Initialize the player's inventory
        Inventory playerInventory = new Inventory();
        Player player = new Player(3, 0, 100, playerInventory); // Create a default player

        while (!exit) {
            System.out.println();
            System.out.println(
                    "Game Menu\n 1: Check Map \n 2: Check Inventory \n 3: Player Information \n 4: Quit");
            System.out.print("Enter your choice: ");
            int menuChoice = sc.nextInt();

            System.out.println();

            switch (menuChoice) {
                case 1: {
                    // Call the displayMap method from the gameMap class
                    gameMap.displayMap();

                    // Call the moveWithinMap method from the PlayerMovement class
                    PlayerMovement.moveWithinMap(player);

                    System.out.println("Are you sure you want to exit?(Y/N): ");
                    sc.nextLine(); // Consume the newline character left by sc.nextInt()
                    String destination = sc.nextLine().trim();

                    if (destination.equalsIgnoreCase("Y")) {
                        System.out.println("Returning to ZombITs Menu...");
                        break; // Break out of the case to return to the menu
                    }

                    System.out.println("Moving to " + destination + "...");

                    // Additional logic for moving to the destination goes here

                    System.out.println("Please press enter to continue...");
                    sc.nextLine();

                    break;
                }

                case 2: {
                    System.out.println();
                    System.out.println("Collected items:");
                    for (String itemName : playerInventory.getItems().keySet()) {
                        int itemCount = playerInventory.getItems().get(itemName);
                        System.out.println(itemName + ": " + itemCount);
                    }
                
                    // Allow the player to use an item
                    System.out.print("Do you want to use an item? (Y/N): ");
                    String useItemChoice = sc.next();
                
                    if (useItemChoice.equalsIgnoreCase("Y")) {
                        System.out.print("Enter the item name to use: ");
                        String itemName = sc.next();
                        // Use the item and update the player's state
                        playerInventory.useItem(itemName, player);
                    }
                
                    System.out.println();
                    break;
                    }
                    
                case 3: {
                    // Call the displayPlayerInfo method from the PlayerInformation class
                    PlayerInformation.displayPlayerInfo(player);
                    break;
                }

                case 4: {
                    System.out.println("Are you sure you want to quit the game? Your progress won't be saved.");
                    System.out.print("Quit the game. 1: Yes or 2: No? ");
                    String exitChoice = sc.next();

                    if (exitChoice.equals("1")) {
                        System.out.println("Exiting the game! Goodbye.");
                        exit = true;
                    } else if (exitChoice.equals("2")) {
                        System.out.println("You are continuing to the game.");
                        System.out.println();
                    } else {
                        System.out.println("Invalid choice! You are continuing to the game.");
                        System.out.println();
                    }

                    break;
                }

                default: {
                    System.out.println("Game over!");
                    break;
                }
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        // Uncomment the line below if you want to test the ZombITsMenu independently
        displayMenu();
    }
}
