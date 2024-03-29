package model;

import org.json.JSONObject;
import persistence.Alljasons;
import persistence.WriteJason;

import java.util.ArrayList;

// a single sleep with a time, date, satisfied score and type.
public class Sleep implements Alljasons {


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

    //EFFECTS: return the Date of the sleep
    public String getDate() {
        return this.date.getDatee();
    }

    public Date getDatee() {
        return this.date;
    }

    //EFFECTS: return time of sleep
    public int getTime() {
        return this.time;
    }


    //EFFECTS: return the Score of the sleep
    public int getScore() {
        return this.score;
    }

    //EFFECTS: get type
    public String getType() {
        return this.type;
    }

    //EFFECTS: return true if reach the goals; return false if did not;
    public Boolean reachGoal(Goals g) {
        if ((this.time >= g.getTime()) && (this.score >= g.getScore())) {
            return true;
        }
        return false;
    }


    // EFFECTS: return sleep in Json form
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sleep_time", this.time);
        json.put("sleep_date", this.date.toJson());
        json.put("sleep_score", this.score);
        json.put("sleep_type", this.type);
        return json;
    }

}
