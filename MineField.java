import java.util.Random;

public class MineField {
    private int width;
    private int height;
    private double difficulty;
    private Tile[][] tiles;
    private boolean isPopulated;

    public MineField(int width, int height, double difficulty) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.tiles = new Tile[height][width];
        this.isPopulated = false;
    }

    public void populateField(int excludeX, int excludeY) {
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
        int minesToPlace = (int)Math.round(this.width * this.height * this.difficulty);
        for (int i = 0; i < minesToPlace; i++) {
            int coordX = this.indexToCoords(possibleTileIndexes[i])[0];
            int coordY = this.indexToCoords(possibleTileIndexes[i])[1];

            minedTiles[coordY][coordX] = true;
        }

        // Generate a 2d int array that stores the info about the number of mines near a tile
        int[][] minesNear = new int[this.height][this.width];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                minesNear[y][x] = this.countMinesNear(x, y, minedTiles);
                System.out.print(minedTiles[y][x] + "\t" + minesNear[y][x] + "\t\t");
            }
            System.out.println();
        }
    }
    /*
    public TileInteractionResults openAt(int x, int y) {

    }
    public TileInteractionResults markFlagAt(int x, int y) {

    }
    public TileInteractionResults markQuestionAt(int x, int y) {

    }
    public TileInteractionResults markClearAt(int x, int y) {

    }

    public String toString() {

    }
    */
    private boolean isValidCoord(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
    private int countMinesNear(int x, int y, boolean[][] minedTiles) {
        int minesNear = 0;
        for (int deltaY = -1; deltaY < 2; deltaY++) {
            for (int deltaX = -1; deltaX < 2; deltaX++) {
                int checkX = x + deltaX;
                int checkY = y + deltaY;
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
    private boolean[][] validCoordsNear(int x, int y) {
        boolean[][] output = new boolean[3][3];
        for (int checkY = 0; checkY < 3; checkY++)
            for (int checkX = 0; checkX < 3; checkX++)
                output[y][x] = isValidCoord(checkX - 1, checkY - 1);
        return output;
    }
}