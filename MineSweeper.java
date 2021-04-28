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
            
            int x = 0;
            int y = 0;

            if (instruction.getArguments().length >= 2) {
                x = InputHandler.parseFromNumber(instruction.getArguments()[0]);
                y = InputHandler.parseFromNumber(instruction.getArguments()[1]);
            }

            switch (instruction.getType()) {
                case GAME_HELP:
                    break;
                case GAME_INSTANT_RETRY:
                    break;
                case GAME_QUIT:
                    break;
                case GAME_RESTART:
                    break;
                case TILE_MARK_CLEAR:
                    mineField.markClearAt(x, y);
                    break;
                case TILE_MARK_FLAG:
                    mineField.markFlagAt(x, y);
                    break;
                case TILE_MARK_QUESTION:
                    mineField.markQuestionAt(x, y);
                    break;
                case TILE_OPEN:
                    mineField.openAt(x, y);
                    break;
            }
        }
    }
}