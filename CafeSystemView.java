

import java.util.Scanner;

public class CafeSystemView {

    private static Scanner scan = new Scanner(System.in);
    private static int choice;
    private static String[] options = { "1. Place Order \n2. View Queue \n0. Exit" };

    public static void main(String[] args) {
        displayOptions();
    }

    

    private static void displayOptions() {
        MenuTable menuTable = new MenuTable(); // Creating an instance of MenuTable
        do {
            System.out.println("Cafe System");
            printSeparator();
            printOptions();
            printSeparator();
            System.out.print("Enter choice: ");
            int choice = scan.nextInt();
            printSeparator();

            switch (choice) {
                case 1:
                    placeOrder();
                    break;

                case 2:
                    menuTable.displayOrderQueue();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    return;
            }
        } while (choice != 0);
    }

    private static void placeOrder() {
        MenuTable.placeOrder();
    }

    private static void printSeparator() {
        int separatorLength = 20 * options.length;
        for (int i = 0; i < separatorLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }

}
