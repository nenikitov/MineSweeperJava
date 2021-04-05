/** The result of opening a tile */
public enum OpenTileResult {
    /** The tile didn't contain a mine */
    SUCCESS,
    /** The tile contained a mine */
    EXPLOSION,
    /** The tile can't be opened */
    ERROR
}
