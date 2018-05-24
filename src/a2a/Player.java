package a2a;

/**
 * Implements a player.
 * @author Victor Starzynski
 * @version 1.0
 */
public class Player implements java.io.Serializable {

    /**
     * Name of the Player.
     * At this stage it either "White" or "Black"
     */
    private String playerName;
    
    /**
     * Constructor of the class.
     * @param name Name of the Player.
     */
    public Player(String name) {
        playerName = name;
    }
    
    /**
     * Gets the name of the player.
     * @return the name.
     */
    public String getPlayerName() {
        return playerName;
    }
}
