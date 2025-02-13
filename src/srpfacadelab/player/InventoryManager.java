package srpfacadelab;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Item> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    // Checks if adding the given item would exceed the carrying capacity
    public boolean canAddItem(Item item, int carryingCapacity) {
        return (calculateInventoryWeight() + item.getWeight()) <= carryingCapacity;
    }

    // Checks if the item already exists in the inventory (used for unique items)
    public boolean itemExists(Item item) {
        for (Item i : inventory) {
            if (i.getId() == item.getId()) {
                return true;
            }
        }
        return false;
    }

    // Adds an item to the inventory
    public void addItem(Item item) {
        inventory.add(item);
    }

    // Calculates the total weight of all items in the inventory
    public int calculateInventoryWeight() {
        int sum = 0;
        for (Item i : inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    // Calculates the total armour provided by the items in the inventory
    public int calculateTotalArmour() {
        int totalArmour = 0;
        for (Item i : inventory) {
            totalArmour += i.getArmour();
        }
        return totalArmour;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}