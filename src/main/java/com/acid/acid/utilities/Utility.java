package com.acid.acid.utilities;

import com.acid.acid.entity.OutboundItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static double calculatePoint(OutboundItem item){
        var amount = Utility.round(item.getQty() * item.getUnitPrice(), 0);
        return (amount >= 50 && amount <= 100) ? amount : amount * 2;
    }
    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static String getMonth(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate d = LocalDate.parse(date, formatter);
        Month month = d.getMonth();
        return month.name();
    }
}
