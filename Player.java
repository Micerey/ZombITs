// Player.java
import java.util.Random;

public class Player {
    private int lives;
    private int progress;
    private int stamina;
    private String currentLocation;  // New field to store the current location
    private String currentItem;  // New field to store the current item
    private Inventory inventory;

    public Player(int lives, int progress, int stamina, Inventory inventory) {
        this.lives = lives;
        this.progress = progress;
        this.stamina = stamina;
        this.inventory = inventory;
    }

    // Add a constructor with default values
    public Player() {
        this(3, 0, 100, new Inventory());
    }

    public int getLives() {
        return lives;
    }

    public int getProgress() {
        return progress;
    }

    public int getStamina() {
        return stamina;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }

    public String getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(String item) {
        this.currentItem = item;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void loseLife() {
        lives--;

        if (lives == 0) {
            System.out.println("You lost all your lives. Game over!");
            System.exit(0);
        }
    }

    public void updateStamina(int distance) {
        stamina -= distance;
        if (stamina < 0) {
            stamina = 0;
        }
    }

    public void restoreLives(int points) {
        lives += points;
    }

    public void restoreEnergy(int points) {
        stamina += points;
        if (stamina > 100) {
            stamina = 100;
        }
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
        if (this.stamina > 100) {
            this.stamina = 100;
        } else if (this.stamina < 0) {
            this.stamina = 0;
        }
    }
    

    public boolean hasItem(String itemName) {
        return inventory.getItems().containsKey(itemName) && inventory.getItems().get(itemName) > 0;
    }

    public boolean hasEnoughWoodenPlanks(int count) {
        return inventory.getItems().containsKey("Wooden Planks") && inventory.getItems().get("Wooden Planks") >= count;
    }

    public int getWoodenPlanksCount() {
        return inventory.getItems().getOrDefault("Wooden Plank", 0);
    }
    
    @Override
    public String toString() {
        return "Player{" +
                "lives=" + lives +
                ", progress=" + progress +
                ", stamina=" + stamina +
                ", currentLocation='" + currentLocation + '\'' +
                ", currentItem='" + currentItem + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
