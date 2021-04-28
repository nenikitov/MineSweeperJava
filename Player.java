public class Player {
    private int lives;

    /**
     * Initialize the player
     * @param lives The number of lives that the player has
     */
    public Player(int lives) {
        this.lives = lives;
    }

    /**
     * Subtract a live and return if game should be over
     * @return True - game should be over, false - game should continue
     */
    public boolean openedMine() {
        this.lives--;
        return this.lives <= 0;
    }
}