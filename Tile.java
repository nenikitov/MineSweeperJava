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
        
    }
}