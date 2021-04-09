public class MineSweeper {
    public static void main(String[] args) {

        // Maybe implement difficulties?
        // Piece of Cake
        // Mine Me Plenty
        // Thou Art a Defuse Meister
        // They Call Me "The Mine Sweeper"
		// Ulta mine field
        // Minecore!
        // Custom

        Player player = new Player(1);
        MineField mineField = new MineField(10, 20, 0.2);

        mineField.openTile(0, 0);
        System.out.println(mineField);
    }
}
