public class PlayerAction {
    private PlayerActions type;
    private String[] arguments;

    /**
     * Initialize Player action
     * @param type The type of the player action {@link PlayerActions}
     * @param arguments The arguments that are passed with the action
     */
    public PlayerAction(PlayerActions type, String[] arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    /**
     * Get the type of the current action
     * @return The type {@link PlayerActions}
     */
    public PlayerActions getType() {
        return this.type;
    }

    /**
     * Get the arguments that are passed with the action
     * @return The arguments
     */
    public String[] getArguments() {
        return this.arguments;
    }
}