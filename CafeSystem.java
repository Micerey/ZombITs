package View;

import java.util.*;

class Drink {
    private String id;
    private String name;
    private double price;

    public Drink(String id, String name) {
        this.id = id;
        this.name = name;
        this.price = getPriceFromMenu(name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    private double getPriceFromMenu(String name) {
        if (name.startsWith("IC")) {
            return 40;
        } else if (name.startsWith("P")) {
            return 50;
        } else if (name.startsWith("HB")) {
            return 35;
        }
        return 0;
    }
}

class Order {
    private int orderNumber;
    private Queue<Drink> drinks;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.drinks = new LinkedList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Queue<Drink> getDrinks() {
        return drinks;
    }

    public double getTotalCost() {
        return drinks.stream().mapToDouble(Drink::getPrice).sum();
    }
}

public class CafeSystem {
    private Scanner scanner = new Scanner(System.in);
    private Queue<Order> orderQueue;
    private int currentOrderNumber;

    public CafeSystem() {
        orderQueue = new LinkedList<>();
        currentOrderNumber = 1; // Initialize the first order number
    }

    public void displayMenuOptions() {
        System.out.println("Cafe System");
        System.out.println("--------------------");
        System.out.println("1. Place Order");
        System.out.println("2. View Queue");
        System.out.println("0. Exit");
        System.out.println("--------------------");
    }

    private Map<String, List<Drink>> createMenu() {
        Map<String, List<Drink>> menu = new LinkedHashMap<>();

        // Define the menu
        menu.put("ICED COFFEE", Arrays.asList(
                new Drink("A1", "IC BRUSKO"), new Drink("A2", "IC MACCHIATO"), new Drink("A3", "IC MOCA"),
                new Drink("A4", "IC VANILLA"), new Drink("A5", "IC FUDGE"), new Drink("A6", "IC MATCHA"),
                new Drink("A7", "IC KARAMEL"), new Drink("A8", "IC WHITECHOCO"), new Drink("A9", "IC VIETNAMESE")));

        menu.put("PRAF", Arrays.asList(
                new Drink("B1", "P COOKIES&CREAM"), new Drink("B2", "P TARO"), new Drink("B3", "P MATCHA"),
                new Drink("B4", "P STRAWBERRY"), new Drink("B5", "P CHOCOLATE"), new Drink("B6", "P MELON DEW"),
                new Drink("B7", "P AVOCADO"), new Drink("B8", "P MANGO"), new Drink("B9", "P BANANA")));

        menu.put("HOTBREW", Arrays.asList(
                new Drink("C1", "HB BRUSKO"), new Drink("C2", "HB MACCHIATO"), new Drink("C3", "HB MOCA"),
                new Drink("C4", "HB VANILLA"), new Drink("C5", "HB FUDGE"), new Drink("C6", "HB MATCHA"),
                new Drink("C7", "HB KARAMEL"), new Drink("C8", "HB WHITECHOCO"), new Drink("C9", "HB VIETNAMESE")));

        return menu;
    }

    public void displayFullMenu() {
        Map<String, List<Drink>> menu = createMenu(); // Get the menu
        System.out.println("--------------------");
        System.out.println("Full Menu:");
        menu.forEach((category, drinks) -> {
            System.out.println(category + ":");
            for (Drink drink : drinks) {
                System.out.println("  - " + drink.getId() + ": " + drink.getName() + " (" + drink.getPrice() + " PHP)");
            }
        });
        System.out.println("--------------------");
    }

    public void placeOrder() {
        Map<String, List<Drink>> menu = createMenu(); // Get the menu
        Order currentOrder = new Order(currentOrderNumber); // Order with current number
        boolean isOrderActive = true;

        while (isOrderActive) {
            System.out.println("--------------------");
            System.out.println("Order Number #" + currentOrder.getOrderNumber() + ":");
            displayFullMenu(); // Display the complete menu when placing an order
            System.out.print("Enter Product ID (separated by spaces): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("confirm")) {
                orderQueue.add(currentOrder); // Add the order to the queue
                System.out.println("Order confirmed:");
                for (Drink drink : currentOrder.getDrinks()) {
                    System.out.println(" - " + drink.getName() + " (" + drink.getPrice() + " PHP)");
                }
                System.out.println("Total Cost: " + currentOrder.getTotalCost() + " PHP");
                isOrderActive = false;
                currentOrderNumber++; // Increment for the next order
            } else if (userInput.equalsIgnoreCase("void")) {
                System.out.println("Order voided.");
                isOrderActive = false;
            } else {
                // Add drinks to the current order
                String[] productIds = userInput.split(" ");
                for (String productId : productIds) {
                    boolean found = false;
                    for (List<Drink> drinks : menu.values()) {
                        for (Drink drink : drinks) {
                            if (drink.getId().equalsIgnoreCase(productId)) {
                                orderQueue.add(currentOrder);
                                currentOrder.getDrinks().add(drink);
                                System.out.println("Added: " + drink.getName());
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid product ID: " + productId);
                    }
                }
            }

            System.out.println("--------------------");
            System.out.println("Options:");
            System.out.println("1. Confirm");
            System.out.println("2. Void");
            System.out.println("3. Return");
            System.out.println("--------------------");
            System.out.println("Choose an option:");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    orderQueue.add(currentOrder);
                    System.out.println("Order confirmed.");
                    isOrderActive = false;
                    currentOrderNumber++; // Increment for the next order
                    break;
                case "2":
                    System.out.println("Order voided.");
                    currentOrder.getDrinks().clear();
                    isOrderActive = false;
                    break;
                case "3":
                    isOrderActive = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void viewQueue() {
        System.out.println("--------------------");
        System.out.println("Current Orders:");
        for (Order order : orderQueue) {
            System.out.println("Order #" + order.getOrderNumber() + ":");
            for (Drink drink : order.getDrinks()) {
                System.out.println("  - " + drink.getName() + " (" + drink.getPrice() + " PHP)");
            }
        }
        System.out.println("--------------------");
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenuOptions(); // Display the main menu
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    placeOrder(); // Place a new order
                    break;
                case "2":
                    viewQueue(); // View current orders
                    break;
                case "0":
                    System.out.println("Exiting...");
                    isRunning = false; // Stop the program
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        CafeSystem system = new CafeSystem();
        system.start(); // Start the cafe system
    }
}
