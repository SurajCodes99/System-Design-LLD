package commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ParkVehicleCommand implements Command {
    private CommandHandler commandHandler;

    @Override
    public void execute(String[] commandLine) {
        commandHandler.parkVehicle(commandLine);
    }
}
