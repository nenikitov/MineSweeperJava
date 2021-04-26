public enum TileStates {
    CLOSED           ('■'),
    OPENED           (' '),
    MARKED_FLAG      ('P'),
    MARKED_QUESTION  ('?'),
    EXPLODED         ('*');

    private final char displayChar;

    private TileStates(char displayChar) {
        this.displayChar = displayChar;
    }

    public String toString() {
        return Character.toString(this.displayChar);
    }
}