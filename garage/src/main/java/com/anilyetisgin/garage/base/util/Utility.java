package com.anilyetisgin.garage.base.util;

import com.anilyetisgin.garage.base.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.StringJoiner;

public class Utility {

    public static String[] splitAction(String action) {
        return action.split(" ");
    }

    public static boolean isCommandCorrect(String action) {
        if(StringUtils.isEmpty(action)) {
            return false;
        }
        else {

            String [] command = splitAction(action);
            if (StringUtils.equals(command[0], Constants.STATUS)) {
                return (command.length == 1);
            } else if (StringUtils.equals(command[0], Constants.LEAVE)) {
                return (command.length == 2 && NumberUtils.isParsable(command[1]));
            } else if (StringUtils.equals(command[0], Constants.PARK)) {
                return (command.length == 4);
            } else return false;

        }

    }

    public static String joinStrings(String... args) {
        StringJoiner joiner = new StringJoiner(" ");
        for (String string : args) {
            joiner.add(string == null ? "" : string);
        }
        return joiner.toString();
    }

    public static String joinStringsWithNewLine(List<String> messages) {
        StringJoiner joiner = new StringJoiner("\n", "", "\n");
        for (String str : messages) {
            joiner.add(str == null ? "" : str);
        }
        return joiner.toString();
    }

}
