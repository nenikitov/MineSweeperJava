import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    /**
     * Initialize Input Handler
     */
    InputHandler() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompt user the number
     * @param min Mininum number that the user can enter (inclusive)
     * @param max Maximum number that the user can enter (exclusive)
     * @return A valid number that the user enters
     */
    public int promptNumber(int min, int max) {
        // Initial message
        System.out.println("Please enter a number. It should be from " + min + " to " + (max - 1) + " :");

        // Loop until the function returns
        while (true) {
            // Check if entered value is a number
            if (!scanner.hasNextInt()) {
                // Write an error message and go to next iteration (reask)
                System.out.println("\"" + scanner.next() + "\" is invalid. Entered value is not a number. Please reenter :");
                continue;
            }

            int number = scanner.nextInt();
            // Check if the entered number is in bounds
            if (number < min || number >= max) {
                // Write an error message and go to next iteration (reask)
                System.out.println("\"" + number + "\" is invalid. Entered number is outside the range from " + min + " to " + (max - 1) + ". Please reenter :");
                continue;
            }

            // If the execution goes here, the number is valid
            // Return it
            return number;
        }
    }

    /**
     * Prompt user the game command
     * @return The game instruction data {@link GameInstructionData}. WARNING: provides little data validation of arguments 
     */
    public GameInstructionData promptPlayerAction() {
        // Initial message
        System.out.println("Please enter the command :");
        
        // Loop until function returns
        while (true) {
            String[] command = scanner.nextLine().split(" ");
            
            // The empty input from the console always has at least "new line" character, so I can safely access the 0th index of the array
            // Check if line is empty (.trim() removes whitespaces)
            if (command[0].trim().length() == 0) {
                // Write an error message and go to next iteration (reask)
                System.out.println("You have entered an empty line. Please reenter :");
                continue;
            }

            // Check if command exists in the list of commands
            if (!isGameCommand(command[0])) {
                // Write an error message and go to next iteration (reask)
                System.out.println("\"" + command[0] + "\" is unknown command. Write \"help\" to get the documentation how to play the game. Please reenter :" );
                continue;
            }

            GameCommands commandAction = findGameCommand(command[0]);
            // Check if the number of attribute is right
            if (command.length - 1 != commandAction.getNumberOfArguments()) {
                // Write an error message and go to next iteration (reask)
                System.out.println("The command \"" + command[0] + "\" needs " + commandAction.getNumberOfArguments() + " attributes. You entered " + (command.length - 1) + ". Please reenter :");
                continue;
            }

            // If the execution goes here, the input is valid
            // Generate an array of arguments (the array of the command without the first part)
            String[] arguments = new String[command.length - 1];
            for (int i = 0; i < arguments.length; i++)
                arguments[i] = command[i+1];
            // Return everything
            return new GameInstructionData(commandAction, arguments);
        }
    }
    private boolean isGameCommand(String input) {
        for (GameCommands action : GameCommands.values()) {
            for (String alias : action.getAliases()) {
                if (alias.equals(input))
                    return true;
            }
        }
        return false;
    }
    private GameCommands findGameCommand(String input) {
        for (GameCommands action : GameCommands.values()) {
            for (String alias : action.getAliases()) {
                if (alias.equals(input))
                    return action;
            }
        }
        return GameCommands.GAME_HELP;
    }
}