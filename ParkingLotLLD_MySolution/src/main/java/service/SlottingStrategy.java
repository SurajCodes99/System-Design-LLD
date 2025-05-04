package service;

import models.ParkingLot;
import models.ParkingSlot;
import models.SlotType;
import java.util.Map;

public class SlottingStrategy {

    public static void buildFloorToParkingSlotMap(Integer floors, Integer slotsCount, Map<Integer, ParkingSlot[]> floorToParkingSlotMap) {
        for(int i = 0; i < floors; i++){
            ParkingSlot[] parkingSlots = new ParkingSlot[slotsCount];
            for(int j = 0; j < slotsCount; j++){
                parkingSlots[j] = new ParkingSlot();
                parkingSlots[j].setSlotNumber(j+1);
            }
            floorToParkingSlotMap.put(i+1, parkingSlots); //From 1 to n
        }
    }

    public static void assignParkingSlots(ParkingLot parkingLot, int floors) {
        // Get the map:
        Map<Integer, ParkingSlot[]> map = parkingLot.getFloorToParkingSlotMapping();

        for(int i = 0; i < floors; i++) {
            ParkingSlot[] parkingSlots = map.get(i+1); //From 1 to n
            defaultAssignmentParkingSlots(parkingSlots);
        }
    }

    private static void defaultAssignmentParkingSlots(ParkingSlot[] parkingSlots) {
        int count = 0;
        for(ParkingSlot parkingSlot : parkingSlots) {
            if(count == 0) parkingSlot.setSlotType(SlotType.TRUCK);
            else if(count < 3) parkingSlot.setSlotType(SlotType.BIKE);
            else parkingSlot.setSlotType(SlotType.CAR);

            // Setting the slot number from 1:
            parkingSlot.setSlotNumber(count + 1);
            count++;
        }
    }
}
