import java.util.*;

public class MenuTable {
    private Scanner scanner = new Scanner(System.in);
    private Queue<Order> orderQueue;

    public MenuTable() {
        orderQueue = new LinkedList<>();
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(Queue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void displayOrderQueue() {
        System.out.println("Current Orders:");
        for (Order order : orderQueue) {
            System.out.println("Order #" + order.getOrderNumber());
            for (Drink drink : order.getDrinks()) {
                System.out.println(" - " + drink.getName());
            }
        }
    }

    public void placeOrder() {
        Map<String, List<Drink>> menu = createMenu();
    
        // Display menu and process orders
        while (true) {
            displayMenu(menu);
    
            double totalCost = 0;
            Queue<Drink> currentOrder = new LinkedList<>();
    
            System.out.println("Order Number #(" + orderQueue.size() + "):");
            System.out.print("Enter Product ID (separated by spaces): ");
            String userInput = scanner.nextLine();
    
            if (userInput.trim().isEmpty()) {
                continue;
            }
    
            if (userInput.equalsIgnoreCase("confirm")) {
                if (!currentOrder.isEmpty()) {
                    Order newOrder = new Order(orderQueue.size() + 1, currentOrder);
                    orderQueue.add(newOrder);
                    System.out.println("Order confirmed:");
                    for (Drink drink : currentOrder) {
                        System.out.println(" - " + drink.getName() + " (" + drink.getPrice() + " PHP)");
                        totalCost += drink.getPrice();
                    }
                    System.out.println("Total Cost: " + totalCost + " PHP");
                    System.out.println("Order Queue Size: " + orderQueue.size()); // Debug statement
                } else {
                    System.out.println("No items in the current order.");
                }
                break;
            } else if (userInput.equalsIgnoreCase("void")) {
                currentOrder.clear();
                totalCost = 0;
                System.out.println("Order voided.");
                continue;
            }
    
            String[] productIds = userInput.split(" ");
            for (String productId : productIds) {
                boolean found = false;
                for (List<Drink> drinks : menu.values()) {
                    for (Drink drink : drinks) {
                        if (drink.getId().equalsIgnoreCase(productId)) {
                            currentOrder.add(drink);
                            totalCost += drink.getPrice();
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid product ID: " + productId);
                }
            }
    
            System.out.println("\nCurrent Order:");
            for (Drink drink : currentOrder) {
                System.out.println(drink.getName() + " - " + drink.getPrice() + " PHP");
            }
            System.out.println("Total Cost: " + totalCost + " PHP");
    
            System.out.println("\nOptions:");
            System.out.println("1. Confirm");
            System.out.println("2. Void");
            System.out.println("3. Return");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
    
            switch (option) {
                case "1": // Confirm
                    if (!currentOrder.isEmpty()) {
                        Order newOrder = new Order(orderQueue.size() + 1, currentOrder);
                        orderQueue.add(newOrder);
                        System.out.println("Order confirmed.");
                        System.out.println("Order Queue Size: " + orderQueue.size()); // Debug statement
                    } else {
                        System.out.println("No items in the current order.");
                    }
                    return;
    
                case "2":
                    currentOrder.clear();
                    totalCost = 0;
                    System.out.println("Order voided.");
                    continue;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    

    private void displayMenu(Map<String, List<Drink>> menu) {
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
}

class Order {
    private int orderNumber;
    private Queue<Drink> drinks;

    public Order(int orderNumber, Queue<Drink> drinks) {
        this.orderNumber = orderNumber;
        this.drinks = drinks;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Queue<Drink> getDrinks() {
        return drinks;
    }
}
