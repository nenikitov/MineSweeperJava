public class MineSweeper {
    /* TODO
     * Separate the input handling logic in separate class
     * Player actions should have more general arguments rather than tileX and tileY
     * Execute action method for the mineField
     * Enum attributes https://www.baeldung.com/java-enum-values for player actions
     * Switch expressions with -> https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
     * Finish input handler prompting actions
     * Fix the naming of PlayerAcions (rename the enum to be smth like GameCommands and attributes to arguments)
    */

    public static void main(String[] args) {
        Player player = new Player(1);
        MineField mineField = new MineField(15, 7, 0.15);
        InputHandler inputHandler = new InputHandler();

        // Game loop
        while (true) {
            System.out.println("============");
            System.out.println(mineField);

            GameInstructionData instruction = inputHandler.promptGameInstruction();
            while (!mineField.isValidInstructionData(instruction)) {
                System.out.println("The coordinates you entered are invalid. Please reener: ");
                instruction = inputHandler.promptGameInstruction();
            }
            
            switch (instruction.getType()) {
                case GAME_HELP: {
                    break;
                }
                case GAME_INSTANT_RETRY: {
                    break;
                }
                case GAME_QUIT: {
                    inputHandler.promptBoolean();
                    break;
                }
                case GAME_RESTART: {
                    break;
                }
                case TILE_MARK_CLEAR: {
                    // Get coords of the input
                    int x = InputHandler.parseFromNumber(instruction.getArguments()[0]);
                    int y = InputHandler.parseFromNumber(instruction.getArguments()[1]);
                    // Execute and get the result
                    TileInteractionResults interactionResult = mineField.markClearAt(x, y);
                    //#region Print the confirmation message
                    if (interactionResult == TileInteractionResults.INVALID_TILE)
                        System.out.println("You cannot clear the marks of this tile, it is not interactable. This turn is skipped...");
                    else if (interactionResult == TileInteractionResults.SUCCESS)
                        System.out.println("Sucessfully cleared all the marks from this tile...");
                    //#endregion
                    break;
                }
                case TILE_MARK_FLAG: {
                    // Get coords of the input
                    int x = InputHandler.parseFromNumber(instruction.getArguments()[0]);
                    int y = InputHandler.parseFromNumber(instruction.getArguments()[1]);
                    // Execute and get the result
                    TileInteractionResults interactionResult = mineField.markFlagAt(x, y);
                    //#region Print the confirmation message
                    if (interactionResult == TileInteractionResults.INVALID_TILE)
                        System.out.println("You cannot mark this tile with a flag, it is not interactable. This turn is skipped...");
                    else if (interactionResult == TileInteractionResults.SUCCESS)
                        System.out.println("Sucessfully marked this tile with a flag...");
                    //#endregion
                    break;
                }
                case TILE_MARK_QUESTION: {
                    // Get coords of the input
                    int x = InputHandler.parseFromNumber(instruction.getArguments()[0]);
                    int y = InputHandler.parseFromNumber(instruction.getArguments()[1]);
                    // Execute and get the result
                    TileInteractionResults interactionResult = mineField.markQuestionAt(x, y);
                    //#region Print the confirmation message
                    if (interactionResult == TileInteractionResults.INVALID_TILE)
                        System.out.println("You cannot mark this tile with a question, it is not interactable. This turn is skipped...");
                    else if (interactionResult == TileInteractionResults.SUCCESS)
                        System.out.println("Sucessfully marked this tile with a question...");
                    //#endregion
                    break;
                }
                case TILE_OPEN: {
                    // Get coords of the input
                    int x = InputHandler.parseFromNumber(instruction.getArguments()[0]);
                    int y = InputHandler.parseFromNumber(instruction.getArguments()[1]);
                    // Execute and get the result
                    TileInteractionResults interactionResult = mineField.openAt(x, y);
                    //#region Print the confirmation message
                    break;
                }
            }
        }
    }
}