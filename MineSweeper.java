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

        // Game loop
        while (true) {
            System.out.println("============");
            System.out.println(mineField);

            GameInstructionData instruction = InputHandler.promptGameInstruction();
            
            switch (instruction.getType()) {
                case GAME_HELP: {
                    System.out.println("===== HELP PAGE =====");
                    System.out.println("=== How to play ===");
                    System.out.println("\tWhat you were seeing is the grid of the mine field.");
                    System.out.println("\tThe point of the game is to open all the tiles that do not coontain mines, while avoiding mine tiles.");
                    System.out.println("\t* Opening a mine tile will decrement the number of your lives. This can result into game over!");
                    System.out.println("\t* Opeining an empty tile reveals the number of mines in adjacent tiles.");
                    System.out.println("=== Controls ===");
                    System.out.println("\tHow to call?        | Arguments?        | What does it do?");
                    System.out.println("\t--------------------+-------------------+-----------------");
                    for (GameCommands command : GameCommands.values())
                        System.out.println("\t" + command.getDocumentation());

                    System.out.println();
                    InputHandler.promptEnter();
                    break;
                }
                case GAME_INSTANT_RETRY: {
                    break;
                }
                case GAME_QUIT: {
                    // Get the confirmation
                    System.out.println("You want to quit the game.");
                    boolean result = InputHandler.promptBoolean();
                    
                    // Quit if the confirmation was recieved
                    if (result)
                        return;
                    
                    break;
                }
                case GAME_RESTART: {
                    break;
                }
                case TILE_MARK_CLEAR: {
                    // Get coords of the input
                    int x = Integer.parseInt(instruction.getArguments()[0]);
                    int y = Integer.parseInt(instruction.getArguments()[1]);
                    // Execute and get the result
                    try {
                        TileInteractionResults interactionResult = mineField.markClearAt(x, y);
                        //#region Print the confirmation message
                        if (interactionResult == TileInteractionResults.INVALID_TILE) {
                            System.out.println("You cannot clear the marks of this tile, it is not interactable.");
                            System.out.println("This turn is skipped...");
                        }
                        else if (interactionResult == TileInteractionResults.SUCCESS)
                            System.out.println("Sucessfully cleared the marks from this tile.");
                        //#endregion
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("This turn is skipped...");
                    }
                    break;
                }
                case TILE_MARK_FLAG: {
                    // Get coords of the input
                    int x = Integer.parseInt(instruction.getArguments()[0]);
                    int y = Integer.parseInt(instruction.getArguments()[1]);
                    // Execute and get the result
                    try {
                        TileInteractionResults interactionResult = mineField.markFlagAt(x, y);
                        //#region Print the confirmation message
                        if (interactionResult == TileInteractionResults.INVALID_TILE) {
                            System.out.println("You cannot mark this tile with a flag, it is not interactable.");
                            System.out.println("This turn is skipped...");
                        }
                        else if (interactionResult == TileInteractionResults.SUCCESS)
                            System.out.println("Sucessfully marked this tile with a flag.");
                        //#endregion
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("This turn is skipped...");
                    }
                    break;
                }
                case TILE_MARK_QUESTION: {
                    // Get coords of the input
                    int x = Integer.parseInt(instruction.getArguments()[0]);
                    int y = Integer.parseInt(instruction.getArguments()[1]);
                    // Execute and get the result
                    try {
                        TileInteractionResults interactionResult = mineField.markQuestionAt(x, y);
                        //#region Print the confirmation message
                        if (interactionResult == TileInteractionResults.INVALID_TILE) {
                            System.out.println("You cannot mark this tile with a question, it is not interactable.");
                            System.out.println("This turn is skipped...");
                        }
                        else if (interactionResult == TileInteractionResults.SUCCESS)
                            System.out.println("Sucessfully marked this tile with a question.");
                        //#endregion
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("This turn is skipped...");
                    }
                    break;
                }
                case TILE_OPEN: {
                    // Get coords of the input
                    int x = Integer.parseInt(instruction.getArguments()[0]);
                    int y = Integer.parseInt(instruction.getArguments()[1]);
                    // Execute and get the result
                    try {
                        TileInteractionResults interactionResult = mineField.openAt(x, y);
                        //#region Print the confirmation message
                        if (interactionResult == TileInteractionResults.INVALID_TILE) {
                            System.out.println("You cannot open this tile, it is not interactable.");
                            System.out.println("This turn is skipped...");
                        }
                        else if (interactionResult == TileInteractionResults.SUCCESS)
                            System.out.println("Sucessfully opened this tile.");
                        //#endregion
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("This turn is skipped...");
                    }
                    break;
                }
            }
        }
    }
}