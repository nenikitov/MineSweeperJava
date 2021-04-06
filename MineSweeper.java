public class MineSweeper {
    public static void main(String[] args) {

        // Maybe implement difficulties?
        // I'm too Young to Explode
        // Piece of Cake
        // Mine Me Plenty
        // Thou Art a Defuse Meister
        // They Call Me "The Mine Sweeper"
        // Minecore!
        // Custom

        Field field = new Field(10,5,0.2);
        field.openTile(0, 0);
        field.openTile(1, 0);
        field.openTile(2, 0);
        field.openTile(3, 0);

        System.out.println(field);
    }
}
