package tiles;

/**
 * The list of all the tile states
 */
public enum TileStates {
    //#region enum entries
    CLOSED           ('\u25A0'),
    OPENED           (' '),
    MARKED_FLAG      ('\u2691'),
    MARKED_QUESTION  ('?'),
    EXPLODED         ('\u0436');
    //#endregion

    //#region Fields
    private final char displayChar;
    //#endregion

    //#region Constructors
    private TileStates(char displayChar) {
        this.displayChar = displayChar;
    }
    //#endregion

    //#region Other
    /**
     * Convert the tile type to character that will be displayed in the minefield
     */
    public String toString() {
        return Character.toString(this.displayChar);
    }
    //#endregion
}
