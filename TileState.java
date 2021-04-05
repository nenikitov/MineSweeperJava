/** All the possible states of the tile */
public enum TileState {
    /** The tile is closed, the content is hidden */
    CLOSED,
    /** The tile is opened and it didn't contain a mine */
    OPENED,
    /** The tile is marked with a flag, there is a big chance it has a mine */
    MARKED_FLAG,
    /** The tile is marked with a question mark, there might be a mine there */
    MARKED_QUESTION,
    /** The tile is opened and it contains a mine */
    EXPLODED
}