public class MineSweeper {
	/* TODO
	 * Separate the input handling logic in separate class
	 * Player actions should have more general arguments rather than tileX and tileY
	 * Execute action method for the mineField
	 * Enum attributes https://www.baeldung.com/java-enum-values for player actions
	 * Switch expressions with -> https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
	*/
	
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        int option = inputHandler.promptNumber(1, 5);
        System.out.println(option);
    }
}