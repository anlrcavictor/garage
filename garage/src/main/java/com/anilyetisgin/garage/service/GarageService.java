package com.anilyetisgin.garage.service;

import com.anilyetisgin.garage.base.constants.Constants;
import com.anilyetisgin.garage.base.exception.InvalidActionException;
import com.anilyetisgin.garage.base.util.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Slf4j
@AllArgsConstructor
public class GarageService {

    private final ParkService parkService;

    private final CarFactory carFactory;

    private final ParkEntry parkEntry;

    public String issueTicket(String action) {

        if (Utility.isCommandCorrect(action)) {

            synchronized (parkEntry) {
                String[] command = Utility.splitAction(action);
                if (StringUtils.equals(command[0], Constants.PARK))
                    return parkService.parkVehicle(carFactory.carBuilder(command[1], command[2], command[3]));
                else if (StringUtils.equals(command[0], Constants.LEAVE))
                    return parkService.leaveVehicle(Integer.parseInt(command[1]));
                else
                    return parkService.writeStatus();
            }

        } else
            throw new InvalidActionException();
    }
}
