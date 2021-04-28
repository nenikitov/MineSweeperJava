public class GameInstructionData {
    //#region Fields
    private GameCommands type;
    private String[] arguments;
    //#endregion

    //#region Constructors
    /**
     * Initialize Player action
     * @param type The type of the player action {@link GameCommands}
     * @param arguments The arguments that are passed with the action
     */
    public GameInstructionData(GameCommands type, String[] arguments) {
        this.type = type;
        this.arguments = arguments;
    }
    //#endregion

    //#region Getters
    /**
     * Get the type of the current action
     * @return The type {@link GameCommands}
     */
    public GameCommands getType() {
        return this.type;
    }
    /**
     * Get the arguments that are passed with the action
     * @return The arguments
     */
    public String[] getArguments() {
        return this.arguments;
    }
    //#endregion
}