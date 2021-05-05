package difficulty;

/**
 * The list of all possible difficulties
 */
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
    /**
     * Get the width of the mine field from the difficulty
     * @return The width
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * Get the height of the mine field from the difficulty
     * @return The height
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * Get the mine percentage from the difficulty
     * @return The mine percentage
     */
    public double getMinePercentage() {
        return this.minePercentage;
    }
    /**
     * Get the number of lives that the player has from the difficulty
     * @return The number of player lives
     */
    public int getPlayerLives() {
        return this.playerLives;
    }
    //#endregion

    //#region Other
    /**
     * Transforms the difficulty stats to the table
     */
    public String toString() {
        // Start with empty string
        String output = "";

        // Add name and align it to table grid
        output += this.name;
        output = completeDescription(output, 40);
        
        // Add description and align it
        output += this.description;
        output = completeDescription(output, 90);

        // Add mine field dimenstions and align it
        output += (this.width != 0 && this.height != 0) ? this.width + "x" + this.height : "N.A.";
        output = completeDescription(output, 110);
        
        // Add mine percentage and align it
        output += (this.minePercentage != 0) ? (int)(this.minePercentage * 100) + "%" : "N.A.";
        output = completeDescription(output, 130);
        
        // Add player lives
        output += (this.playerLives != 0) ? this.playerLives : "N.A.";

        return output;
    }
    //#endregion

    //#region Helper methods
    private String completeDescription(String string, int length) {
        // Complete string to target length
        while (string.length() < length)
            string += " ";
        // Add table line at the end
        string += "| ";

        return string;
    }
    //#endregion
}
