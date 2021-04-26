public class Tile {
    private TileStates state;
    private boolean mined;
    private int minesNear;

    /**
     * Initialize a dummy tile object
     */
    public Tile() {
        this.state = TileStates.CLOSED;
    }
    /**
     * Initialize the tile
     * @param mined If the tile contains a mine
     * @param minesNear The number of adjacent tiles that have mines
     */
    public void populate(boolean mined, int minesNear) {
        this.mined = mined;
        this.minesNear = minesNear;
    }

    /**
     * Open the tile
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults open() {
        if (this.isInteractable()) {
            if (mined) {
                this.state = TileStates.EXPLODED;
                return TileInteractionResults.EXPLOSION;
            }
            else {
                this.state = TileStates.OPENED;
                return TileInteractionResults.SUCCESS;
            }
        }
        else
            return TileInteractionResults.INVALID_TILE;
    }
    /**
     * Mark (or unmark) the tile with a flag
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markFlag() {
        if (this.isInteractable()) {
            if (this.state == TileStates.MARKED_FLAG)
                this.state = TileStates.CLOSED;
            else
                this.state = TileStates.MARKED_FLAG;

            return TileInteractionResults.SUCCESS;
        }
        else
            return TileInteractionResults.INVALID_TILE;
    }
    /**
     * Mark (or unmark) the tile with a question mark
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markQuestion() {
        if (this.isInteractable()) {
            if (this.state == TileStates.MARKED_QUESTION)
                this.state = TileStates.CLOSED;
            else
                this.state = TileStates.MARKED_QUESTION;

            return TileInteractionResults.SUCCESS;
        }
        else
            return TileInteractionResults.INVALID_TILE;
    }
    /**
     * Clear all the marks from the tile
     * @return The result of the interaction {@link TileInteractionResults}
     */
    public TileInteractionResults markClear() {
        if (this.isInteractable()) {
            this.state = TileStates.CLOSED;
            return TileInteractionResults.SUCCESS;
        }
        else
            return TileInteractionResults.INVALID_TILE;
    }

    /** 
     * Converts the tile to readable format
    */
    public String toString() {
        String output = "[";

        // Display numbers for the opened tiles that have more than 0 mines nearby
        if (this.state == TileStates.OPENED && this.minesNear > 0)
            output += this.minesNear;
        else
            output += this.state;

        output += "] ";

        return output;
    }

    private boolean isInteractable() {
        return this.state != TileStates.EXPLODED && this.state != TileStates.OPENED;
    }
}