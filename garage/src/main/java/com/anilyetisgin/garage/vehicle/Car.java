package com.anilyetisgin.garage.vehicle;

import lombok.Data;


public class Car extends Vehicle {

    public Car(String plate, String color){
        super(plate, color, 2);
    }

}
