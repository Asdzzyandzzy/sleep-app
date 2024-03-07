package model;

import persistence.Alljasons;
import persistence.*;


import org.json.JSONObject;
import persistence.Alljasons;
import persistence.WriteJason;

public class Date implements Alljasons {
    // The date of the sleep

    private int year;
    private int month;
    private int day;


    //REQUIRES: year must between 1000-9999; month must between 1 - 12;
    // day must between 1-31 for month 1 3 5 7 8 10 12;
    // day must between 1-30 for month 4 6 9 11;
    // day must between 1-29 for month 2 when year can / by 4 but not 100 or year can / by 400;
    // day must between 1-28 for month 2 in the other case;
    //MODIFIES: this
    //EFFECTS: crate a valid date
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //EFFECTS: get year
    public int getYear() {
        return this.year;
    }

    //EFFECTS: get month
    public int getMonth() {
        return this.month;
    }

    //EFFECTS: get day
    public int getDay() {
        return this.day;
    }

    // EFFECTS: return date in Json form
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("year",this.year);
        json.put("month",this.month);
        json.put("day",this.day);
        return json;
    }
}
