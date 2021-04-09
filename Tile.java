public class Tile {
	//#region Fields
    private TileState state;
    private boolean mined;
    private int minesNear;
	//#endregion

	//#region Constructor
    /**
     * Initialize the tile object
     * @param mined If the tile has a mine
     * @param minesNear The number of mines in the adjacent tiles                                      
     */
    public Tile(boolean mined, int minesNear) {
        this.state = TileState.CLOSED;
        this.mined = mined;
        this.minesNear = minesNear;
    }
	//#endregion

	//#region Tile interaction
    /**
     * Open the tile (reveal the content)
     * @return The result (SUCCESS if the tile was correct, EXPLOSION if the tile had a mine, ERROR if the tile can't be opened)
     */
    public OpenTileResult open() {
        if (!isInteractable())
            return OpenTileResult.ERROR;
        else {
            if (this.mined) {
                this.state = TileState.EXPLODED;
                return OpenTileResult.EXPLOSION;
            }
            else {
                this.state = TileState.OPENED;
                return OpenTileResult.SUCCESS;
            }
        }
    }
    /**
     * Mark the tile with a flag
     * @return The result (TRUE if it can be marked, FALSE if it can't)
     */
    public boolean markFlag() {
        if (isInteractable()) {
            if (this.state == TileState.MARKED_FLAG)
                this.state = TileState.CLOSED;
            else
                this.state = TileState.MARKED_FLAG;

            return true;
        }
        else
            return false;
    }
    /**
     * Mark the tile with a question mark
     * @return The result (TRUE if it can be marked, FALSE if it can't)
     */
    public boolean markQuestion() {
        if (isInteractable()) {
            if (this.state == TileState.MARKED_QUESTION)
                this.state = TileState.CLOSED;
            else
                this.state = TileState.MARKED_QUESTION;
            
            return true;
        }
        else
            return false;
    }
    /**
     * Clear all the marks from the tile
     * @return The result (TRUE if it can be cleared, FALSE if it can't)
     */
    public boolean markClear() {
        if (isInteractable()) {
            this.state = TileState.CLOSED;
            return true;
        }
        else
            return false;
    }
	//#endregion

	//#region Other methods
    @Override
    public String toString() {
        switch (this.state) {
            case CLOSED:
                return "C";
            case EXPLODED:
                return "E";
            case MARKED_FLAG:
                return "F";
            case MARKED_QUESTION:
                return "Q";
            case OPENED:
                return "O";
            default:
                return "-";
        }
    }
	//#endregion

	//#region Helper methods
    private boolean isInteractable() {
        return !(this.state == TileState.OPENED || this.state == TileState.EXPLODED);
    }
	//#endregion
}