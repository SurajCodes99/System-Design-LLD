package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {
    private SlotType slotType;
    private Integer slotNumber;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
}
