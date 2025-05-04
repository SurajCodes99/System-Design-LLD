package commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DisplayCountFreeCommand implements Command{
    private CommandHandler commandHandler;

    @Override
    public void execute(String[] commandLine) {
        commandHandler.displayCountFree(commandLine);
    }
}
