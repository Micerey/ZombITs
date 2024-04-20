import java.util.*;

public class MenuTable {
    private Queue<Drink> orderQueue;
    
    public MenuTable() {
        this.orderQueue = new LinkedList<>();
    }
    
    public Queue<Drink> getOrderQueue() {
        return orderQueue;
    }
    
    public void setOrderList(Queue<Drink> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void displayOrderQueue() {
        System.out.println("Current Orders:");
        for (Drink drink : orderQueue) {
            System.out.println(drink.getName());
        }
    }

    public static void placeOrder() {
        Map<String, List<Drink>> menu = new LinkedHashMap<>();
 
        menu.put("ICED COFFEE", Arrays.asList(
            new Drink("A1", "IC BRUSKO"), new Drink("A2", "IC MACCHIATO"), new Drink("A3", "IC MOCA"),
            new Drink("A4", "IC VANILLA"), new Drink("A5", "IC FUDGE"), new Drink("A6", "IC MATCHA"),
            new Drink("A7", "IC KARAMEL"), new Drink("A8", "IC WHITECHOCO"), new Drink("A9", "IC VIETNAMESE")
        ));
        
        menu.put("PRAF", Arrays.asList(
            new Drink("B1", "P COOKIES&CREAM"), new Drink("B2", "P TARO"), new Drink("B3", "P MATCHA"),
            new Drink("B4", "P STRAWBERRY"), new Drink("B5", "P CHOCOLATE"), new Drink("B6", "P MELON DEW"),
            new Drink("B7", "P AVOCADO"), new Drink("B8", "P MANGO"), new Drink("B9", "P BANANA")
        ));
        
        menu.put("HOTBREW", Arrays.asList(
            new Drink("C1", "HB BRUSKO"), new Drink("C2", "HB MACCHIATO"), new Drink("C3", "HB MOCA"),
            new Drink("C4", "HB VANILLA"), new Drink("C5", "HB FUDGE"), new Drink("C6", "HB MATCHA"),
            new Drink("C7", "HB KARAMEL"), new Drink("C8", "HB WHITECHOCO"), new Drink("C9", "HB VIETNAMESE")
        ));

        int maxDrinks = menu.values().stream().mapToInt(List::size).max().orElse(0);
        Scanner scanner = new Scanner(System.in);

        MenuTable menuTable = new MenuTable();
        
        while (true) {
            for (String category : menu.keySet()) {
                System.out.printf("%-20s", category);
            }
            System.out.println();

            for (int i = 0; i < 20 * menu.size(); i++) {
                System.out.print("-");
            }
            System.out.println();
            
            for (int i = 0; i < maxDrinks; i++) {
                for (List<Drink> drinks : menu.values()) {
                    if (i < drinks.size()) {
                        Drink drink = drinks.get(i);
                        System.out.printf("%-20s", drink.getId() + ": " + drink.getName());
                    } else {
                        System.out.printf("%-20s", "");
                    }
                }
                System.out.println();
            }

            for (int i = 0; i < 20 * menu.size(); i++) {
                System.out.print("-");
            }
            System.out.println();

            Queue<Drink> orderQueue = menuTable.getOrderQueue();
            double totalCost = 0;

            System.out.println("Order Number #(number in queue):");
            System.out.print("Enter Product ID (separated by spaces): ");
            String userInput = scanner.nextLine();

            if (userInput.trim().isEmpty()) {
                continue;
            }

            if (userInput.equalsIgnoreCase("confirm")) {
                System.out.println("Finalized Order:");
                for (Drink drink : orderQueue) {
                    System.out.println(drink.getName() + " - " + drink.getPrice() + " PHP");
                }
                System.out.println("Total Cost: " + totalCost + " PHP");
                break;
            } else if (userInput.equalsIgnoreCase("void")) {
                menuTable.setOrderList(new LinkedList<>()); 
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
                            orderQueue.add(drink);
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
            for (Drink drink : orderQueue) {
                System.out.println(drink.getName() + " - " + drink.getPrice() + " PHP");
            }
            System.out.println();
            System.out.println("Total Cost: " + totalCost + " PHP");

            System.out.println("\nOptions:");
            System.out.println("1. Confirm");
            System.out.println("2. Void");
            System.out.println("3. View Queue");
            System.out.println("4. Return");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Finalized Order:");
                    for (Drink drink : orderQueue) {
                        System.out.println(drink.getName() + " - " + drink.getPrice() + " PHP");
                    }
                    System.out.println("Total Cost: " + totalCost + " PHP");
                    break;
                case "2":
                    menuTable.setOrderList(new LinkedList<>());
                    totalCost = 0;
                    System.out.println("Order voided.");
                    continue;
                case "3":
                    System.out.println();
                    menuTable.displayOrderQueue();
                    System.out.println();
                case "4":
                    continue;
                default:
                    System.out.println("Invalid option.");
            }

        }
    }
}

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
