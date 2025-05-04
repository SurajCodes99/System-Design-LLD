package service;

import models.ParkingLot;
import models.ParkingSlot;
import models.SlotType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisplayService {
    //Method to display the count of free slots:
    public static void displayFreeCount(SlotType slotType, ParkingLot parkingLot) {
        for (Map.Entry<Integer, ParkingSlot[]> map : parkingLot.getFloorToParkingSlotMapping().entrySet()) {
            int freeSlotCount = countFreeSlots(map.getValue(), slotType);
            int floor = map.getKey();
            System.out.printf("No. of free slots for %s on Floor %s: %s%n",
                    slotType.name(), floor, freeSlotCount);
        }
    }
    //Method to display the fetch the free slots:
    public static void displayFreeSlots(SlotType slotType, ParkingLot parkingLot) {
        for (Map.Entry<Integer, ParkingSlot[]> map : parkingLot.getFloorToParkingSlotMapping().entrySet()) {
            List<Integer> freeSlots = fetchFreeSlots(map.getValue(), slotType);
            int floor = map.getKey();
            System.out.printf("Free slots for %s on Floor %s: %s%n",
                    slotType.name(), floor, freeSlots);
        }
    }

    //Method to display the occupied slots:
    public static void displayOccupiedSlots(SlotType slotType, ParkingLot parkingLot) {
        for (Map.Entry<Integer, ParkingSlot[]> map : parkingLot.getFloorToParkingSlotMapping().entrySet()) {
            List<Integer> occupiedSlots = fetchOccupiedSlots(map.getValue(), slotType);
            int floor = map.getKey();
            System.out.printf("Occupied slots for %s on Floor %s: %s%n",
                    slotType.name(), floor, occupiedSlots);
        }
    }

    private static int countFreeSlots(ParkingSlot[] parkingSlots, SlotType slotType) {
        int count = 0;

        //Checks if slot isn't occupied:
        for(ParkingSlot slot : parkingSlots)
            if(!slot.isOccupied() &&
                    slot.getSlotType().equals(slotType)) count++;

        //Returns count of unoccupied slots.
        return count;
    }

    private static List<Integer> fetchFreeSlots(ParkingSlot[] parkingSlots, SlotType slotType) {
        List<Integer> slotNumbers = new ArrayList<>();

        //Checks if slot isn't occupied:
        for(ParkingSlot slot : parkingSlots)
            if(!slot.isOccupied() &&
                    slot.getSlotType().equals(slotType)) slotNumbers.add(slot.getSlotNumber());

        //Returns count of unoccupied slots.
        return slotNumbers;
    }

    private static List<Integer> fetchOccupiedSlots(ParkingSlot[] parkingSlots, SlotType slotType) {
        List<Integer> occupiedSlotList = new ArrayList<>();

        //Checks if slot isn't occupied:
        for(ParkingSlot slot : parkingSlots)
            if(slot.isOccupied() &&
                    slot.getSlotType().equals(slotType)) occupiedSlotList.add(slot.getSlotNumber());

        //Returns count of unoccupied slots.
        return occupiedSlotList;
    }
}
