package com.creditcard.CreditCardApp.util;

import java.time.LocalDate;
import java.time.Period;

public class AgeUtil {
    public static int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
