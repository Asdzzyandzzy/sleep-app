package model;

import org.json.JSONObject;
import persistence.Alljasons;

//the Goals of the sleep
public class Goals implements Alljasons {


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

    // EFFECTS: return goal in Json form
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goal_time", this.time);
        json.put("goal_score", this.score);
        return json;
    }

}
