public class PlayerAction {
    private PlayerActions type;
    private int[] arguments;

    /**
     * Initialize Player action
     * @param type The type of the player action {@link PlayerActions}
     * @param arguments The arguments that are passed with the action (will throw an IllegalArgumentException if the number of arguments is invalid)
     */
    public PlayerAction(PlayerActions type, int[] arguments) {
        if (arguments.length != type.getAttributeNumber())
            throw new IllegalArgumentException("Invalid number of arguments for the current Player Action type");
        else {
            this.type = type;
            this.arguments = arguments;
        }
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
    public int[] getArguments() {
        return this.arguments;
    }
}