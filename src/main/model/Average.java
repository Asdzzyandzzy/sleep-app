package model;

import java.util.ArrayList;

// a list of weekly sleep with stat.(average)
public class Average {


    private ArrayList<Sleep> averageList;

    //MODIFIES: this
    //EFFECTS: create a list for a week of sleep
    public Average() {
        averageList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a sleep into the list;
    public void addSleep(Sleep sleep) {
        this.averageList.add(sleep);
    }

    //EFFECTS: get the size of the list
    public int getNum() {
        return this.averageList.size();
    }

    //Requires: list is not empty
    //EFFECTS: calculate the average hours people sleep in each sleep
    public int calculateTime() {
        int time = 0;
        for (int i = 0; i < getNum(); i++) {
            time = time + this.averageList.get(i).getTime();
        }
        return time / getNum();
    }

    //Requires: list is not empty
    //EFFECTS: calculate the average score in each sleep
    public int calculateScore() {
        int score = 0;
        for (int i = 0; i < getNum(); i++) {
            score = score + this.averageList.get(i).getScore();
        }
        return score / getNum();
    }

}
