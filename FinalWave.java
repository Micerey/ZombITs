import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FinalWave {
    private static int lives;

    public void startFinalWave(Player player) {
        lives = player.getLives();
        List<Integer> selectedQuestions = selectRandomQuestions();

        for (int i = 0; i < selectedQuestions.size(); i++) {
            int questionNumber = selectedQuestions.get(i);
            System.out.println("Question " + (i + 1) + ":");
            String question = generateExtremeQuestion(questionNumber);
            System.out.println(question);

            // Start the countdown timer for each question (30 seconds)
            CountdownTimer.startTimer(30, () -> {
                System.out.println("Time's up! You failed to answer the question.");
                decreaseLives();
            });

            // Get user's answer
            String userAnswer = getUserAnswer();

            // Cancel the timer as the user has provided an answer
            CountdownTimer.cancelTimer();

            // Validate the answer
            if (userAnswer.trim().isEmpty()) {
                System.out.println("Answer cannot be empty. You lose a life.");
                decreaseLives();
            } else if (isExtremeQuestionCorrect(userAnswer, questionNumber)) {
                System.out.println("Correct! Proceed to the next question.");
            } else {
                System.out.println("Incorrect! You lose a life.");
                decreaseLives();
            }
            System.out.println();

            // Pause for 3 seconds before displaying the next question
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Congratulate the player if they survive the final wave
        System.out.println("Congratulations! You have survived the final wave!");

        System.exit(0);
    }

    static List<Integer> selectRandomQuestions() {
        List<Integer> allQuestions = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            allQuestions.add(i);
        }
        Collections.shuffle(allQuestions);
        return allQuestions.subList(0, 5); // Select the first 5 questions
    }

    static String generateExtremeQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "2x + 5 = 17. What is x = ?";
            case 2:
                return "5x + 18 = 33. What is x = ?";
            case 3:
                return "7x + 3x = 70. What is x = ?";
            case 4:
                return "5x - 2x = 6. What is x = ?";
            case 5:
                return "3y + 7 = 16. What is y?";
            case 6:
                return "4x + 8 = 24. What is x = ?";
            case 7:
                return "6x - 3 = 21. What is x = ?";
            case 8:
                return "10x + 5 = 35. What is x = ?";
            case 9:
                return "2y + 10 = 18. What is y = ?";
            case 10:
                return "3y - 5 = 16. What is y = ?";
            case 11:
                return "4z + 12 = 24. What is z = ?";
            case 12:
                return "8z - 6 = 42. What is z = ?";
            case 13:
                return "3w + 15 = 30. What is w = ?";
            case 14:
                return "7w - 14 = 21. What is w = ?";
            case 15:
                return "5a + 20 = 45. What is a = ?";
            case 16:
                return "9a - 27 = 54. What is a = ?";
            case 17:
                return "6b + 18 = 42. What is b = ?";
            case 18:
                return "2b - 4 = 10. What is b = ?";
            case 19:
                return "4c + 16 = 40. What is c = ?";
            case 20:
                return "10c - 30 = 50. What is c = ?";
            default:
                return "Invalid question type";
        }
    }

    static String getUserAnswer() {
        System.out.print("Your answer: ");
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    static boolean isExtremeQuestionCorrect(String userAnswer, int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "6".equals(userAnswer.trim());
            case 2:
                return "3".equals(userAnswer.trim());
            case 3:
                return "7".equals(userAnswer.trim());
            case 4:
                return "2".equals(userAnswer.trim());
            case 5:
                return "3".equals(userAnswer.trim());
            case 6:
                return "4".equals(userAnswer.trim());
            case 7:
                return "6".equals(userAnswer.trim());
            case 8:
                return "3".equals(userAnswer.trim());
            case 9:
                return "4".equals(userAnswer.trim());
            case 10:
                return "7".equals(userAnswer.trim());
            case 11:
                return "2".equals(userAnswer.trim());
            case 12:
                return "8".equals(userAnswer.trim());
            case 13:
                return "5".equals(userAnswer.trim());
            case 14:
                return "7".equals(userAnswer.trim());
            case 15:
                return "5".equals(userAnswer.trim());
            case 16:
                return "9".equals(userAnswer.trim());
            case 17:
                return "6".equals(userAnswer.trim());
            case 18:
                return "7".equals(userAnswer.trim());
            case 19:
                return "6".equals(userAnswer.trim());
            case 20:
                return "8".equals(userAnswer.trim());
            default:
                return false;
        }
    }

    static void decreaseLives() {
        lives--;
        System.out.println("Lives remaining: " + lives);
        if (lives == 0) {
            System.out.println("Game Over. No lives remaining.");
            System.exit(0);
        }
    }

    
}
