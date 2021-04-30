package input;

/**
 * The list of all the commands that the user can use during the gameplay
 */
public enum GameCommands {
    //#region Enum entries
    TILE_OPEN          (new String[] { "open",     "o"  }, 2, "Opens the tile."),
    TILE_MARK_FLAG     (new String[] { "flag",     "f"  }, 2, "Marks the tile with a flag. Used to mark where you are sure there is a mine."),
    TILE_MARK_QUESTION (new String[] { "question", "q"  }, 2, "Marks the tile with a question. Used to mark where you are unsure there is a mine."),
    TILE_MARK_CLEAR    (new String[] { "clear",    "c"  }, 2, "Clears all the marks form a tile"),
    GAME_HELP          (new String[] { "help",     "h"  }, 0, "Prints the help text (as you see here)."),
    GAME_INSTANT_RETRY (new String[] { "retry",    "r"  }, 0, "Resets the game, keeping the same settings."),
    GAME_RESTART       (new String[] { "restart",  "r!" }, 0, "Resets the game and prompts to input new settings."),
    GAME_QUIT          (new String[] { "quit",     "q!" }, 0, "Quits the game.");
    //#endregion

    //#region Enum fields
    private final String[] aliases;
    private final int arguments;
    private final String documentation;
    //#endregion

    //#region Constructors
    private GameCommands(String[] aliases, int arguments, String documentation) {
        this.aliases = aliases;
        this.arguments = arguments;
        this.documentation = documentation;
    }
    //#endregion

    //#region Getters
    /**
     * Get the aliases of the command (strings that can be used to call the game commands)
     * @return Aliases 
     */
    public String[] getAliases() {
        return this.aliases;
    }
    /**
     * Get the number of arguments that the command needs to execute correctly
     * @return Number of arguments
     */
    public int getNumberOfArguments() {
        return this.arguments;
    }
    /**
     * Generate the documentation for the command
     * @return Detatiled documentation
     */
    public String getDocumentation() {
        // Start with empty documentation
        String output = "";

        // Append aliases
        for (int i = 0; i < this.aliases.length; i++) {
            output += this.aliases[i];

            if (i != this.aliases.length - 1)
                output += ", ";
        }

        // Make everything aligned to the table in documentation
        for (int i = output.length(); i < 20; i++)
            output += " ";
        output += "| ";

        // Append number of arguments
        output += this.arguments;

        // Make everything aligned to the table in documentation
        for (int i = output.length(); i < 40; i++)
            output += " ";
        output += "| ";

        // Append documentation
        output += this.documentation;

        // Output finalized documentation
        return output;
    }
    //#endregion
}
