package model;

import java.util.ArrayList;

public class AverageMonth {
    //a list of monthly sleep with stat.(average)

    private ArrayList<Sleep> averageMonthList;

    //MODIFIES: this
    //EFFECTS: create a list for a week of sleep
    public AverageMonth() {
        averageMonthList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a sleep into the list;
    public void addSleep(Sleep sleep) {
        this.averageMonthList.add(sleep);
    }
}
