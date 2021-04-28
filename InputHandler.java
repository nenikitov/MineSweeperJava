import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    InputHandler() {
        scanner = new Scanner(System.in);
    }

    public int promptNumber(int min, int max) {
        System.out.println("Please enter a number. It should be from " + min + " to " + (max - 1) + ".");

        while (true) {
            // Check if entered value is a number
            if (!scanner.hasNextInt()) {
                System.out.println("\"" + scanner.next() + "\" is invalid. Entered value is not a number. Please reenter: ");
                continue;
            }

            int number = scanner.nextInt();
            // Check if the entered number is in bounds
            if (number < min || number >= max) {
                System.out.println("\"" + number + "\" is invalid. Entered number is outside the range from " + min + " to " + (max - 1) + ". Please reenter: ");
                continue;
            }

            // If the execution goes here, the number is valid
            return number;
        }
    }

    public PlayerAction promptPlayerAction() {
        return new PlayerAction(PlayerActions.GAME_HELP, new int[0]);
    }
}