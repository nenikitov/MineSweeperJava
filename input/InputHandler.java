package input;

import java.util.Scanner;

/**
 * The class that is used to prompt the user the commands
 */
public class InputHandler {
    //#region Fields
    private static final Scanner scanner = new Scanner(System.in);
    private static final String reenterMessage = "Please reenter :";
    //#endregion

    //#region Getting the input from the user
    /**
     * Prompt user to press enter key
     */
    public static void promptEnter() {
        // Initial message
        System.out.println("Please press ENTER to continue:");
        scanner.nextLine();
    }
    /**
     * Prompt user a boolean value
     * @return A valid boolean that user entered
     */
    public static boolean promptBoolean() {
        // Initial message
        System.out.println("Are you sure?");

        while (true) {
            String input = scanner.nextLine().toLowerCase();

            //#region User entered true
            if (input.equals("yes") || input.equals("y"))
                return true;
            //#endregion

            //#region User entered false
            if (input.equals("no") || input.equals("n"))
                return false;
            //#endregion

            // If the execution goes here, the input was invalid
            System.out.println("You wrote an invalid answer.");
            System.out.println(reenterMessage);
        }
    }
    /**
     * Prompt user the number
     * @param min Mininum number that the user can enter (inclusive)
     * @param max Maximum number that the user can enter (exclusive)
     * @return A valid number that the user entered
     */
    public static int promptNumber(int min, int max) {
        // Initial message
        System.out.println("Please enter a number. It should be from " + min + " to " + (max - 1) + " :");

        // Loop until the function returns
        while (true) {
            //#region Check if entered value is a number
            if (!scanner.hasNextInt()) {
                // Write an error message and go to next iteration (reask)
                System.out.println("\"" + scanner.next() + "\" is invalid. Entered value is not a number.");
                System.out.println(reenterMessage);
                continue;
            }
            //#endregion

            //#region Check if the entered number is in bounds
            int number = scanner.nextInt();
            if (number < min || number >= max) {
                // Write an error message and go to next iteration (reask)
                System.out.println("\"" + number + "\" is invalid. Entered number is outside the range from " + min + " to " + (max - 1) + ".");
                System.out.println(reenterMessage);
                continue;
            }
            //#endregion

            // If the execution goes here, the number is valid
            // Return it
            return number;
        }
    }
    /**
     * Prompt user the game command
     * @return A valid game instruction data {@link GameInstructionData} that the user entered. WARNING: provides little data validation of arguments 
     */
    public static GameInstructionData promptGameInstruction() {
        // Initial message
        System.out.println("Please enter the command :");
        
        // Loop until function returns
        while (true) {
            String[] command = scanner.nextLine().split(" ");
            
            // The empty input from the console always has at least "new line" character, so I can safely access the 0th index of the array
            //#region Check if line is empty (.trim() removes whitespaces)
            if (command[0].trim().length() == 0) {
                // Write an error message and go to next iteration (reask)
                System.out.println("You have entered an empty line.");
                System.out.println(reenterMessage);
                continue;
            }
            //#endregion

            //#region Make everything lowercase (for simplicity)
            for (int i = 0; i < command.length; i++)
                command[i] = command[i].toLowerCase();
            //#endregion

            //#region Check if the command is valid
            try {
                GameCommands currentCommand = GameInstructionData.findGameCommand(command[0]);

                //#region Generate an array of arguments (the array of the command without the first part)
                String[] arguments = new String[command.length - 1];
                for (int i = 0; i < arguments.length; i++)
                    arguments[i] = command[i + 1];
                //#endregion

                try {
                    // Generate the command and return it
                    return new GameInstructionData(currentCommand, arguments);
                }
                catch (IllegalArgumentException e) {
                    // There is something wrong with the arguments
                    // Write an error message and go to next iteration
                    System.out.println(e.getMessage());
                    System.out.println(reenterMessage);
                    continue;
                }
            }
            catch (IllegalArgumentException e) {
                // The command does not exist
                // Write an error message and go to next iteration
                System.out.println(e.getMessage());
                System.out.println(reenterMessage);
                continue;
            }
            //#endregion
        }
    }
    //#endregion

    //#region Info parsing methods
    /**
     * Parse a String to integer (WARNING: doesn't support negative numbers)
     * @param text String representation of the number in alphabetic form (a - 0, z - 25, aa - 26)
     * @return Integer representing the number (-1 if error)
     */
    public static int parseFromAlphabetNumber(String text) {
        // If the string is empty, do not even bother checking
        if (text.length() == 0)
            throw new NumberFormatException("Invalid input for " + text);

        int ouptut = 0;
        int powers = text.length() - 1;
        // For each character
        for (int i = 0; i < text.length(); i++) {
            // If the character is invalid, stop parsing
            if (text.charAt(i) < 'a' || text.charAt(i) > 'z')
                throw new NumberFormatException("Invalid input for " + text);

            int currentNumber = text.charAt(i) - 'a' + 1;
            // Add it to result (paying attention to the power)
            ouptut += Math.pow(26, powers - i) * currentNumber;
        }
        // Return final result (the additional math is done to make the output more readable)
        return ouptut - 1;
    }
    /**
     * Transform integer to String representation of the number (WARNING: doesn't support negative numbers)
     * @param number Integer representing the number
     * @return String representation of the number in alphabetic form (a - 0, z - 25, aa - 26)
     */
    public static String parseToAlphabetNumber(int number) {
        // If the number is invalid - return error (empty)
        if (number < 0)
            throw new NumberFormatException("Invalid input for " + number);

        // If the number is 0, return 0 is alphabet numbering system 
        if (number == 0)
            return "a";

        String output = "";
        number++;
        // For each digit
        while (number > 0) {
            int currentDigit = number % 26 - 1;
            number /= 26;
            // Append it to result
            output = (char)(currentDigit + 'a') + output;
        }
        // Return final result (the additional math is done because I made the output more readable)
        return output;
    }
    //#endregion
}