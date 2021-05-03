package gameplay;

import java.util.Random;
import input.InputHandler;
import tiles.Tile;
import tiles.TileInteractionResults;
import tiles.TileStates;

/**
 * The class that generates and stores the state of the gameplay mine field. Is used to interact with tiles
 */
public class MineField {
    //#region Fields
    private int width;
    private int height;
    private double difficulty;
    private Tile[][] tiles;
    private boolean isPopulated;
    private int mines;
    //#endregion

    //#region Constructors
    /**
     * Initialize the mine field object
     * @param widt The width of a playing field
     * @param height The height of a playing field
     * @param difficulty The difficulty (percentage from 0 to 1) that indicated the mine count
     */
    public MineField(int width, int height, double difficulty) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.isPopulated = false;
        mines = (int)Math.round(this.width * this.height * this.difficulty);
        this.tiles = new Tile[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.tiles[y][x] = new Tile();
    }
    //#endregion

    //#region Mine placement
    private void populateField(int excludeX, int excludeY) {
        // Generate the array of all possible indexes where the mine can be places (except the exclude coodinate - first move)
        int excludeIndex = coordsToIndex(excludeX, excludeY);
        int[] possibleTileIndexes = new int[this.width * this.height - 1];
        for (int i = 0; i < possibleTileIndexes.length; i++)
            possibleTileIndexes[i] = i + (i >= excludeIndex ? 1 : 0);

        // Shuffle it
        Random random = new Random();
        for (int i = 0; i < possibleTileIndexes.length; i++) {
            int temp = possibleTileIndexes[i];
            int randomIndex = random.nextInt(possibleTileIndexes.length);
            possibleTileIndexes[i] = possibleTileIndexes[randomIndex];
            possibleTileIndexes[randomIndex] = temp;
        }

        // Generate a 2d boolean array that stores the info about the tiles that should be mines
        boolean[][] minedTiles = new boolean[this.height][this.width];
        for (int i = 0; i < mines; i++) {
            int coordX = this.indexToCoords(possibleTileIndexes[i])[0];
            int coordY = this.indexToCoords(possibleTileIndexes[i])[1];

            minedTiles[coordY][coordX] = true;
        }

        // Generate a 2d int array that stores the info about the number of mines near a tile
        int[][] minesNear = new int[this.height][this.width];
        for (int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++)
                minesNear[y][x] = this.countMinesNear(x, y, minedTiles);

        // Initialize all the tiles
        for (int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++)
                tiles[y][x].populate(minedTiles[y][x], minesNear[y][x]);
        
        this.isPopulated = true;
    }
    //#endregion

    //#region Tile interaction
    /**
     * Open the tile at specific coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults openAt(int x, int y) {
        // Check if the tile is inside the mine field
        if (!isValidCoord(x, y))
            throw new IndexOutOfBoundsException("The coordinates are outside the mine field.");

        // If it is the first move - generate mines
        if (!this.isPopulated)
            this.populateField(x, y);

        // Open the tile
        if (this.tiles[y][x].getMinesNear() != 0 || this.tiles[y][x].getMined())
            return tiles[y][x].open();
        else {
            this.tiles[y][x].open();
            for (int relativeY = y - 1; relativeY <= y + 1; relativeY++) {
                for (int relativeX = x - 1; relativeX <= x + 1; relativeX++) {
                    if (this.isValidCoord(relativeX, relativeY)) {
                        if (this.tiles[relativeY][relativeX].getState() != TileStates.OPENED)
                            openAt(relativeX, relativeY);
                    }
                }
            }
            return TileInteractionResults.SUCCESS;
        }
    }
    /**
     * Mark with a flag the tile at specific coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markFlagAt(int x, int y) {
        // Check if the tile is inside the mine field
        if (!isValidCoord(x, y))
            throw new IndexOutOfBoundsException("The coordinates are outside the mine field.");

        // Mark the tile with a flag
        return tiles[y][x].markFlag();
    }
    /**
     * Mark with a question mark the tile at specific coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markQuestionAt(int x, int y) {
        // Check if the tile is inside the mine field
        if (!isValidCoord(x, y))
            throw new IndexOutOfBoundsException("The coordinates are outside the mine field.");

        // Mark the tile with a question
        return tiles[y][x].markQuestion();
    }
    /**
     * Clear marks the tile at specific coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markClearAt(int x, int y) {
        // Check if the tile is inside the mine field
        if (!isValidCoord(x, y))
            throw new IndexOutOfBoundsException("The coordinates are outside the mine field.");

        // Clear the marks from the tile
        return tiles[y][x].markClear();
    }

    /**
     * Return if the game is won (all the tiles that should be opened are opened)
     * @return Is the game won
     */
    public boolean isGameWon() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                // Increment the counter for each tile that is empty, but which is not opened
                if (!this.tiles[y][x].getMined()
                    && (this.tiles[y][x].getState() != TileStates.OPENED && this.tiles[y][x].getState() != TileStates.EXPLODED))
                    return false;
            }
        }

        return true;
    }
    //#endregion

    //#region Other
    /** 
     * Convert the mine field to readable format
    */
    public String toString() {
        String output = "   |";
        // Print top coordinates
        for (int x = 0; x < this.width; x++) {
            String coord = InputHandler.parseToAlphabetNumber(x);
            for (int i = coord.length(); i < 3; i++)
                coord += " ";
            coord += "|";
            output += coord;
        }
        output += "\n";

        // Print line divisor
        for (int x = 0; x <= this.width; x++) {
            output += "---+";
        }
        output += "\n";

        // Go through each tile
        for (int y = 0; y < this.height; y++) {
            String coord = Integer.toString(y + 1);
            for (int i = coord.length(); i < 3; i++)
                coord += " ";
            output += coord + "|";
            for (int x = 0; x < this.width; x++) {
                output += tiles[y][x];
            }
            output += "\n";
        }

        return output;
    }
    /**
     * Get the number of mines that this minefield contains
     * @return Number of mines
     */
    public int getMines() {
        return this.mines;
    }
    /**
     * Get the number of tiles that are flagged
     * @return The number of tiles that are flagged
     */
    public int getFlags() {
        int counter = 0;

        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getState() == TileStates.MARKED_FLAG)
                    counter++;
            }
        }

        return counter;
    }
    //#endregion
    
    //#region Helper methods
    private boolean isValidCoord(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
    private int countMinesNear(int x, int y, boolean[][] minedTiles) {
        int minesNear = 0;
        // Go through each tile which is inside 3x3 square centered aroung the target tile
        for (int deltaY = -1; deltaY < 2; deltaY++) {
            for (int deltaX = -1; deltaX < 2; deltaX++) {
                // Caclulate its coordinates
                int checkX = x + deltaX;
                int checkY = y + deltaY;
                // If it is valid (inside the field) and if it contains a mine - increment the counter
                if (isValidCoord(checkX, checkY)) {
                    if (minedTiles[checkY][checkX])
                        minesNear++;
                }
            }
        }
        return minesNear;
    }
    private int[] indexToCoords(int index) {
        return new int[] { index % width, index / width }; 
    }
    private int coordsToIndex(int x, int y) {
        return y * width + x;
    }    
    //#endregion
}
