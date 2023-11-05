package com.acid.acid.utilities;

import com.acid.acid.entity.OutboundItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.function.Predicate;

public class Utility {

    public static Predicate<OutboundItem> rewardData = i -> i.getUnitPrice() * i.getQty() >= 50;

    public static double calculatePoint(OutboundItem item) {
        var amount = Utility.round(item.getQty() * item.getUnitPrice(),0);
        var min = 50;
        var max = 100;
        return (amount >= min && amount <= max) ?
                Math.min(min, amount - min) :
                2 * (amount - max) + Math.min(min, amount - min);
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

    public static int getQty(){
        return new Random().nextInt(5) + 1;
    }

    public static long getCusId(){
        long min = 1;
        long max = 51;
        return (long) Math.floor(Math.random() * (max - min + 1) + min);
    }
}