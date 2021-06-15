package com.anilyetisgin.garage.service;

import com.anilyetisgin.garage.base.constants.Constants;
import com.anilyetisgin.garage.base.util.Utility;
import com.anilyetisgin.garage.park.ParkSlot;
import com.anilyetisgin.garage.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


@Component
@Slf4j
@AllArgsConstructor
public class ParkService {

    private final List<ParkSlot> parkSlotList;

    private ParkService() {
        parkSlotList = new ArrayList<>();         ;
        parkSlotList.add(ParkSlot.builder().size(11).build());
    }

    public String parkVehicle(Vehicle vehicle) {

        if (isEmptySlotNotExist()) return Constants.GARAGE_FULL;
        if (checkPlate(vehicle.getPlate())) return Constants.NON_UNIQUE_EX_MESSAGE;

        int remainSize = 0;
        for (ParkSlot parkSlot : parkSlotList) {
            if (parkSlot.isEmpty()) {
                if (parkSlot.getSize() >= vehicle.getSize()) {
                    remainSize = parkSlot.getSize() - vehicle.getSize();
                    parkSlot.setVehicle(vehicle);
                    parkSlot.setSize(vehicle.getSize());
                } else return Constants.GARAGE_FULL;
            }
        }

        if (remainSize > 0) parkSlotList.add(ParkSlot.builder().size(remainSize).build());

        return Utility.joinStrings("Allocated", String.valueOf(vehicle.getSize() - 1), "slot");
    }

    public String leaveVehicle(int id) {

        if ((id - 1) > (parkSlotList.size() - 1) || (parkSlotList.get(id - 1).isEmpty()))
            return Utility.joinStrings(Constants.NO_VEHICLE, String.valueOf(id));

        int sizeTobeAdded = parkSlotList.get(id - 1).getSize();
        int sizeTobeAllocated = parkSlotList.get(id - 1).getVehicle().getSize();
        parkSlotList.remove(id - 1);
        parkSlotList.stream().filter(ParkSlot::isEmpty).forEach( parkSlot -> parkSlot.setSize(parkSlot.getSize() + sizeTobeAdded));

        if (isEmptySlotNotExist()) parkSlotList.add(ParkSlot.builder().size(sizeTobeAdded).build());

        return Utility.joinStrings("Allocated", String.valueOf(sizeTobeAllocated - 1), "slot");
    }

    public String writeStatus() {
        if (isGarageEmpty())
            return Constants.GARAGE_EMPTY;
        else
            return writeStatusForNonEmptyGarage();
    }

    private boolean isEmptySlotNotExist() {
        return !parkSlotList.stream().anyMatch(ParkSlot::isEmpty);
    }

    private boolean isGarageEmpty() {
        return parkSlotList.size() == 1 && parkSlotList.get(0).isEmpty();
    }

    private boolean checkPlate(String plate) {
        return parkSlotList.stream().filter(parkSlot -> !parkSlot.isEmpty()).anyMatch(parkSlot -> StringUtils.equals(parkSlot.getVehicle().getPlate(), plate));
    }

    private String writeStatusForNonEmptyGarage() {
        List<String> messages = new ArrayList<>();
        int counter = 0;
        for (ParkSlot parkSlot : parkSlotList) {
            if (!parkSlot.isEmpty()) {
                messages.add(Utility.joinStrings(parkSlot.toString(), String.valueOf(parkSlotList.indexOf(parkSlot)), writeLocation(counter, counter + parkSlot.getSize())));
                counter += parkSlot.getSize();

            }
        }
        return Utility.joinStringsWithNewLine(messages);
    }

    private String writeLocation(int from, int to) {
        to -= 1;
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        while (from < to) {
            joiner.add(String.valueOf(++from));
        }
        return joiner.toString();
    }

}
