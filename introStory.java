public class introStory {
        public static void introduction() {
                String[] story = {

                                "             █   █ █▀▀ █   █▀▀ █▀▀█ █▀▄▀█ █▀▀  ▀▀█▀▀ █▀▀█  █▀▀▀█ █▀▀█ █▀▄▀█ █▀▀▄ ▀█▀ ▀▀█▀▀ █▀▀ ",
                                "             █ █ █ █▀▀ █   █   █  █ █ ▀ █ █▀▀    █   █  █  ▄▄▄▀▀ █  █ █ ▀ █ █▀▀▄  █    █   ▀▀█ ",
                                "             █▄▀▄█ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀▀ ▀   ▀ ▀▀▀    ▀   ▀▀▀▀  █▄▄▄█ ▀▀▀▀ ▀   ▀ ▀▀▀  ▄█▄   █   ▀▀▀ ",
                                " ",
                                " ",
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
                String[] Item_Objective = {
                                "  ITEMS TO COLLECT                 ||  OBJECTIVES",
                                "   Item                            ||    1. Navigate through six different locations.",
                                "     Potion                        ||    2. Gather essential materials within a limited time.",
                                "     Energy drink                  ||    3. Solve mathematical problems for survival.",
                                "     Weapon                        ||    4. Preserve items and survival by solving math correctly.",
                                "     Calculator                    ||    5. Collect the correct items for the final",
                                " ",
                                "  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++",
                                " ",
                                "                               █▀▄▀█ █▀▀ █▀▀ █ █ ▄▀█ █▄ █ █ █▀▀ █▀",
                                "                               █ ▀ █ ██▄ █▄▄ █▀█ █▀█ █ ▀█ █ █▄▄ ▄█",
                                " ",
                                " ",
                                " ITEMS",
                                " Items will be randomly distributed among the locations at the beginning of the game.",
                                "    1. Potion          -       adds health",
                                "    2. Energy Drink    -       adds stamina",
                                "    3. Calculator      -       can be used in solving the problems",
                                "    4. Weapon          -       can be used against zombies",
                                " ",
                                " ZOMBIE ",
                                "    1. Random zombie encounter.",
                                "    2. You can advance to the next location, if your answer is correct.",
                                "    3. Otherwise, your life will decrease and a chance to lose an item.",
                                "    4. The lost item will be randomly redistributed to another location.",
                                " ",
                                " STAMINA ",
                                "    1. The player's stamina will decrease in accordance to the total distance they travelled..",
                                "    2. When stamina runs out, the player rests overnight where they are, leading to the loss of items",
                                " ",
                                " ITEMS RETRIEVAL",
                                "    1. Players must retrieve four (4) items in total to fortify their base against the final onslaught",
                                "       of zombies.",
                                "    2. ",

                };

                int maxLength = 0;
                for (String intro : Item_Objective) {
                        maxLength = Math.max(maxLength, intro.length());
                }

                String border = "-".repeat(maxLength + 4);

                System.out.println(border);
                for (String intro : Item_Objective) {
                        System.out.printf("| %s %s |\n", intro, " ".repeat(maxLength - intro.length()));
                }
                System.out.println(border);
        };

}
