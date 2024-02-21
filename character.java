import java.util.Timer;
import java.util.TimerTask;

public class character {
    public static void displayChar(Runnable onAnimationComplete) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ani1();
            }
        }, 1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ani2();
            }
        }, 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ani3();
                // Invoke the callback once the animation is complete
                onAnimationComplete.run();
            }
        }, 4000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                introStory.introduction();
                timer.cancel(); // Stop the timer after displaying ani3()
            }
        }, 6000);
    }

        // Print the ASCII art representation of the character
        public static void ani1() {

                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(
                                "                                                                                  _______       _____                                         ");
                System.out.println(
                                "                                                                            _    (        `    (     )                                        ");
                System.out.println(
                                "                                                                          (    `            ` /      \\                                       ");
                System.out.println(
                                "                                                                         (                             )_                                     ");
                System.out.println(
                                "                                                                          (                              )____                                ");
                System.out.println(
                                "                                                                          `                                   )                               ");
                System.out.println(
                                "                                                                       (`                                    )                                ");
                System.out.println(
                                "                                                                      (                                 ____)                                 ");
                System.out.println(
                                "                                                                       (___                             )_____                                ");
                System.out.println(
                                "                                                                        (                                    )                                ");
                System.out.println(
                                "                                                                         (_____________________________________)                              ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(" ".repeat(145) + " _______");
                System.out.println(" " + " ".repeat(143) + ".'       `. ");
                System.out.println(" ".repeat(143) + "| (o)   (o) |     ");
                System.out.println("   (O _ O)" + " ".repeat(133) + "|    ___    |");
                System.out.println("    |/ |/ " + " ".repeat(134) + "\\  /|\\|\\  /");
                System.out.println("    | |   " + " ".repeat(133) + "  |       |");
                System.out.println("════ |  " + "═".repeat(117) + " ".repeat(18) + " /  ----   \\");
                System.out.println("-- -/ \\  " + "-- ".repeat(39) + " ".repeat(17)
                                + "/   (__)    \\");
                System.out.println("═".repeat(125));
        }

        public static void ani2() {
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(
                                "                                         _______       _____                                         ");
                System.out.println(
                                "                                   _    (        `    (     )                                        ");
                System.out.println(
                                "                                 (    `            ` /      \\                                       ");
                System.out.println(
                                "                                (                             )_                                     ");
                System.out.println(
                                "                                 (                              )____                                ");
                System.out.println(
                                "                                 `                                   )                               ");
                System.out.println(
                                "                              (`                                    )                                ");
                System.out.println(
                                "                             (                                 ____)                                 ");
                System.out.println(
                                "                              (___                             )_____                                ");
                System.out.println(
                                "                               (                                    )                                ");
                System.out.println(
                                "                                (_____________________________________)                              ");
                System.out.println(
                                "                                                                                                     ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(" ".repeat(145) + " _______");
                System.out.println(" " + " ".repeat(143) + ".'       `. ");
                System.out.println(" ".repeat(143) + "| (o)   (o) |     ");
                System.out.println("                                       (O _ O)" + " ".repeat(97) + "|    ___    |");
                System.out.println("                                        |/ |/" + " ".repeat(99) + "\\  /|\\|\\  /");
                System.out.println("                                        | |" + " ".repeat(100) + "  |       |");
                System.out.println("═".repeat(40) + " | " + "═".repeat(82) + " ".repeat(18) + " /  ----   \\");
                System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- --  / \\  " + "-- ".repeat(27) + " ".repeat(17)
                                + "/   (__)    \\");
                System.out.println("═".repeat(125));
        }

        public static void ani3() {
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                           ");
                System.out.println(
                                "                                                                                                                          ");
                System.out.println(
                                "                                                              _______       _____                                         ");
                System.out.println(
                                "                                                        _    (        `    (     )                                        ");
                System.out.println(
                                "                                                      (    `            ` /      \\                                       ");
                System.out.println(
                                "                                                     (                             )_                                     ");
                System.out.println(
                                "                                                      (                              )____                                ");
                System.out.println(
                                "                                                      `                                   )                               ");
                System.out.println(
                                "                                                   (`                                    )                                ");
                System.out.println(
                                "                                                  (                                 ____)                                 ");
                System.out.println(
                                "                                                   (___                             )_____                                ");
                System.out.println(
                                "                                                    (                                    )                                ");
                System.out.println(
                                "                                                     (_____________________________________)                              ");
                System.out.println(
                                "                                                                                                                          ");
                System.out.println(
                                "                                                                                                                                              ");
                System.out.println(" ".repeat(144) + " _______");
                System.out.println(" " + " ".repeat(142) + ".'       `. ");
                System.out.println(" ".repeat(142) + "| (o)   (o) |     ");
                System.out.println(" ".repeat(78) + "(O _ O)" + " ".repeat(57) + "|    ___    |");
                System.out.println(" ".repeat(78) + "|/ |/ " + " ".repeat(59) + "\\  /|\\|\\  /");
                System.out.println(" ".repeat(78) + "| |" + " ".repeat(61) + "  |       |");
                System.out.println("═".repeat(78) + " | " + "═".repeat(44) + " ".repeat(17) + " /  ----   \\");
                System.out.println("-- ".repeat(26) + "/ \\  " + "-- ".repeat(14) + " ".repeat(17)
                                + "/   (__)    \\");
                System.out.println("═".repeat(125));
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
        }

        public static void displayChar(Object object) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'displayChar'");
        }
}
