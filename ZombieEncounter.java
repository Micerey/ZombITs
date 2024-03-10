import java.util.Random;
import java.util.Scanner;

public class ZombieEncounter {
    private static final int MAX_LIVES = 3;

    private static int lives = MAX_LIVES;
    private static Random random = new Random();

    public static void encounterZombie(Player player) {
        Scanner sc = new Scanner(System.in);

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
            } else if (player.hasItem("Baseball Bat")) {
                System.out.println("You successfully defended with the baseball bat. No life lost.");
            } else {
                System.out.println("The zombie got you! You lost a life.");
                player.loseLife();
            }
        }
    }

    private static void displayProblem(int num1, int num2, int operation) {
        String operator;
        switch (operation) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                if (num1 < num2) {
                    // Swap the numbers if num1 is less than num2
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                break;
            case 2:
                operator = "*";
                break;
            case 3:
                operator = "/";
                // Ensure num1 is greater than num2 and both are even
                num1 = Math.max(num1, num2 + 1);
                num1 = makeEven(num1);
                num2 = findDivisorForEven(num1);
                break;
            default:
                operator = "+"; // Default to addition
                break;
        }
    
        System.out.println("Solve the math problem to proceed:");
        System.out.print(num1 + " " + operator + " " + num2 + " = ");
    }
    
    private static int calculateCorrectAnswer(int num1, int num2, int operation) {
        switch (operation) {
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                // Check if the division is exact, otherwise return a value to indicate an incorrect answer
                return (num1 % num2 == 0) ? num1 / num2 : -1;
            default:
                return num1 + num2; // Default to addition
        }
    }

    private static int makeEven(int num) {
        // Make sure num is even
        return (num % 2 == 0) ? num : num + 1;
    }

    private static int findDivisorForEven(int num) {
        // Find an appropriate divisor for even numbers
        return (num % 4 == 0) ? 4 : (num % 2 == 0) ? 2 : 1;
    }    
}
