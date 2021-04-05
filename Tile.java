public class Tile {
    private TileState state;
    private boolean mined;
    private int minesNear;

    public Tile(boolean mined, int minesNear) {
        this.state = TileState.CLOSED;
        this.mined = mined;
        this.minesNear = minesNear;
    }

    public void setMined() {
        this.mined = true;
    }
    public void setMinesNear(int minesNear) {
        this.minesNear = minesNear;
    }

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
    public boolean markClear() {
        if (isInteractable()) {
            this.state = TileState.CLOSED;
            return true;
        }
        else
            return false;
    }

    public boolean getMined() {
        return this.mined;
    }
    public int getMinesNear() {
        return this.minesNear;
    }

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

    private boolean isInteractable() {
        return !(this.state == TileState.OPENED || this.state == TileState.EXPLODED);
    }
}