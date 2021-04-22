public class Tile {
    private TileStates state;
    private boolean mined;
    private int minesNear;

    /**
     * Initialize the tile object
     * @param mined If the tile contains a mine
     * @param minesNear The number of adjacent tiles that have mines
     */
    public Tile(boolean mined, int minesNear) {
        this.state = TileStates.CLOSED;
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
        switch (this.state) {
            case CLOSED:
                output += '■';
                break;
            case OPENED:
                if (this.minesNear == 0)
                    output += " ";
                else
                    output += this.minesNear;
                break;
            case EXPLODED:
                output += 'X';
                break;
            case MARKED_FLAG:
                output += 'P';
                break;
            case MARKED_QUESTION:
                output += '?';
                break;
        }
        output += "] ";

        return output;
    }

    private boolean isInteractable() {
        return this.state != TileStates.EXPLODED && this.state != TileStates.OPENED;
    }
}