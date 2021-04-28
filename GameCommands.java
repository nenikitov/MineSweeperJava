public enum GameCommands {
    //#region Enum entries
    TILE_OPEN          (new String[] { "open",     "o"  }, 2),
    TILE_MARK_FLAG     (new String[] { "flag",     "f"  }, 2),
    TILE_MARK_QUESTION (new String[] { "question", "q"  }, 2),
    TILE_MARK_CLEAR    (new String[] { "clear",    "c"  }, 2),
    GAME_HELP          (new String[] { "help",     "h"  }, 0),
    GAME_INSTANT_RETRY (new String[] { "retry",    "r"  }, 0),
    GAME_RESTART       (new String[] { "restart",  "r!" }, 0),
    GAME_QUIT          (new String[] { "quit",     "q!" }, 0);
    //#endregion

    //#region Enum fields
    private final String[] aliases;
    private final int arguments;
    //#endregion

    //#region Constructor
    private GameCommands(String[] aliases, int arguments) {
        this.aliases = aliases;
        this.arguments = arguments;
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
    //#endregion
}