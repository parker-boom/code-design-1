package srpfacadelab;

import java.util.List;

public class ItemUsageHandler {

    // Handles item usage for a player
    public void useItem(RpgPlayer player, Item item, IGameEngine gameEngine) {
        if ("Stink Bomb".equals(item.getName())) {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);
            if (enemies != null) {
                for (IEnemy enemy : enemies) {
                    enemy.takeDamage(100);
                }
            }
        }
    }
}