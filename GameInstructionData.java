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

    //#region Attribute manipulation
    /**
     * Try to figure out which argument is X, which attribute is Y coordinate, transform it into numbers from 0 to 1 and save it
     */
    public void transformArgumentsToCoords() {
        //#region Check if there is a correct number of arguments
        if (this.arguments.length != 2)
            return;
        //#endregion
        
        //#region Initialize and parse values
        // Both arguments as numbers
        int numberArgument0 = InputHandler.parseFromNumber(this.arguments[0]);
        int numberArgument1 = InputHandler.parseFromNumber(this.arguments[1]);

        // Both arguments as alphabet numbers
        int alphabetArgument0 = InputHandler.parseFromAlphabetNumber(this.arguments[0]);
        int alphabetArgument1 = InputHandler.parseFromAlphabetNumber(this.arguments[1]);
        ////#endregion

        //#region Check if there is a valid configuration (one of the coordinates should be represented as a number, the other as alphabet)
        // Verify the validity of 2 configuraions (coordinates "3 a" and "a 3" should be both valid)
        boolean validAlphabet0Number1 = alphabetArgument0 != -1 && numberArgument1 != -1;
        boolean validNumber0Alphabet1 = numberArgument0 != -1 && alphabetArgument1 != -1;
        if (!(validAlphabet0Number1 || validNumber0Alphabet1))
            return;
        //#endregion

        //#region Determine which coordinate is X and which is Y and set the arguments accordingly
        if (validAlphabet0Number1) {
            arguments[0] = Integer.toString(alphabetArgument0);
            arguments[1] = Integer.toString(numberArgument1 - 1);
        }
        else {
            arguments[0] = Integer.toString(alphabetArgument1);
            arguments[1] = Integer.toString(numberArgument0 - 1);
        }
    }
    //#endregion
}