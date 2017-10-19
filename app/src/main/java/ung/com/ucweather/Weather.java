package ung.com.ucweather;

import java.util.ArrayList;

/**
 * Created by Ung on 19/10/2017.
 */

public class Weather {
    private String month;

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    private String city;

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private ArrayList<Day> days;

    public ArrayList<Day> getDays() {
        return this.days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
}
