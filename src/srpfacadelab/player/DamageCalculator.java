package srpfacadelab;

public class DamageCalculator {
    
    // Calculates the effective damage (using idea from phase 1)
    public int calculateDamage(int damage, int armour, int carryingCapacity, int currentInventoryWeight) {
        // Apply a 25% damage reduction if carrying less than 50% of the capacity.
        if (currentInventoryWeight < carryingCapacity / 2) {
            damage = (int) Math.round(damage * 0.75);
        }
        return damage - armour;
    }

    // Determines if a parry effect should be played
    public boolean isParry(int damage, int armour) {
        return damage < armour;
    }
}