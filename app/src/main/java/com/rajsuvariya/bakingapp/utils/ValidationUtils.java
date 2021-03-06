package com.rajsuvariya.bakingapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by @rajsuvariya on 16/03/17.
 */

public final class ValidationUtils {

    public static boolean isPanValid(String panNumber) {
        Boolean isValid = false;
        if(panNumber.length()==10){
            isValid = true;
        }
        return  isValid;
    }
    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
