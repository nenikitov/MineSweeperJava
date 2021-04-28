public class Player {
    private int lives;

    public Player(int lives) {
        this.lives = lives;
    }

    public boolean openedMine() {
        this.lives--;
        return this.lives <= 0;
    }
}