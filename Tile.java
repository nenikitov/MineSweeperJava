public class Tile {
    private TileState state;
    private boolean mined;
    private int minesNear;

    public Tile(boolean mined, int minesNear) {
        this.state = TileState.CLOSED;
        this.mined = mined;
        this.minesNear = minesNear;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
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
    public void markFlag() {
        if (isInteractable())
        {
            if (this.state == TileState.MARKED_FLAG)
                this.state = TileState.CLOSED;
            else
                this.state = TileState.MARKED_FLAG;
        }
    }
    public void markQuestion() {
        if (isInteractable())
        {
            if (this.state == TileState.MARKED_QUESTION)
                this.state = TileState.CLOSED;
            else
                this.state = TileState.MARKED_QUESTION;
        }
    }
    public void markClear() {
        if (isInteractable())
            this.state = TileState.CLOSED;
    }

    public boolean getMined() {
        return this.mined;
    }
    public int getMinesNear() {
        return this.minesNear;
    }


    private boolean isInteractable() {
        return !(this.state == TileState.OPENED || this.state == TileState.EXPLODED);
    }
}