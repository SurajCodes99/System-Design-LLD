package commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DisplayOccupiedSlotsCommand implements Command {
    private CommandHandler commandHandler;

    @Override
    public void execute(String[] commandLine) {
        commandHandler.displayOccupiedSlots(commandLine);
    }
}
