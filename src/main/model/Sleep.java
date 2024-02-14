package model;

import java.util.ArrayList;

public class Sleep {
    // a single sleep with a time, date, satisfied score and type.

    private int time;
    private Date date;
    private int score;
    private String type;

    //REQUIRES: time must less than 24; score must between 0 - 100;
    // type: "Nap","overnight Sleep", "Afternoon Sleep"
    //MODIFIES: this
    //EFFECTS: create a new sleep
    public Sleep(int time, Date date, int score, String type) {
        this.time = time;
        this.date = date;
        this.score = score;
        this.type = type;
    }

    //EFFECTS: return true if reach the goals; return false if did not;
    public boolean isReachGoals(Goals goal) {
        if ((goal.getTime() == this.time) && (goal.getScore() == this.score)) {
            return true;
        }
        return false;
    }

    //EFFECTS: return time of sleep
    public int getTime() {
        return this.time;
    }

    //EFFECTS: return the Date of the sleep
    public Date getDate() {
        return this.date;
    }

    //EFFECTS: return the Score of the sleep
    public int getScore() {
        return this.score;
    }

    public String getType() {
        return this.type;
    }

}
