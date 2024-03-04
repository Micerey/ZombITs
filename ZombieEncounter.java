import java.util.Random;
import java.util.Scanner;

public class ZombieEncounter {
    private static final int MAX_LIVES = 3;

    private static int lives = MAX_LIVES;

    public static void encounterZombie(Player player) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        if (random.nextDouble() <= 0.7) {
            System.out.println("Oh no! You've encountered a zombie!");

            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            int operation = random.nextInt(4); // 0: Addition, 1: Subtraction, 2: Multiplication, 3: Division
            int correctAnswer = calculateCorrectAnswer(num1, num2, operation);

            displayProblem(num1, num2, operation);

            CountdownTimer.startTimer(15, () -> {
                if (player.hasItem("Baseball Bat")) {
                    System.out.println("You successfully defended with the baseball bat. No life lost.");
                } else {
                    System.out.println("The zombie got you! You lost a life.");
                    player.loseLife();
                }
            });

            int userAnswer;
            try {
                userAnswer = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); // Consume the invalid input
                CountdownTimer.cancelTimer();
                return;
            } finally {
                CountdownTimer.cancelTimer();
            }

            if (userAnswer == correctAnswer) {
                System.out.println("Congratulations! You defeated the zombie and can proceed.");
                dropItemUponDefeat(player);
            } else if (player.hasItem("Baseball Bat")) {
                System.out.println("You successfully defended with the baseball bat. No life lost.");
            } else {
                System.out.println("The zombie got you! You lost a life.");
                player.loseLife();
            }
        }
    }

    private static void dropItemUponDefeat(Player player) {
        Random random = new Random();
        double dropChance = random.nextDouble();

        if (dropChance <= 0.3) { // 30% chance for the zombie to drop an item
            String droppedItem = LocationItemsGenerator.generateItem("Zombie");
            System.out.println("The defeated zombie dropped an item: " + droppedItem);
            player.getInventory().addItem(droppedItem);
        } else {
            System.out.println("The defeated zombie didn't drop any items.");
        }
    }

    private static void displayProblem(int num1, int num2, int operation) {
        Random random = new Random(); // This random is used for the adjustment, not for selecting the operation

        int adjustedNum1 = Math.max(num1, num2); // Use a separate variable for adjusted num1
        num2 = Math.min(num1, num2); // Ensure num1 is always greater than or equal to num2 in subtraction

        switch (operation) {
            case 0:
                System.out.println("Solve the math problem to proceed:");
                System.out.print(num1 + " + " + num2 + " = ");
                break;
            case 1:
                System.out.println("Solve the math problem to proceed:");
                System.out.print(adjustedNum1 + " - " + num2 + " = ");
                break;
            case 2:
                System.out.println("Solve the math problem to proceed:");
                System.out.print(num1 + " * " + num2 + " = ");
                break;
            case 3:
                // Ensure division results in a whole number with an even dividend
                int factor = random.nextInt(4) * 2; // Generate a random even number as a factor
                adjustedNum1 = num2 * factor;
                System.out.println("Solve the math problem to proceed:");
                System.out.print(adjustedNum1 + " / " + num2 + " = ");
                break;
        }
    }

    private static int calculateCorrectAnswer(int num1, int num2, int operation) {
        int adjustedNum1 = Math.max(num1, num2); // Use a separate variable for adjusted num1
        num2 = Math.min(num1, num2); // Ensure num1 is always greater than or equal to num2 in subtraction

        switch (operation) {
            case 0:
                return num1 + num2;
            case 1:
                return adjustedNum1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                // Ensure division results in a whole number with an even dividend
                int factor = new Random().nextInt(4) * 2; // Generate a random even number as a factor
                adjustedNum1 = num2 * factor;
                return adjustedNum1 / num2;
            default:
                return num1 + num2; // Default to addition
        }
    }
}
