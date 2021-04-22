public class MineSweeper {
    public static void main(String[] args) {
        Tile tile = new Tile(true, 0);
        tile.markQuestion();
        tile.open();
        System.out.println(tile);
    }    
}