import commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParkingLotRunner {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("create_parking_lot", new CreateParkingLotCommand(commandHandler));
        commandMap.put("park_vehicle", new ParkVehicleCommand(commandHandler));
        commandMap.put("unpark_vehicle", new UnparkVehicleCommand(commandHandler));
        commandMap.put("display_free_count", new DisplayCountFreeCommand(commandHandler));
        commandMap.put("display_free_slots", new DisplayCountFreeSlotsCommand(commandHandler));
        commandMap.put("display_occupied_slots", new DisplayOccupiedSlotsCommand(commandHandler));


        executer(commandMap);
    }

    private static void executer(Map<String, Command> commandMap) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            if ("exit".equals(input)) {
                System.out.println("Exiting...");
                break;
            }

            String commandToExecute = parts[0].toLowerCase();

            if(commandToExecute.equals("display")) commandToExecute = parts[0] + "_".concat(parts[1]);

            Command command = commandMap.get(commandToExecute);

            if (command != null) {
                command.execute(parts);
            } else {
                System.out.println("Invalid command: " + input);
            }
        }
        scanner.close();
    }
}
