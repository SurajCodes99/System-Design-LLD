package commands;

import models.*;
import service.DisplayService;
import service.ParkingService;
import service.SlottingStrategy;

import java.util.HashMap;
import java.util.Map;

/* All logic related to executing my command resides here:*/
public class CommandHandler {
    private ParkingLot parkingLot;

    public void createParkingLot(String[] commandLineArgs){
        String parkingLotId = commandLineArgs[1];
        int floors = Integer.parseInt(commandLineArgs[2]);
        Integer slotsCount = Integer.valueOf(commandLineArgs[3]);

        //Step 1: Build the map:
        Map<Integer, ParkingSlot[]> floorToParkingSlotMap = new HashMap<>();
        SlottingStrategy.buildFloorToParkingSlotMap(floors, slotsCount, floorToParkingSlotMap);

        //Create object:
        ParkingLot newParkingLot;
        newParkingLot = new ParkingLot(parkingLotId, floorToParkingSlotMap);

        //Slotting strategy for different vehicles:
        SlottingStrategy.assignParkingSlots(newParkingLot, floors);
        System.out.printf("Created parking lot with %s floors and %s slots per floor%n", floors, slotsCount);

        this.parkingLot = newParkingLot;
    }
    public void displayCountFree(String[] commandLineArgs){
        var vehicleType = commandLineArgs[2];
        SlotType slotType = SlotType.valueOf(vehicleType);
        DisplayService.displayFreeCount(slotType, parkingLot);
    }

    // To return the free slots (returns the slot numbers):
    public void displayCountFreeSlots(String[] commandLineArgs) {
        var vehicleType = commandLineArgs[2];
        SlotType slotType = SlotType.valueOf(vehicleType);
        DisplayService.displayFreeSlots(slotType, parkingLot);
    }

    public void displayOccupiedSlots(String[] commandLineArgs) {
        var vehicleType = commandLineArgs[2];
        SlotType slotType = SlotType.valueOf(vehicleType);
        DisplayService.displayOccupiedSlots(slotType, parkingLot);
    }

    public void parkVehicle(String[] commandLineArgs){
        //Sample input: park_vehicle BIKE KA-01-DB-1541 black
        var vehicleType = commandLineArgs[1];
        var registrationNumber = commandLineArgs[2];
        var vehicleColor = commandLineArgs[3];

        Vehicle vehicle = new Vehicle(VehicleEnum.valueOf(vehicleType), registrationNumber,
                vehicleColor);

        SlotType slotType = SlotType.valueOf(vehicleType);
        ParkingService.parkVehicle(parkingLot, slotType, vehicle);

    }

    public void unParkVehicle(String[] commandLineArgs){
        var ticket = commandLineArgs[1];
        ParkingService.unParkVehicle(ticket, parkingLot);
    }
}
