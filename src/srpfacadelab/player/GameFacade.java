// Design Choices: I kept all functions dealing directly with the player in the player class. This class just acts as a "middle man" so that we can use this, change the underlying functionality, and the rest of the code doesn't need to change.
package srpfacadelab;

import java.util.ArrayList;
import java.util.List;

// An interface for interacting with the player from the perspective of the game engine
public class GameFacade {
    private IGameEngine gameEngine;
    private List<RpgPlayer> players;

    // Constructor
    public GameFacade(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.players = new ArrayList<>();
    }

    // Create a new player and register it
    public RpgPlayer createPlayer() {
        RpgPlayer player = new RpgPlayer(gameEngine);
        players.add(player);
        return player;
    }

    // Have the current player pick up an item
    public boolean pickUpItem(RpgPlayer player, Item item) {
        return player.pickUpItem(item);
    }

    // Have the current player use an item
    public void useItem(RpgPlayer player, Item item) {
        player.useItem(item);
    }

    // Apply damage to the current player
    public void applyDamage(RpgPlayer player, int damage) {
        player.takeDamage(damage);
    }

    // Return a list of all players
    public List<RpgPlayer> getPlayers() {
        return players;
    }
}