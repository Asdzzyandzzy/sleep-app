package model;

import java.util.ArrayList;

public class ListOfSleepType {
    //the stat. of different type of sleep

    private ArrayList<Sleep> nap;
    private ArrayList<Sleep> overnightSleep;
    private ArrayList<Sleep> afternoonSleep;

    //MODIFIES: this
    //EFFECTS: create a list of Sleep which category by type.
    public ListOfSleepType() {
        nap = new ArrayList<>();
        overnightSleep = new ArrayList<>();
        afternoonSleep = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add sleep into the list of it's type;
    private void addSleeps(Sleep sleep) {

        if (sleep.getType() == "nap") {
            this.nap.add(sleep);
        }
        if (sleep.getType() == "overnight sleep") {
            this.overnightSleep.add(sleep);
        } else {
            this.afternoonSleep.add(sleep);
        }

    }
}
