import java.util.Scanner;

public class Player {
    private Scanner scanner;
    private int lives;

    public Player(int lives) {
        this.lives = lives;
        this.scanner = new Scanner(System.in);
    }

    public int promptNumber(int bound) {
        System.out.println("Please enter a number. It should be from 1 to " + (bound - 1) + ".");

        while (true) {
            // Check if entered value is a number
            if (!scanner.hasNextInt()) {
                System.out.println(scanner.next() + " is invalid. Entered value is not a number. Please reenter:");
                continue;
            }

            int number = scanner.nextInt();
            // Check if entered value is in bounds
            if (number <= 0 || number >= bound) {
                System.out.println(number + " is invalid. Entered balue is outside the range from 1 to " + (bound - 1) + ". Please reenter:");
                continue;
            }

            // If it goes here, the value is valid.
            return number;
        }
    }
}