package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {
    private String parkingLotId;
    private Map<Integer, ParkingSlot[]> floorToParkingSlotMapping;
}
