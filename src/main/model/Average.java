package model;

import java.util.ArrayList;

public class Average {
    // a list of weekly sleep with stat.(average)

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

    public int getNum() {
        return this.averageList.size();
    }

    public int calculateTime() {
        int time = 0;
        for (int i = 0; i < getNum(); i++) {
            time = time + this.averageList.get(i).getTime();
        }
        return time / getNum();
    }

    public int calculateScore() {
        int score = 0;
        for (int i = 0; i < getNum(); i++) {
            score = score + this.averageList.get(i).getScore();
        }
        return score / getNum();
    }

}
