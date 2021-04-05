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
        this.tiles = new Tile[this.height][this.width];
        this.isPopulated = false;
    }

    public OpenTileResult openTile(int x, int y) {
        if (!this.isPopulated)
            populateField(coordsToIndex(x, y));

        return tiles[y][x].open();
    }
    public boolean markFlagTile(int x, int y) {
        return tiles[y][x].markFlag();
    }
    public boolean markQuestionTile(int x, int y) {
        return tiles[y][x].markQuestion();
    }
    
    private void populateField(int firstClickLocation) {
        // Generate all possible tile indexes (as an indexes) to use later to generate mines
        int size = this.width * this.height;
        ArrayList<Integer> possibleMineIndexes = new ArrayList<Integer>();

        for (int i = 0; i < size; i++)
            possibleMineIndexes.add(i);

        // Remove the tile that was the first to be opened (so the player can not explode on the first turn)
        possibleMineIndexes.remove(firstClickLocation);

        // Shuffle the indexes
        Random rand = new Random();
        for (int i = 0; i < size - 1; i++) {
            int temp = possibleMineIndexes.get(i);
            int randomIndex = rand.nextInt(size - 1);
            possibleMineIndexes.set(i, possibleMineIndexes.get(randomIndex));
            possibleMineIndexes.set(randomIndex, temp);
        }

        // Pick the first indexes and gemerate a boolean array putting the mines at those indexes
        int mineCount = (int)(this.difficulty * size);
        boolean[][] minedTiles = new boolean[this.height][this.width];
        for (int i = 0; i < mineCount; i++) {
            int[] coords = indexToCoords(possibleMineIndexes.get(i));
            minedTiles[coords[1]][coords[0]] = true;
        }

        // Finally initialize all mines
        // Cycle through each tile
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                // The tile is mined if the same index is TRUE inside the boolean array
                boolean mined = minedTiles[y][x];
                // The number of mines near is calculated by going through each valid tile nearby, checking if it should contain a mine
                int minesNear = 0;
                ArrayList<Integer[]> validIndexes = validIndexesNear(x, y);
                for (Integer[] coords : validIndexes)
                    if (minedTiles[coords[1]][coords[0]])
                        minesNear ++;
                // Initialize the tile
                this.tiles[y][x] = new Tile(mined, minesNear);
            }
        }

        this.isPopulated = true;
    }
	
	public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                System.out.print((tiles[y][x].getMined() ? "X" : ".") + tiles[y][x].getMinesNear() + "\t");
            System.out.println();
        }
    }

    public boolean isValidCoord(int x, int y) {
        // Has invalid X coordinate?
        if (x < 0 || x > width - 1)
            return false;
        // Has invalid Y coordinate
        if (y < 0 || y > height - 1)
            return false;
        // All checks passed, it is valid
        return true;
    }

    private int[] indexToCoords(int index) {
        return new int[] {index % this.width, index / this.width};
    }
    private int coordsToIndex(int x, int y) {
        return x + y * this.width;
    }
    private ArrayList<Integer[]> validIndexesNear(int x, int y) {
        ArrayList<Integer[]> validIndexes = new ArrayList<Integer[]>();
        // Check top left corner
        if (isValidCoord(x - 1, y - 1))
            validIndexes.add(new Integer[] {x - 1, y - 1});
        // Check top
        if (isValidCoord(x    , y - 1))
            validIndexes.add(new Integer[] {x    , y - 1});
        // Check top right corner
        if (isValidCoord(x + 1, y - 1))
            validIndexes.add(new Integer[] {x + 1, y - 1});
        // Check left side
        if (isValidCoord(x - 1, y    ))
            validIndexes.add(new Integer[] {x - 1, y    });
        // Check right side
        if (isValidCoord(x + 1, y    ))
            validIndexes.add(new Integer[] {x + 1, y    });
        // Check bottom left corner
        if (isValidCoord(x - 1, y + 1))
            validIndexes.add(new Integer[] {x - 1, y + 1});
        // Check bottom
        if (isValidCoord(x    , y + 1))
            validIndexes.add(new Integer[] {x    , y + 1});
        // Check bottom right
        if (isValidCoord(x + 1, y + 1))
            validIndexes.add(new Integer[] {x + 1, y + 1});

        return validIndexes;
    }
}