package difficulty;

public enum Difficulties {
    //#region Enum entries
    TRIVIAL("Piece of Cake"                    , "Easy mode for beginners"                  , 8 , 6 , 0.1 , 2),
    EASY   ("Mine Me Plenty"                   , "A bit harder, but still easy"             , 10, 8 , 0.12, 2),
    NORMAL ("Throu Art a Defuse Meister"       , "The right mode for intermediate players"  , 15, 8 , 0.15, 1),
    HARD   ("They Call Me \"The Mine Sweeper\"", "About as difficult as you want to go"     , 20, 10, 0.2 , 1),
    UNFAIR ("Minecore!"                        , "This skill level isn't even remotely fair", 25, 15, 0.25, 1),
    CUSTOM ("Mine Your own Custom Mine Field"  , "Set up the difficulty yourself"           , 0 , 0 , 0   , 0);
    //#endregion

    //#region Enum fields
    private final String name;
    private final String description;
    private final int width;
    private final int height;
    private final double minePercentage;
    private final int playerLives;
    //#endregion

    //#region Constructors
    private Difficulties(String name, String description, int width, int height, double minePercentage, int playerLives) {
        this.name = name;
        this.description = description;
        this.width = width;
        this.height = height;
        this.minePercentage = minePercentage;
        this.playerLives = playerLives;
    }
    //#endregion

    //#region Getters
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return description;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public double getMinePercentage() {
        return this.minePercentage;
    }
    public int getPlayerLives() {
        return this.playerLives;
    }
    //#endregion
}
