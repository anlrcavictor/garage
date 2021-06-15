package com.anilyetisgin.garage.base.exception;

import com.anilyetisgin.garage.base.constants.Constants;

public class InvalidActionException extends RuntimeException {

    public InvalidActionException(){
        super(Constants.INVALID_EX_MESSAGE);
    }
}
