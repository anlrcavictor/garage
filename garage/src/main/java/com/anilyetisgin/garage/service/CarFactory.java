package com.anilyetisgin.garage.service;

import com.anilyetisgin.garage.base.constants.VehicleConstants;
import com.anilyetisgin.garage.vehicle.Car;
import com.anilyetisgin.garage.vehicle.Jeep;
import com.anilyetisgin.garage.vehicle.Truck;
import com.anilyetisgin.garage.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    public Vehicle carBuilder(String plate, String color, String vehicleType) {
        Vehicle vehicle;
        switch (vehicleType) {
            case VehicleConstants.CAR:
                vehicle = new Car(plate, color);
                break;
            case VehicleConstants.JEEP:
                vehicle = new Jeep(plate, color);
                break;
            case VehicleConstants.TRUCK:
                vehicle = new Truck(plate, color);
                break;
            default:
                return null;
        }
        return vehicle;
    }


}
