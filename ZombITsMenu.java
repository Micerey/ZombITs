import java.util.Scanner;

public class ZombITsMenu {
    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        // int lives = 100;

        // To display menu options
        System.out.println("Welcome to the ZombITs Game! Are you ready to test your wit and luck to survive?");

        // Loop will continue unless the user wants to exit (exit becomes true)
        while (!exit) {
            System.out.println();
            System.out.println(
                    "Game Menu\n 1:Check Map \n 2:Check Inventory \n 3:Player Information \n 4:Quit");
            // prompt user to choose in the menu
            System.out.print("Enter your choice: ");
            int menuChoice = sc.nextInt();

            System.out.println();

            switch (menuChoice) {
                case 1: {
                    gameMap.displayMap();

                    System.out.println("Please enter the destination you want to move to: ");
                    String destination = sc.nextLine();
                    System.out.println("Moving to " + destination + "...");

                    // Additional logic for moving to the destination goes here

                    System.out.println("Please press enter to continue...");
                    sc.nextLine();

                    break;
                }

                case 2: {
                    System.out.println();
                    System.out.println("Collected items:");
                    System.out.println("Potion: ");
                    System.out.println("Energy drink: ");
                    System.out.println("Calculator: ");
                    System.out.println("Zombie Immunity: ");
                    System.out.println();
                    break;
                }

                case 3: {
                    System.out.println("Lives: ");
                    System.out.println("Progress: ");
                    System.out.println("Stamina: ");
                    break;
                }

                case 4: {
                    System.out.println("Are you sure you want to quit the game? You're progress won't be saved.");
                    System.out.print("Quit the game. 1: Yes or 2: No? ");
                    String exitChoice = sc.next();

                    if (exitChoice.equals("1")) {
                        System.out.println("Exiting the game! Goodbye.");
                        exit = true;
                    }

                    else if (exitChoice.equals("2")) {
                        System.out.println("You are continuing to the game.");
                        System.out.println();
                    }

                    else {
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
}
