import difficulty.Difficulties;
import gameplay.MineField;
import gameplay.Player;
import input.GameCommands;
import input.GameInstructionData;
import input.InputHandler;
import tiles.TileInteractionResults;

public class MineSweeper {
    public static void main(String[] args) {
        //#region Initial setup
        Player player;
        MineField mineField;
        int width = 0;
        int height = 0;
        double minePercentage = 0;
        int playerLives = 0;
        boolean shouldAskForDifficulty = true;
        //#endregion

        //#region Game loop (with/without difficulty selection and with game reset)
        GameReset: while (true) {
            //#region Difficulty selection
            if (shouldAskForDifficulty) {
                // Print difficulties
                Difficulties[] difficulties = Difficulties.values();
                System.out.println("Select the difficulty:");
                System.out.println("Option  | Name                                    | Description                                     | Dimensions        | Mine percentage   | Player lives");
                System.out.println("--------+-----------------------------------------+-------------------------------------------------+-------------------+-------------------+-------------");
                for (int i = 0; i < difficulties.length; i++)
                    System.out.println((i + 1) + "\t| " + difficulties[i]);

                // Ask the option
                int difficultyIndex = InputHandler.promptNumber(1, difficulties.length + 1) - 1;
                if (difficultyIndex != 0) {
                    // If not a custom difficutly, assign predefined values
                    Difficulties selectedDifficulty = difficulties[difficultyIndex];
                    width = selectedDifficulty.getWidth();
                    height = selectedDifficulty.getHeight();
                    minePercentage = selectedDifficulty.getMinePercentage();
                    playerLives = selectedDifficulty.getPlayerLives();
                }
                else {
                    System.out.println("=== WIDTH ===");
                    width = InputHandler.promptNumber(3, 51);
                    System.out.println("=== HEIGHT ===");
                    height = InputHandler.promptNumber(3, 51);
                    System.out.println("=== MINE PERCENTAGE ===");
                    minePercentage = InputHandler.promptNumber(5, 31) / 100.0;
                    System.out.println("=== PLAYER LIVES ===");
                    playerLives = InputHandler.promptNumber(1, 11);
                }
            }
            //#endregion

            //#region Game initialization
            player = new Player(playerLives);
            mineField = new MineField(width, height, minePercentage);
            shouldAskForDifficulty = false;
            //#endregion

            //#region Gameplay loop
            boolean gameLost = false;
            boolean gameWon = false;
            while (!(gameLost || gameWon)) {
                System.out.println();
                System.out.println("============================");
                System.out.println("Lives Left   : " + player.getLives());
                System.out.println("Total Mines  : " + mineField.getMines());
                System.out.println("Flags Placed : " + mineField.getFlags());
                System.out.println();
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
                        // Get the confirmation
                        System.out.println("You want to retry the game. (Play the game with the same settings)");
                        boolean result = InputHandler.promptBoolean();
                        
                        // Retry if the confirmation was recieved
                        if (result)
                            continue GameReset;
                        
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
                        // Get the confirmation
                        System.out.println("You want to restart the game. (Set the different difficulty and play again)");
                        boolean result = InputHandler.promptBoolean();
                        
                        // Restart if the confirmation was recieved
                        if (result) {
                            shouldAskForDifficulty = true;
                            continue GameReset;
                        }
                        
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
                            else if (interactionResult == TileInteractionResults.EXPLOSION) {
                                System.out.println("This tile contained a mine.");
                                gameLost = player.openedMine();
                            }
                            else if (interactionResult == TileInteractionResults.SUCCESS) {
                                System.out.println("Sucessfully opened this tile.");
                                gameWon = mineField.isGameWon();
                            }
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
            //#endregion
            
            //#region End game screen
            System.out.println("============================");
            System.out.println("GAME FINISHED: ");
            System.out.println(gameWon ? "You opened all empty tiles..." : "You opened too many mined tiles...");
            System.out.println(gameWon ? "You won!" : "You lost!");
            System.out.println();
            System.out.println("Final minefield state: ");
            System.out.println(mineField);
            System.out.println();
            InputHandler.promptEnter();
            //#endregion
        }
        //#endregion
    }
}
