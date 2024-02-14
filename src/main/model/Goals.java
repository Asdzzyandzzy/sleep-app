package model;

public class Goals {
    //the Goals of the sleep

    private int time;
    private int score;

    //REQUIRES: score must between 0-100; time must between 1-24;
    //MODIFIES: this
    //EFFECTS: create a new GOALs
    public Goals(int time, int score) {
        this.time = time;
        this.score = score;
    }

    //EFFECTS: return time
    public int getTime() {
        return this.time;
    }

    //EFFECTS: return score;
    public int getScore() {
        return this.score;
    }

}
