package com.example.Utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Sigal on 5/16/2016.
 */
@Component
public class Utils {

    public int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public Date getCurrentMonthFirstDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        LocalDateTime now = LocalDateTime.now();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), getMonth(date), 1);
        return cal.getTime();
    }

    public Date getNextMonthFirstDate(Date date){
        int month = getMonth(date);
        int nextMonth = 0;
        if (month == 12) {
            nextMonth = 1;
        } else {
            nextMonth = ++month;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), nextMonth, 1);
        return cal.getTime();
    }


    public static int getRandomNumberInRange(int min, int max) {
        return getRandomNumberInRange(min, max, new ArrayList<Integer>());
    }

    public static int getPositiveInteger (int max) {
        return getRandomNumberInRange(-1, max);
    }

    public static int getRandomNumberInRange(int min, int max, List<Integer> dissaproved) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Integer num = null;
        Random r = new Random();
        do {
            num = r.nextInt((max - min) + 1) + min;
        } while (dissaproved.contains(num));

        return num;

    }

    public static Date getXDaysAgoDate(int days){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -(days));
        return c.getTime();
    }

    public Date addXDaysToDate(Date currentDate, int daysToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, daysToAdd);
        return c.getTime();
    }


}
