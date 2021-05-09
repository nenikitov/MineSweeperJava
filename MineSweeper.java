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
        // Objects
        Player player;
        MineField mineField;
        // Difficulty
        boolean shouldAskForDifficulty = true;
        int width = 0;
        int height = 0;
        double minePercentage = 0;
        int playerLives = 0;
        // Gameplay
        boolean gameLost = false;
        boolean gameWon = false;
        //#endregion

        //#region Game loop (with/without difficulty selection and with game reset)
        GameReset: while (true) {
            //#region Difficulty selection
            if (shouldAskForDifficulty) {
                // Print difficulties
                Difficulties[] difficulties = Difficulties.values();
                printTheDifficulties(difficulties);

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
                    System.out.println("Please set up the custom difficulty:");
                    System.out.println("=== Enter the width of the mine field: ===");
                    width = InputHandler.promptNumber(5, 51);
                    System.out.println("=== Enter the height of the mine field: ===");
                    height = InputHandler.promptNumber(5, 51);
                    System.out.println("=== Enter the percentage of the mine tiles: ===");
                    minePercentage = InputHandler.promptNumber(5, 31) / 100.0;
                    System.out.println("=== Enter how many lives the player has: ===");
                    playerLives = InputHandler.promptNumber(1, 11);
                }
            }
            //#endregion

            //#region Game initialization
            player = new Player(playerLives);
            mineField = new MineField(width, height, minePercentage);
            shouldAskForDifficulty = false;
            gameLost = false;
            gameWon = false;
            //#endregion

            //#region Gameplay loop
            while (!(gameLost || gameWon)) {
                // Initial print
                printGameInfo(player, mineField);

                GameInstructionData instruction = InputHandler.promptGameInstruction();
                switch (instruction.getType()) {
                    case GAME_HELP: {
                        //#region Print game documentation
                        printHelp();
                        InputHandler.promptEnter();
                        //#endregion
                        break;
                    }
                    case GAME_INSTANT_RETRY: {
                        //#region Ask if the user is sure and restart the game with the same settings
                        // Get the confirmation
                        System.out.println("You want to retry the game. (Play the game with the same settings)");
                        boolean result = InputHandler.promptBoolean();

                        // Retry if the confirmation was recieved
                        if (result)
                            continue GameReset;
                        //#endregion
                        break;
                    }
                    case GAME_QUIT: {
                        //#region Ask if the user is sure and quit the game
                        // Get the confirmation
                        System.out.println("You want to quit the game.");
                        boolean result = InputHandler.promptBoolean();

                        // Quit if the confirmation was recieved
                        if (result)
                            return;
                        //#endregion
                        break;
                    }
                    case GAME_RESTART: {
                        //#region Ask if the user is sure and restart the game with different settings
                        // Get the confirmation
                        System.out.println("You want to restart the game. (Set the different difficulty and play again)");
                        boolean result = InputHandler.promptBoolean();

                        // Restart if the confirmation was recieved
                        if (result) {
                            shouldAskForDifficulty = true;
                            continue GameReset;
                        }
                        //#endregion
                        break;
                    }
                    case TILE_MARK_CLEAR: {
                        //#region Clear the marks of the tile
                        // Get coords of the input
                        int x = Integer.parseInt(instruction.getArguments()[0]);
                        int y = Integer.parseInt(instruction.getArguments()[1]);
                        // Execute and get the result
                        try {
                            markClearTile(mineField, x, y);
                        }
                        catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                            System.out.println("This turn is skipped...");
                        }
                        //#endregion
                        break;
                    }
                    case TILE_MARK_FLAG: {
                        //#region Mark the tile with the flag
                        // Get coords of the input
                        int x = Integer.parseInt(instruction.getArguments()[0]);
                        int y = Integer.parseInt(instruction.getArguments()[1]);
                        // Execute and get the result
                        try {
                            markFlagTile(mineField, x, y);
                        }
                        catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                            System.out.println("This turn is skipped...");
                        }
                        //#endregion
                        break;
                    }
                    case TILE_MARK_QUESTION: {
                        //#region Mark the tile with the question
                        // Get coords of the input
                        int x = Integer.parseInt(instruction.getArguments()[0]);
                        int y = Integer.parseInt(instruction.getArguments()[1]);
                        // Execute and get the result
                        try {
                            markQuestionTile(mineField, x, y);
                        }
                        catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                            System.out.println("This turn is skipped...");
                        }
                        //#endregion
                        break;
                    }
                    case TILE_OPEN: {
                        //#region Open the tile
                        // Get coords of the input
                        int x = Integer.parseInt(instruction.getArguments()[0]);
                        int y = Integer.parseInt(instruction.getArguments()[1]);
                        // Execute and get the result
                        try {
                            TileInteractionResults openResult = openTile(mineField, player, x, y);
                            if (openResult == TileInteractionResults.EXPLOSION) {
                                gameLost = player.openedMine();
                            }
                            else if (openResult == TileInteractionResults.SUCCESS) {
                                gameWon = mineField.isGameWon();
                            }
                        }
                        catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                            System.out.println("This turn is skipped...");
                        }
                        //#endregion
                        break;
                    }
                }
            }
            //#endregion

            //#region End game screen
            printEndGameScreen(mineField);
            int choice = InputHandler.promptNumber(1, 4);
            if (choice == 2)
                shouldAskForDifficulty = true;
            if (choice == 3)
                return;
            //#endregion
        }
        //#endregion
    }

    //#region Helper methods
    private static void printTheDifficulties(Difficulties[] difficulties) {
        System.out.println("Select the difficulty:");
        System.out.println("Option  | Name                                    | Description                                     | Dimensions        | Mine percentage   | Player lives");
        System.out.println("--------+-----------------------------------------+-------------------------------------------------+-------------------+-------------------+-------------");
        for (int i = 0; i < difficulties.length; i++)
            System.out.println((i + 1) + "\t| " + difficulties[i]);
    }
    private static void printGameInfo(Player player, MineField mineField) {
        System.out.println();
        System.out.println("============================");
        System.out.println("Lives Left   : " + player.getLives());
        System.out.println("Total Mines  : " + mineField.getMines());
        System.out.println("Flags Placed : " + mineField.getFlags());
        System.out.println();
        System.out.println(mineField);
    }
    private static void printHelp() {
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
    }
    private static void printEndGameScreen(MineField mineField) {
        boolean gameWon = mineField.isGameWon();
        System.out.println("============================");
        System.out.println("GAME FINISHED: ");
        System.out.println(gameWon ? "You opened all empty tiles..." : "You opened too many mined tiles...");
        System.out.println(gameWon ? "You won!" : "You lost!");
        System.out.println();
        System.out.println("Final minefield state: ");
        System.out.println(mineField);
        System.out.println();
        System.out.println("What you would like to do?");
        System.out.println("1\tReplay the game with the same settings");
        System.out.println("2\tChange the difficulty and replay the game");
        System.out.println("3\tQuit the game");
    }
    private static TileInteractionResults openTile(MineField mineField, Player player, int x, int y) {
        TileInteractionResults interactionResult = mineField.openAt(x, y);
        //#region Print the confirmation message
        if (interactionResult == TileInteractionResults.INVALID_TILE) {
            System.out.println("You cannot open this tile, it is not interactable.");
            System.out.println("This turn is skipped...");
        }
        else if (interactionResult == TileInteractionResults.EXPLOSION)
            System.out.println("This tile contained a mine.");
        else
            System.out.println("Successfully opened this tile.");
        return interactionResult;
        //#endregion
    }
    private static void markFlagTile(MineField mineField, int x, int y) {
        TileInteractionResults interactionResult = mineField.markFlagAt(x, y);
        //#region Print the confirmation message
        if (interactionResult == TileInteractionResults.INVALID_TILE) {
            System.out.println("You cannot mark this tile with a flag, it is not interactable.");
            System.out.println("This turn is skipped...");
        }
        else if (interactionResult == TileInteractionResults.SUCCESS)
            System.out.println("Successfully marked this tile with a flag.");
        //#endregion
    }
    private static void markQuestionTile(MineField mineField, int x, int y) {
        TileInteractionResults interactionResult = mineField.markQuestionAt(x, y);
        //#region Print the confirmation message
        if (interactionResult == TileInteractionResults.INVALID_TILE) {
            System.out.println("You cannot mark this tile with a question, it is not interactable.");
            System.out.println("This turn is skipped...");
        }
        else if (interactionResult == TileInteractionResults.SUCCESS)
            System.out.println("Successfully marked this tile with a question.");
        //#endregion
    }
    private static void markClearTile(MineField mineField, int x, int y) {
        TileInteractionResults interactionResult = mineField.markClearAt(x, y);
        //#region Print the confirmation message
        if (interactionResult == TileInteractionResults.INVALID_TILE) {
            System.out.println("You cannot clear the marks of this tile, it is not interactable.");
            System.out.println("This turn is skipped...");
        }
        else if (interactionResult == TileInteractionResults.SUCCESS)
            System.out.println("Successfully cleared the marks from this tile.");
        //#endregion
    }
    //#endregion
}
