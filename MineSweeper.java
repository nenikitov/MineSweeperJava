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
        InputHandler inputHandler = new InputHandler();

        GameInstructionData action = inputHandler.promptPlayerAction();
		System.out.println(action.getType());
		for (int i = 0; i < action.getArguments().length; i++)
			System.out.println(action.getArguments()[i]);
    }
}