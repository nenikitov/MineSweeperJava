public class Testing {
    public static void main(String[] args) {
        Tile tile = new Tile(true, 0);
        tile.open();
        tile.markClear();
        System.out.println(tile);
    }    
}
