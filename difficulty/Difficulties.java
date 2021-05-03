package difficulty;

public enum Difficulties {
    //#region Enum entries
    TRIVIAL("Piece of cake"                    , 8, 6, 0.15, 2),
    EASY   ("Mine Me Plenty"                   , 8, 6, 0.15, 2),
    NORMAL ("Throu Art a Defuse Meister"       , 8, 6, 0.15, 2),
    HARD   ("They Call Me \"The Mine Sweeper\"", 8, 6, 0.15, 2),
    UNFAIR ("Minecore!"                        , 8, 6, 0.15, 2),
    CUSTOM ("Mine your own Custom Mine Field"  , 0, 0, 0   , 0);
    //#endregion

    //#region Enum fields
    private final String name;
    private final int width;
    private final int height;
    private final double minePercentage;
    private final int playerLives;
    //#endregion

    //#region Constructors
    private Difficulties(String name, int width, int height, double minePercentage, int playerLives) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.minePercentage = minePercentage;
        this.playerLives = playerLives;
    }
    //#endregion

    //#region Getters
    public int getPlayerLives() {
        return this.playerLives;
    }
    public double getMinePercentage() {
        return this.minePercentage;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public String getName() {
        return this.name;
    }
    //#endregion
}
