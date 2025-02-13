package srpfacadelab;

public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    // How much the player can carry in pounds
    private int carryingCapacity;

    // Import the new helper classes
    private InventoryManager inventoryManager;
    private DamageCalculator damageCalculator;
    private ItemUsageHandler itemUsageHandler;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.carryingCapacity = MAX_CARRYING_CAPACITY;
        this.inventoryManager = new InventoryManager();
        this.damageCalculator = new DamageCalculator();
        this.itemUsageHandler = new ItemUsageHandler();
    }

    // Since this is an action done by the player, it should be done in the RpgPlayer class
    public boolean pickUpItem(Item item) {
        // Check if there is enough capacity
        if (!inventoryManager.canAddItem(item, carryingCapacity))
            return false;

        // For unique items, ensure it is not already in the inventory
        if (item.isUnique() && inventoryManager.itemExists(item))
            return false;

        // If the item heals, consume it immediately
        if (item.getHeal() > 0) {
            health += item.getHeal();
            if (health > maxHealth)
                health = maxHealth;

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }
            return true;
        }

        // Play special effects based on the item's properties
        if (item.isRare() && item.isUnique()) {
            gameEngine.playSpecialEffect("blue_swirly");
        } else if (item.isRare()) {
            gameEngine.playSpecialEffect("cool_swirly_particles");
        }

        // Add the item to the inventory and update the player's armour
        inventoryManager.addItem(item);
        updateArmour();
        return true;
    }

    // Use an item
    public void useItem(Item item) {
        itemUsageHandler.useItem(this, item, gameEngine);
    }

    // Update the player's armour based on the inventory
    private void updateArmour() {
        this.armour = inventoryManager.calculateTotalArmour();
    }

    // Again, since this is an action done TO the player, it should be done in the RpgPlayer class
    public void takeDamage(int damage) {
        int currentWeight = inventoryManager.calculateInventoryWeight();

        if (damageCalculator.isParry(damage, armour)) {
            gameEngine.playSpecialEffect("parry");
        }

        int effectiveDamage = damageCalculator.calculateDamage(damage, armour, carryingCapacity, currentWeight);
        health -= effectiveDamage;
        gameEngine.playSpecialEffect("lots_of_gore");
    }

    // Getters and setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }
}
