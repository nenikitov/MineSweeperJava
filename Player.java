/**
 * The class that stores the state of the player. Is used to calculate the lives that the player has
 */
public class Player {
    //#region Fields
    private int lives;
    //#endregion

    //#region Constructors
    /**
     * Initialize the player
     * @param lives The number of lives that the player has
     */
    public Player(int lives) {
        this.lives = lives;
    }
    //#endregion

    //#region Other
    /**
     * Subtract a live and return if game should be over
     * @return True - game should be over, false - game should continue
     */
    public boolean openedMine() {
        this.lives--;
        return this.lives <= 0;
    }
    //#endregion
}