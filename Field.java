import java.util.ArrayList;
import java.util.Random;

public class Field {
    private int width;
    private int height;
    private double difficulty;
    private Tile[][] tiles;
    private boolean isPopulated;

    public Field(int width, int height, double difficulty) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.tiles = new Tile[height][width];  
        this.isPopulated = false;
    }
    public void restartField() {
        this.tiles = new Tile[height][width];
        this.isPopulated = false;
    }

    public void openTile(int x, int y) {
        if (!this.isPopulated)
            populateField(coordsToIndex(x, y));

        //TODO opening a tile
    }
    
    private void populateField(int firstClickLocation) {
        // Generate all possible tile indexes (as an index)
        int size = this.width * this.height;
        ArrayList<Integer> tileIndexes = new ArrayList<Integer>(size);

        for (int i = 0; i < size; i++)
            tileIndexes.add(i);

        tileIndexes.remove(firstClickLocation);

        // Shuffle
        Random rand = new Random();
        for (int i = 0; i < size - 1; i++) {
            int temp = tileIndexes.get(i);
            int randomIndex = rand.nextInt(size - 1);
            tileIndexes.set(i, tileIndexes.get(randomIndex));
            tileIndexes.set(randomIndex, temp);
        }

        // Generate boolean array with mines
        int mineCount = (int)(this.difficulty * size);
        boolean[][] minedTiles = new boolean[height][width];
        for (int i = 0; i < mineCount; i++) {
            int[] coords = indexToCoords(tileIndexes.get(i));
            minedTiles[coords[1]][coords[0]] = true;
        }

        // Finally initialize all mines
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                boolean mined = minedTiles[y][x];
                int minesNear = 0;
                
                this.tiles[y][x] = new Tile(mined, minesNear)
            }
        }
    }


    private int[] indexToCoords(int index) {
        return new int[] {index % this.width, index / this.width};
    }
    private int coordsToIndex(int x, int y) {
        return x + y * this.width;
    }
}