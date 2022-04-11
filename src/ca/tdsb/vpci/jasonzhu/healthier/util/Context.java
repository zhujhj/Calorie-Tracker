package ca.tdsb.vpci.jasonzhu.healthier.util;

import ca.tdsb.vpci.jasonzhu.healthier.model.User;

public class Context {
    private static User user;
    private static int dayOfMonth;
    private static String month;
    private static int year;
    private static String spaces = " ";

    public static String getSpaces() {
        return spaces;
    }

    public static void setSpaces(String spaces) {
        Context.spaces = spaces;
    }

    public static String getWeekdays() {
        return weekdays;
    }

    public static void setWeekdays(String weekdays) {
        Context.weekdays = weekdays;
    }

    static String weekdays = "Sunday" + spaces + "Monday" + spaces + "Tuesday" + spaces + "Wednesday" + spaces +
            "Thursday" + spaces + "Friday" + spaces + "Saturday";

    public static String getMonth() {
        return month;
    }

    public static void setMonth(String month) {
        Context.month = month;
    }

    public static int getDayOfMonth() {
        return dayOfMonth;
    }

    public static void setDayOfMonth(int dayOfMonth) {
        Context.dayOfMonth = dayOfMonth;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Context.user = user;
    }

    public static void setYear(int year) {
        Context.year = year;
    }

    public static int getYear() {
        return Context.year;
    }

}