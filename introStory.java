public class introStory {
    public static void introduction() {
        String[] story = {
                "       Welcome to ZombITs, where the virtual world meets the undead apocalypse! In this thrilling augmented",
                " reality game, you'll find yourself surrounded by hordes of zombies, armed with nothing but your skills and",
                " a virtual arsenal.",
                "                                         ",
                "      Your mission? Survive. It's that simple. But beware, the zombies aren't the only threat lurking in",
                " this digital wasteland. With each level, the challenges get tougher, and the stakes get higher.     ",
                "                                         ",
                "      Think fast, act smart, and most importantly, stay alive. ZombITs is the ultimate test of your survival",
                " instincts. Are you ready to take on the undead and emerge as the ultimate survivor? Welcome to ZombITs." };

        int maxLength = 0;
        for (String intro : story) {
            maxLength = Math.max(maxLength, intro.length());
        }

        String border = "-".repeat(maxLength + 4);

        System.out.println(border);
        for (String intro : story) {
            System.out.printf("| %s %s |\n", intro, " ".repeat(maxLength - intro.length()));
        }
        System.out.println(border);
    }

    public static void displayIO() {
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
    }
}
