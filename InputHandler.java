import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    InputHandler() {
        scanner = new Scanner(System.in);
    }

    public int promptNumber(int min, int max) {
        System.out.println("Please enter a number. It should be from " + min + " to " + (max - 1) + " :");

        while (true) {
            // Check if entered value is a number
            if (!scanner.hasNextInt()) {
                System.out.println("\"" + scanner.next() + "\" is invalid. Entered value is not a number. Please reenter :");
                continue;
            }

            int number = scanner.nextInt();
            // Check if the entered number is in bounds
            if (number < min || number >= max) {
                System.out.println("\"" + number + "\" is invalid. Entered number is outside the range from " + min + " to " + (max - 1) + ". Please reenter :");
                continue;
            }

            // If the execution goes here, the number is valid
            return number;
        }
    }

    public PlayerAction promptPlayerAction() {
        System.out.println("Please enter the command :");
        
        while (true) {
            String[] command = scanner.nextLine().split(" ");
            
            // Check if line is empty (.trim() removes whitespaces)
            if (command[0].trim().length() == 0) {
                System.out.println("You have entered an empty line. Please reenter :");
                continue;
            }

            // Check if command exists in the list of commands
            if (!isGameCommand(command[0])) {
                System.out.println("\"" + command[0] + "\" is unknown command. Write \"help\" to get the documentation how to play the game. Please reenter :" );
                continue;
            }

            GameCommands commandAction = findGameCommand(command[0]);
            // Check if the number of attribute is right
            if (command.length - 1 != commandAction.getNumberOfArguments()) {
                System.out.println("The command \"" + command[0] + "\" needs " + commandAction.getNumberOfArguments() + " attributes. You entered " + (command.length - 1) + ". Please reenter :");
                continue;
            }

            // If the execution goes here, the input is valid
            String[] arguments = new String[command.length - 1];
            for (int i = 0; i < arguments.length; i++)
                arguments[i] = command[i+1];
            return new PlayerAction(commandAction, arguments);
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