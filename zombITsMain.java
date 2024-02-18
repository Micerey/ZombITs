import java.util.Scanner;

public class zombITsMain {
        public static void main(String[] args) {
                Scanner s = new Scanner(System.in);

                // Introduction / story
                System.out.println(
                                "\tWelcome to ZombITs, where the virtual world meets the undead apocalypse! In this thrilling augmented reality game, you'll find yourself surrounded by hordes of zombies, armed with nothing but your skills and a virtual arsenal.\r\n"
                                                + //
                                                "\r" + //
                                                "\tYour mission? Survive. It's that simple. But beware, the zombies aren't the only threat lurking in this digital wasteland. With each level, the challenges get tougher, and the stakes get higher.\r\n"
                                                + //
                                                "\r" + //
                                                "\tThink fast, act smart, and most importantly, stay alive. ZombITs is the ultimate test of your survival instincts. Are you ready to take on the undead and emerge as the ultimate survivor? Welcome to ZombITs.");
                // ============================================================================================================================================

                // Prompt user to start the game
                System.out.println();
                System.out.print("Start the game (1. Yes 2. No): ");
                int readyChoice = s.nextInt();
                s.nextLine();

                if (readyChoice == 1) {
                        // Displays the objective
                        System.out.println();
                        System.out.println(
                                        "_________________________________________________________________________________________________________________");
                        System.out.println(
                                        "|                                                                                                               |");
                        System.out.println(
                                        "|  ITEMS TO COLLECT                     ||  OBJECTIVES                                                          |");
                        System.out.println(
                                        "|    Code | Item                        ||    1. Navigate through six different locations.                      |");
                        System.out.println(
                                        "|    0001 | Weapon & Ammunition         ||    2. Gather essential materials within a limited time.              |");
                        System.out.println(
                                        "|    0002 | Food                        ||    3. Solve mathematical problems for survival.                      |");
                        System.out.println(
                                        "|    0003 | Water                       ||    4. Preserve items and survival by solving math correctly.         |");
                        System.out.println(
                                        "|    0004 | Calculator                  ||    5. Collect the correct items for the final                        |");
                        System.out.println(
                                        "|_______________________________________________________________________________________________________________|");

                        System.out.println();

                        // Wait for the user to press enter
                        System.out.println("Press enter to continue...");
                        s.nextLine();

                        // Call displayMenu() method
                        ZombITsMenu.displayMenu();
                }

                s.close();
        }
}
