public class Tile {
    private TileStates state;
    private boolean mined;
    private int minesNear;

    public Tile(boolean mined, int minesNear) {
        this.state = TileStates.CLOSED;
        this.mined = mined;
        this.minesNear = minesNear;
    }

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
    public TileInteractionResults markClear() {
        if (this.isInteractable()) {
            this.state = TileStates.CLOSED;
            return TileInteractionResults.SUCCESS;
        }
        else
            return TileInteractionResults.INVALID_TILE;
    }

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