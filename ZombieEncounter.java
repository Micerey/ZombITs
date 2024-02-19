import java.util.Random;
import java.util.Scanner;

public class ZombieEncounter {
    private static final int MAX_LIVES = 3;

    private static int lives = MAX_LIVES;

    public static void encounterZombie(Player player) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        if (random.nextDouble() <= 0.3) {
            System.out.println("Oh no! You've encountered a zombie!");

            int num1 = random.nextInt(10) + 1;
            int num2 = random.nextInt(10) + 1;
            int correctAnswer = num1 + num2;

            System.out.println("Solve the math problem to defeat the zombie:");
            System.out.print(num1 + " + " + num2 + " = ");

            CountdownTimer.startTimer(15, new TimerCallback() {
                @Override
                public void onTimerFinish() {
                    System.out.println("The zombie got you! You lost a life.");
                    player.loseLife();
                    PlayerInformation.displayPlayerInfo(player);
                }
            });

            int userAnswer = sc.nextInt();

            CountdownTimer.cancelTimer();

            if (userAnswer == correctAnswer) {
                System.out.println("Congratulations! You defeated the zombie and can proceed.");
            } else {
                System.out.println("Oops! Incorrect answer. You lost a life.");
                player.loseLife();
                PlayerInformation.displayPlayerInfo(player);
            }
        }
    }
}
