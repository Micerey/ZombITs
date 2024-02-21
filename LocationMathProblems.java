import java.util.Random;
import java.util.Scanner;

public class LocationMathProblems {
    private static Scanner scanner = new Scanner(System.in);

    private static void generateAndDisplayProblem(int num1, int num2, int operation) {
        switch (operation) {
            case 0:
                System.out.println("Solve the math problem to proceed:");
                System.out.print(num1 + " + " + num2 + " = ");
                break;
            case 1:
                // Ensure num1 is always greater than or equal to num2 in subtraction
                int adjustedNum1Subtraction = Math.max(num1, num2);
                num2 = Math.min(num1, num2);
                
                System.out.println("Solve the math problem to proceed:");
                System.out.print(adjustedNum1Subtraction + " - " + num2 + " = ");
                break;
            case 2:
                System.out.println("Solve the math problem to proceed:");
                System.out.print(num1 + " * " + num2 + " = ");
                break;
            case 3:
                // Ensure division results in a whole number
                if (num2 == 0 || num1 % num2 != 0) {
                    Random random = new Random();
                    int adjustedNum1Division = num2 * (random.nextInt(9) + 1); // Adjust num1 to make the division result in a whole number
                    System.out.println("Solve the math problem to proceed:");
                    System.out.print(adjustedNum1Division + " / " + num2 + " = ");
                } else {
                    System.out.println("Solve the math problem to proceed:");
                    System.out.print(num1 + " / " + num2 + " = ");
                }
                break;
        }
    }
    
    public static void generateMathProblem(String location, Player player, ZombITsMain game) {
        Random random = new Random();
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        int operation = random.nextInt(4); // Generate a random operation (0 to 3)

        generateAndDisplayProblem(num1, num2, operation);

        CountdownTimer.startTimer(15, () -> {
            System.out.println("Time's up!");
            if (!player.hasItem("Calculator")) {
                System.out.println("You lost a life.");
                player.loseLife();
            } else {
                System.out.println("Good thing you have a Calculator! No life lost.");
            }
            continueInterface(player, location, game);
        });

        int userAnswer;
        try {
            userAnswer = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine();
            CountdownTimer.cancelTimer();
            continueInterface(player, location, game);
            return;
        } finally {
            CountdownTimer.cancelTimer();
        }

        int correctAnswer = getCorrectAnswer(num1, num2, operation);

        if (userAnswer == correctAnswer) {
            System.out.println("Correct! You defeated the zombie and can proceed.");

            // Generate an item for the location
            String obtainedItem = LocationItemsGenerator.generateItem(location);

            // Display the obtained item
            if (obtainedItem != null) {
                System.out.println("You found an item: " + obtainedItem);
                player.getInventory().addItem(obtainedItem); // Add the item to the player's inventory
            } else {
                System.out.println("No item found in this location.");
            }
        } else {
            if (!player.hasItem("Calculator")) {
                System.out.println("Incorrect, you have lost a life...");
                player.loseLife();
            } else {
                System.out.println("Incorrect, but you have a Calculator! No life lost.");
            }
        }

        // Display the result
        System.out.println("Your answer: " + userAnswer);
        System.out.println("Correct answer: " + correctAnswer);

        continueInterface(player, location, game);
    }

    private static void continueInterface(Player player, String location, ZombITsMain game) {
        System.out.println("What would you like to do next?");
        System.out.println("1. Move to another location");
        System.out.println("2. Check inventory");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Current Location:");
                ZombITsMain.displayCurrentMap();
                System.out.println();
                System.out.println("Map:");
                gameMap.displayMap();
                String newLocation = PlayerMovement.moveWithinMap(game, player, location);
                System.out.println("Moving to " + newLocation + "...");
                break;
            case 2:
                System.out.println();
                System.out.println("Collected items:");
                for (String itemName : player.getInventory().getItems().keySet()) {
                    int itemCount = player.getInventory().getItems().get(itemName);
                    System.out.println(itemName + ": " + itemCount);
                }

                System.out.print("Do you want to use an item? (Y/N): ");
                String useItemChoice = scanner.next();

                if (useItemChoice.equalsIgnoreCase("Y")) {
                    System.out.print("Enter the item name to use: ");
                    scanner.nextLine();
                    String itemName = scanner.nextLine().trim();
                    player.getInventory().useItem(itemName, player);
                } else if (useItemChoice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the menu...");
                } else {
                    System.out.println("Invalid choice. Returning to the menu...");
                }

                System.out.println();
                break;
            default:
                System.out.println("Invalid choice. Continuing...");
        }
    }

    private static int getCorrectAnswer(int num1, int num2, int operation) {
        int adjustedNum1 = num1; // Use a separate variable for adjusted num1

        switch (operation) {
            case 0:
                return num1 + num2;
            case 1:
                // Ensure num1 is always greater than or equal to num2 in subtraction
                adjustedNum1 = Math.max(num1, num2);
                num2 = Math.min(num1, num2);
                return adjustedNum1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                // Avoid division by zero
                if (num2 == 0) {
                    num2 = 1;
                }
                return adjustedNum1 / num2;
            default:
                return num1 + num2; // Default to addition
        }
    }
}
