package difficulty;

public enum Difficulties {
    //#region Enum entries
    CUSTOM ("Mine Your own Custom Mine Field"  , "Set up the difficulty yourself"           , 0 , 0 , 0   , 0),
    TRIVIAL("Piece of Cake"                    , "Easy mode for beginners"                  , 8 , 6 , 0.1 , 2),
    EASY   ("Mine Me Plenty"                   , "A bit harder, but still easy"             , 10, 8 , 0.12, 2),
    NORMAL ("Thou Art a Defuse Meister"        , "The right mode for intermediate players"  , 15, 8 , 0.15, 1),
    HARD   ("They Call Me \"The Mine Sweeper\"", "About as difficult as you want to go"     , 20, 10, 0.2 , 1),
    UNFAIR ("Minecore!"                        , "This skill level isn't even remotely fair", 30, 15, 0.25, 1);
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

    //#region Other
    public String toString() {
        // Start with empty
        String output = "";

        // Add name and make it aligned to table grid
        output += this.name;
        while (output.length() < 40)
            output += " ";
        output += "| ";
        
        // Add description
        output += this.description;
        while (output.length() < 90)
            output += " ";
        output += "| ";

        // Add mine field dimenstions
		output += (this.width != 0 && this.height != 0) ? this.width + "x" + this.height : "N.A.";
        while (output.length() < 110)
            output += " ";
        output += "| ";
        
        // Add mine percentage
		output += (this.minePercentage != 0) ? (int)(this.minePercentage * 100) + "%" : "N.A.";
        while (output.length() < 130)
            output += " ";
        output += "| ";
        
        // Add player lives
		output += (this.playerLives != 0) ? this.playerLives : "N.A.";

        return output;
    }
    //#endregion
}
