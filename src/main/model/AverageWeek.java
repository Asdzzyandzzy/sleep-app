package model;

import java.util.ArrayList;

public class AverageWeek {
    // a list of weekly sleep with stat.(average)

    private ArrayList<Sleep> averageWeekList;

    //MODIFIES: this
    //EFFECTS: create a list for a week of sleep
    public AverageWeek() {
        averageWeekList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a sleep into the list;
    public void addSleep(Sleep sleep) {
        this.averageWeekList.add(sleep);
    }

}
