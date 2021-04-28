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
            if (!isPlayerActions(command[0])) {
                System.out.println("\"" + command[0] + "\" is unknown command. Write \"help\" to get the documentation how to play the game. Please reenter :" );
                continue;
            }

            PlayerActions commandAction = findPlayerActions(command[0]);
            // Check if the number of attribute is right
            if (command.length - 1 != commandAction.getAttributeNumber()) {
                System.out.println("The command \"" + command[0] + "\" needs " + commandAction.getAttributeNumber() + " attributes. You entered " + (command.length - 1) + ". Please reenter :");
                continue;
            }

            // If the execution goes here, the input is valid
            // TODO pass the attributes
            return new PlayerAction(commandAction, new String[0]);
        }
    }
    private boolean isPlayerActions(String input) {
        for (PlayerActions action : PlayerActions.values()) {
            for (String alias : action.getAliases()) {
                if (alias.equals(input))
                    return true;
            }
        }
        return false;
    }
    private PlayerActions findPlayerActions(String input) {
        for (PlayerActions action : PlayerActions.values()) {
            for (String alias : action.getAliases()) {
                if (alias.equals(input))
                    return action;
            }
        }
        return PlayerActions.GAME_HELP;
    }
}