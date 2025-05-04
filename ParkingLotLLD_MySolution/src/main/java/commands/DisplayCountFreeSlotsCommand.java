package commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DisplayCountFreeSlotsCommand implements Command {
    private CommandHandler commandHandler;

    @Override
    public void execute(String[] commandLine) {
        commandHandler.displayCountFreeSlots(commandLine);
    }
}
