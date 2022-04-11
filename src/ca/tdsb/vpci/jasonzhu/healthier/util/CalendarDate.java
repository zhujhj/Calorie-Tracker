package ca.tdsb.vpci.jasonzhu.healthier.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarDate {
    private String month = "Month";
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();

    public int getDays() {
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public String getMonth() {
        switch (cal.get(Calendar.MONTH)) {
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "August";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
        }
        return month;
    }

    public int getYear() {
        return cal.get(Calendar.YEAR);
    }

    public int getTodaysDate() {
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getWeekdayOfFirstDay() {
        cal.set(Calendar.DAY_OF_MONTH, 1);
        date = cal.getTime();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public void setToPrevMonth() {
        cal.add(Calendar.MONTH, -1);
        date = cal.getTime();
    }

    public void setToNextMonth() {
        cal.add(Calendar.MONTH, 1);
        date = cal.getTime();
    }
}


