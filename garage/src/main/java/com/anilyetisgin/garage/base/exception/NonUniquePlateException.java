package com.anilyetisgin.garage.base.exception;

import com.anilyetisgin.garage.base.constants.Constants;

public class NonUniquePlateException extends RuntimeException {

    public NonUniquePlateException(){
        super(Constants.NON_UNIQUE_EX_MESSAGE);
    }
}
