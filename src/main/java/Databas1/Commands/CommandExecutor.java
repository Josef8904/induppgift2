package Databas1.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Map<Integer, Command> commands = new HashMap<>();

    public void registerCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void executeCommand(int option) {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }
}
