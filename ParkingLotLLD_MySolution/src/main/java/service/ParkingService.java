package service;

import models.ParkingLot;
import models.ParkingSlot;
import models.SlotType;
import models.Vehicle;

import java.util.Map;
import java.util.Objects;

public class ParkingService {
    public static void parkVehicle(ParkingLot parkingLot, SlotType slotType,
                                   Vehicle vehicle) {
        /*Go through each floor and then figure out
        which floor has the first available slot*/

        //index-0 would have the slotNumber, index-1 floorNumber
        int[] emptySlotDetails = checkAndReturnSlotDetails(parkingLot, slotType);
        int emptySlot = emptySlotDetails[0];
        int emptyFloor = emptySlotDetails[1];

        if(emptySlot != -1 && emptyFloor != -1) {
            occupySlot(parkingLot, emptySlot, emptyFloor, vehicle);
            String ticket = generateTicket(emptySlotDetails, parkingLot.getParkingLotId());
            System.out.printf("Parked vehicle. Ticket ID: %s%n",ticket);
        } else {
            System.out.println("Parking Lot is full!");
        }
    }

    private static int[] checkAndReturnSlotDetails(ParkingLot parkingLot, SlotType slotType) {
        int[] emptySlotDetails = new int[]{-1, -1};
        for (Map.Entry<Integer, ParkingSlot[]> map : parkingLot.getFloorToParkingSlotMapping().entrySet()) {
            Integer emptySlotNumber = fetchEmptySlotNumber(map.getValue(), slotType);
            if(Objects.nonNull(emptySlotNumber)) {
                emptySlotDetails[0] = emptySlotNumber;
                emptySlotDetails[1] = map.getKey();
                break;
            }
        }
        return emptySlotDetails;
    }

    private static Integer fetchEmptySlotNumber(ParkingSlot[] parkingSlots, SlotType slotType) {
        for(ParkingSlot parkingSlot : parkingSlots) {
            if(!parkingSlot.isOccupied()
                    && slotType.equals(parkingSlot.getSlotType())) return parkingSlot.getSlotNumber();
        }
        return null;
    }

    private static String generateTicket(int[] emptySlotDetails, String parkingLotId) {
        String floorNumber = "_".concat(String.valueOf(emptySlotDetails[1]));
        String slotNumber = "_".concat(String.valueOf(emptySlotDetails[0]));

        return parkingLotId.concat(floorNumber).concat(slotNumber);
    }

    private static void occupySlot(ParkingLot parkingLot, int emptySlot, int emptyFloor, Vehicle vehicle) {
        ParkingSlot[] parkingSlots = parkingLot.getFloorToParkingSlotMapping().get(emptyFloor);
        int parkingSlotNumber = emptySlot - 1;

        // Occupy the parkingSlot:
        parkingSlots[parkingSlotNumber].setOccupied(true);
        parkingSlots[parkingSlotNumber].setParkedVehicle(vehicle);
    }

    public static void unParkVehicle(String ticket, ParkingLot parkingLot) {
        String[] ticketSubParts = ticket.split("_");
        Integer ticketFloor = Integer.parseInt(ticketSubParts[1]);
        int ticketSlotId = Integer.parseInt(ticketSubParts[2]);
        String ticketId = ticketSubParts[0];

        boolean isTicketValid = validateTicket(parkingLot, ticketFloor, ticketSlotId,
                ticketId);

        if(isTicketValid) {
            var vehicleDetails = unOccupySlotAndFetchVehicleDetails(parkingLot, ticketSlotId, ticketFloor);
            var registrationNumber = vehicleDetails.getRegistrationNumber();
            var vehicleColor = vehicleDetails.getColor();
            System.out.printf("Unparked vehicle with Registration Number: %s and Color: %s%n",
                    registrationNumber, vehicleColor);
        }else {
            System.out.println("Invalid Ticket!");
        }
    }

    private static Vehicle unOccupySlotAndFetchVehicleDetails(ParkingLot parkingLot, int ticketSlotId, Integer ticketFloor) {
        Vehicle vehicleDetails;
        ParkingSlot[] parkingSlots = parkingLot.getFloorToParkingSlotMapping().get(ticketFloor);
        int parkingSlotNumber = ticketSlotId - 1;

        // UnOccupy the parkingSlot:
        parkingSlots[parkingSlotNumber].setOccupied(false);

        vehicleDetails = parkingSlots[parkingSlotNumber].getParkedVehicle();
        parkingSlots[parkingSlotNumber].setParkedVehicle(null);
        return vehicleDetails;
    }

    private static boolean validateTicket(ParkingLot parkingLot, Integer ticketFloor, int ticketSlotId,
                                          String ticketId) {
        boolean validTicket = ticketId.equals(parkingLot.getParkingLotId());
        boolean validFloor = Objects.nonNull(parkingLot.getFloorToParkingSlotMapping().get(ticketFloor)); //Check if the floor exists
        if(validFloor && validTicket) { // If the floor and ticket exists then check if the spot is occupied:
            return parkingLot.getFloorToParkingSlotMapping().get(ticketFloor).length >= ticketSlotId &&
                    parkingLot.getFloorToParkingSlotMapping().get(ticketFloor)[ticketSlotId-1].isOccupied();
        }
        return false;
    }
}
