import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LocationMathProblems {
    private static Map<String, Boolean> solvedProblems = new HashMap<>();

    public static boolean hasSolvedProblem(String location) {
        return solvedProblems.getOrDefault(location, false);
    }

    public static void markProblemAsSolved(String location) {
        solvedProblems.put(location, true);
    }

    public static void generateMathProblem(String location) {
        Random random = new Random();
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;

        System.out.println("Solve the math problem to proceed:");
        System.out.print(num1 + " + " + num2 + " = ");

        CountdownTimer.startTimer(15, new TimerCallback() {
            @Override
            public void onTimerFinish() {
                System.out.println("Time's up! You lost a life.");
                // Implement logic for losing a life here
            }
        });
        
        Scanner scanner = new Scanner(System.in);
        int userAnswer = scanner.nextInt();
        CountdownTimer.cancelTimer();
        
        int correctAnswer = num1 + num2;

        if (userAnswer == correctAnswer) {
            System.out.println("Correct! You defeated the zombie and can proceed.");
            markProblemAsSolved(location);
        } else {
            System.out.println("Incorrect! You lost a life.");
        }
    }
}
