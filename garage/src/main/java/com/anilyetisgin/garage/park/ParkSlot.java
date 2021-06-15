package com.anilyetisgin.garage.park;

import com.anilyetisgin.garage.base.util.Utility;
import com.anilyetisgin.garage.vehicle.Vehicle;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ParkSlot {

    private Vehicle vehicle;

    private int size;

    public boolean isEmpty(){
        return vehicle == null;
    }

    public String toString(){
        return Utility.joinStrings(this.vehicle.getPlate(), this.vehicle.getColor());
    }


}
