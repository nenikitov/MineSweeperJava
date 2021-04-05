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

    public boolean getMined() {
        return this.mined;
    }
    public int getMinesNear() {
        return this.minesNear;
    }
}