public class Tile {
    private TileStates state;
    private boolean mined;
    private int minesNear;

    public Tile(TileStates state, boolean mined, int minesNear) {
        this.state = state;
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

    }
    public TileInteractionResults markQuestion() {

    }
    public TileInteractionResults markClear() {

    }

    public String toString() {

    }

    private boolean isInteractable() {
        return this.state != TileStates.EXPLODED && this.state != TileStates.OPENED;
    }
}