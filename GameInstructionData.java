/**
 * The class that stores the data of the gameplay command. It is used to transmit data
 */
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
        if (type.getNumberOfArguments() != arguments.length)
            throw new IllegalArgumentException(
                "The number of arguments is invalid. "
                + "The command \"" + type.getAliases()[0] + "\" should have " + type.getNumberOfArguments() + ", "
                + "but you entered " + arguments.length + ".");

        this.type = type;
        this.arguments = arguments;

        try {
            switch (this.type) {
                case TILE_MARK_CLEAR: case TILE_MARK_FLAG: case TILE_MARK_QUESTION: case TILE_OPEN:
                    this.transformArgumentsToCoords();
                    break;
                default:
                    break;
            }
        }
        catch (IllegalArgumentException e) {
            throw e;
        }
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

    //#region Attribute manipulation
    /**
     * Try to figure out which argument is X, which attribute is Y coordinate, transform it into numbers from 0 to 1 and save it
     */
    private void transformArgumentsToCoords() {
        //#region Initialize and parse values
        //#region Both arguments as numbers
        int numberArgument0;
        int numberArgument1;
        
        try {
            numberArgument0 = Integer.parseInt(this.arguments[0]);
        }
        catch (NumberFormatException e) {
            numberArgument0 = -1;
        }
        try {
            numberArgument1 = Integer.parseInt(this.arguments[1]);
        }
        catch (NumberFormatException e) {
            numberArgument1 = -1;
        }
        //#endregion
        //#region Both arguments as alphabet numbers
        int alphabetArgument0;
        int alphabetArgument1;

        try {
            alphabetArgument0 = InputHandler.parseFromAlphabetNumber(this.arguments[0]);
        }
        catch (NumberFormatException e) {
            alphabetArgument0 = -1;
        }
        try {
            alphabetArgument1 = InputHandler.parseFromAlphabetNumber(this.arguments[1]);
        }
        catch (NumberFormatException e) {
            alphabetArgument1 = -1;
        }
        //#endregion
        //#endregion

        //#region Check if there is a valid configuration (one of the coordinates should be represented as a number, the other as alphabet)
        // Verify the validity of 2 configuraions (coordinates "3 a" and "a 3" should be both valid)
        boolean validConfigAlphabet0Number1 = alphabetArgument0 != -1 && numberArgument1 != -1;
        boolean validConfigNumber0Alphabet1 = numberArgument0 != -1 && alphabetArgument1 != -1;
        if (!(validConfigAlphabet0Number1 || validConfigNumber0Alphabet1))
            throw new IllegalArgumentException("The coordinates are not given in the right format (like \"5 a\" or \"a 5\").");
        //#endregion

        //#region Determine which coordinate is X and which is Y and set the arguments accordingly
        if (validConfigAlphabet0Number1) {
            arguments[0] = Integer.toString(alphabetArgument0);
            arguments[1] = Integer.toString(numberArgument1 - 1);
        }
        else {
            arguments[0] = Integer.toString(alphabetArgument1);
            arguments[1] = Integer.toString(numberArgument0 - 1);
        }
    }
    //#endregion

    //#region Other
    /**
     * Find the command that can be called by an input
     * @param text Input used to call the command
     * @return The command
     */
    public static GameCommands findGameCommand(String text) {
        for (GameCommands action : GameCommands.values()) {
            for (String alias : action.getAliases()) {
                if (alias.equals(text))
                    return action;
            }
        }
        throw new IllegalArgumentException("The \"" + text + "\" is a unknown command.");
    }
    //#endregion
}