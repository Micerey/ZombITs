import java.util.Scanner;

public class zombITsMain {
        public static void main(String[] args) {
                Scanner s = new Scanner(System.in);

                // Introduction / story
                character.displayChar();
                // introStory.introduction();

                // Prompt user to start the game
                System.out.println();
                System.out.print("Start the game (1. Yes 2. No): ");
                while (!s.hasNextInt()) {
                        System.out.print("Invalid input. Please enter the correct value: ");
                        s.next();
                }

                int readyChoice = s.nextInt();
                s.nextLine();

                if (readyChoice == 1) {
                        // Displays the objective
                        introStory.displayIO();

                        // Wait for the user to press enter
                        System.out.println("Press enter to continue...");
                        s.nextLine();

                        // Call displayMenu() method
                        ZombITsMenu.displayMenu();

                }

                s.close();
        }
}
