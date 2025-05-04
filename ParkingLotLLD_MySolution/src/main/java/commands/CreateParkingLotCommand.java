package commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateParkingLotCommand implements Command{
    private CommandHandler commandHandler;

    @Override
    public void execute(String[] commandLineArgs) {
        commandHandler.createParkingLot(commandLineArgs);
    }
}
