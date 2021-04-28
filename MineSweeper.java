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

		System.out.println(InputHandler.parseFromAlphabetNumber("ba"));
		/*
		while (true) {
			System.out.println(mineField);
			GameInstructionData currentInstruction = inputHandler.promptGameInstruction();


		}
		*/
    }
}