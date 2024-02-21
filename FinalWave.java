import java.util.Scanner;

public class FinalWave {
    private static int lives;
    private static int woodenPlanksCount;

    public void startFinalWave(Player player) {
        lives = player.getLives();
        woodenPlanksCount = player.getWoodenPlanksCount();

        for (int questionNumber = 1; questionNumber <= 4; questionNumber++) {
            System.out.println("Question " + questionNumber + ":");
            String question = generateQuestion(questionNumber);
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
            if (isAnswerCorrect(userAnswer, questionNumber, woodenPlanksCount)) {
                System.out.println("Correct! Proceed to the next question.");
            } else {
                System.out.println("Incorrect! You lose a life.");
                decreaseLives();
            }
            System.out.println();
        }

        // Congratulate the player if they survive the final wave
        System.out.println("Congratulations! You have survived the final wave!");

        System.exit(0);
    }

    static String generateQuestion(int questionNumber) {
        return generateQuestion(questionNumber, woodenPlanksCount);
    }

    static String generateQuestion(int questionNumber, int woodenPlanksCount) {
        String category;

        if (woodenPlanksCount >= 4) {
            category = "easiest";
        } else if (woodenPlanksCount == 3) {
            category = "easy";
        } else if (woodenPlanksCount == 2) {
            category = "difficult";
        } else {
            category = "extreme";
        }

        return generateQuestionByCategory(category, questionNumber);
    }


    static String generateQuestionByCategory(String category, int questionNumber) {
        switch (category.toLowerCase()) {
            case "easiest":
                return generateEasiestQuestion(questionNumber);
            case "easy":
                return generateEasyQuestion(questionNumber);
            case "difficult":
                return generateDifficultQuestion(questionNumber);
            case "extreme":
                return generateExtremeQuestion(questionNumber);
            default:
                return "Invalid category";
        }
    }

    static String generateEasiestQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "What is the result of 5 + 3?";
            case 2:
                return "If you have 10 apples and give away 2, how many apples do you have left?";
            case 3:
                return "What is the product of 4 * 6?";
            case 4:
                return "If you have 20 candies and want to share them equally among 4 friends, how many candies will each friend get?";
            default:
                return "Invalid question type";
        }
    }

    static String generateEasyQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "If John has 8 marbles and he buys 5 more, then gives away 3, how many marbles does he have now?";
            case 2:
                return "Calculate 7 * 9.";
            case 3:
                return "If you have 15 cookies and want to distribute them equally among 4 friends, how many cookies will each friend get, and how many will be left over?";
            case 4:
                return "What is 3/4 + 1/4?";
            case 5:
                return "Find the missing number in the sequence: 2, 4, 6, _, 10.";
            default:
                return "Invalid question type";
        }
    }

    static String generateDifficultQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "Sarah has 3 times as many books as Tom. If Tom has 5 books, how many books does Sarah have?";
            case 2:
                return "Calculate 8.6 - 3.2.";
            case 3:
                return "Compute 23 * 17.";
            case 4:
                return "Divide 5/8 by 2/4.";
            case 5:
                return "What is 5 * 3 / 3 + 14";
            default:
                return "Invalid question type";
        }
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
            default:
                return "Invalid question type";
        }
    }

    static String getUserAnswer() {
        System.out.print("Your answer: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static boolean isAnswerCorrect(String userAnswer, int questionNumber, int woodenPlanksCount) {
        switch (woodenPlanksCount) {
            case 4:
                return isEasiestQuestionCorrect(userAnswer, questionNumber);
            case 3:
                return isEasyQuestionCorrect(userAnswer, questionNumber);
            case 2:
                return isDifficultQuestionCorrect(userAnswer, questionNumber);
            default:
                return isExtremeQuestionCorrect(userAnswer, questionNumber);
        }
    }

    static boolean isEasiestQuestionCorrect(String userAnswer, int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "8".equals(userAnswer.trim());
            case 2:
                return "8".equals(userAnswer.trim());
            case 3:
                return "24".equals(userAnswer.trim());
            case 4:
                return "5".equals(userAnswer.trim());
            default:
                return false;
        }
    }

    static boolean isEasyQuestionCorrect(String userAnswer, int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "10".equals(userAnswer.trim());
            case 2:
                return "63".equals(userAnswer.trim());
            case 3:
                return "3 remainder 3".equalsIgnoreCase(userAnswer.trim());
            case 4:
                return "1".equals(userAnswer.trim());
            case 5:
                return "8".equals(userAnswer.trim());
            default:
                return false;
        }
    }

    static boolean isDifficultQuestionCorrect(String userAnswer, int questionNumber) {
        switch (questionNumber) {
            case 1:
                return "15".equals(userAnswer.trim());
            case 2:
                return "5.4".equals(userAnswer.trim());
            case 3:
                return "391".equals(userAnswer.trim());
            case 4:
                return "1.25".equals(userAnswer.trim());
            case 5:
                return "23".equals(userAnswer.trim());
            default:
                return false;
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
