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
        
    }
    
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

    private boolean isValidCoord(int x, int y) {

    }
    private int countMinesNear(int x, int y, boolean[][] minedTiles) {

    }
    private int[] indexToCoords(int index) {

    }
    private int coordsToIndex(int x, int y) {

    }
    private int[][] validIndexesNear(int x, int y) {

    }
}