import java.util.ArrayList;
import java.util.Collections;

public class MineField {
	//#region Fields
    private int width;
    private int height;
    private double difficulty;
    private Tile[][] tiles;
    private boolean isPopulated;
	//#endregion

	//#region Constructor
    /**
     * Initialize the field
     * @param width The width of the field (in tiles)
     * @param height The height of the field (in tiles)
     * @param difficulty The difficulty (Percentage of the mines, from 0 (0%) to 1 (100%))
     */
    public MineField(int width, int height, double difficulty) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.tiles = new Tile[height][width];  
        this.isPopulated = false;
    }
    //#endregion
	
	//#region Tile generation
    /** Restart the field and repopulate it with new mines */
	public void restartField() {
        this.tiles = new Tile[this.height][this.width];
        this.isPopulated = false;
    }
    private void populateField(int firstClickLocation) {
        // Generate all possible tile indexes (as an indexes) to use later to generate mines
        int size = this.width * this.height;
        ArrayList<Integer> possibleMineIndexes = new ArrayList<Integer>();

        // Generate consecutive numbers to avoid repetition in mine generation
        for (int i = 0; i < size; i++)
            possibleMineIndexes.add(i);

        // Remove the tile that was the first to be opened (so the player can not explode on the first turn)
        possibleMineIndexes.remove(firstClickLocation);

        // Shuffle the indexes
        Collections.shuffle(possibleMineIndexes);

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
                int minesNear = countMinesNear(minedTiles, x, y);
                // Initialize the tile
                this.tiles[y][x] = new Tile(mined, minesNear);
            }
        }

        this.isPopulated = true;
    }
	//#endregion
	
	//#region Tile interaction
    /**
     * Open a tile
     * @param x The x coordinate of the tile (goes from 0 left to WIDTH - 1 right)
     * @param y The y coordinate of the tile (goes from 0 top to HEIGHT - 1 bottom)
     * @return The result (SUCCESS if the tile was correct, EXPLOSION if the tile had a mine, ERROR if the tile can't be opened)
     */
    public OpenTileResult openTile(int x, int y) {
        if (!this.isPopulated)
            populateField(coordsToIndex(x, y));

        return tiles[y][x].open();
    }
    /**
     * Mark a tile with a flag (probably there is a mine on the tile)
     * @param x The x coordinate of the tile (goes from 0 left to WIDTH - 1 right)
     * @param y The y coordinate of the tile (goes from 0 top to HEIGHT - 1 bottom)
     * @return The result (TRUE if it can be marked, FALSE if it can't)
     */
    public boolean markFlagTile(int x, int y) {
        return tiles[y][x].markFlag();
    }
    /**
     * Mark a tile with a question mark (there might be a mine on this tile)
     * @param x The x coordinate of the tile (goes from 0 left to WIDTH - 1 right)
     * @param y The y coordinate of the tile (goes from 0 top to HEIGHT - 1 bottom)
     * @return The result (TRUE if it can be marked, FALSE if it can't)
     */
    public boolean markQuestionTile(int x, int y) {
        return tiles[y][x].markQuestion();
    }
    /**
     * Clear the mark on the tile
     * @param x The x coordinate of the tile (goes from 0 left to WIDTH - 1 right)
     * @param y The y coordinate of the tile (goes from 0 top to HEIGHT - 1 bottom)
     * @return The result (TRUE if it can be cleared, FALSE if it can't)
     */
    public boolean markClearTile(int x, int y) {
        return tiles[y][x].markClear();
    }
    //#endregion

	//#region Other methods
    @Override
	public String toString() {
        String returnResult = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                returnResult += tiles[y][x] + "\t";
            returnResult += "\n";
        }
        return returnResult;
    }
	//#endregion

	//#region Helper methods
    /**
     * Checks if the tile coordinate is valid (if it is inside the valid range from 0 to WIDTH or HEIGHT - 1)
     * @param x The x coordinate of the tile (goes from 0 left to WIDTH - 1 right)
     * @param y The y coordinate of the tile (goes from 0 top to HEIGHT - 1 bottom)
     * @return If the tile coords are valid
     */
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
    private int countMinesNear(boolean[][] minedTiles, int x, int y) {
        int counter = 0;
        ArrayList<Integer[]> validIndexes = validIndexesNear(x, y);
        for (Integer[] coords : validIndexes)
            if (minedTiles[coords[1]][coords[0]])
                counter++;
        return counter;
    }
	private int[] indexToCoords(int index) {
        return new int[] {index % this.width, index / this.width};
    }
    private int coordsToIndex(int x, int y) {
        return x + y * this.width;
    }
    private ArrayList<Integer[]> validIndexesNear(int x, int y) {
        ArrayList<Integer[]> validIndexes = new ArrayList<Integer[]>();

        // Check the tiles in a 3x3 square pattern
        for (int offsetY = - 1; offsetY <= 1; offsetY++) {
            for (int offsetX = - 1; offsetX <= 1; offsetX++) {
                int coordX = x + offsetX;
                int coordY = y + offsetY;

                // Add valid index only if it is inside the field and if it is not an initial tile
                if (isValidCoord(coordX, coordY) && (coordX != x && coordY != y))
                    validIndexes.add(new Integer[] {coordX, coordY});
            }
        }

        return validIndexes;
    }
	//#endregion
}