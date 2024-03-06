package model;

import persistence.Alljasons;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

public class SleepOverall implements Alljasons {
    //the list of Sleeps

    private ArrayList<Sleep> sleepList;

    //MODIFIES: this
    //EFFECTS: create an overall sleep list
    public SleepOverall() {
        this.sleepList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS add a sleep
    public void addSleep(Sleep sleep) {
        this.sleepList.add(sleep);
    }

    //MODIFIES: this
    //EFFECTS reset sleep overall
    public void resetSleep() {
        this.sleepList = new ArrayList<>();
    }

    //EFFECTS: return the size of the list
    public int getNumber() {
        return this.sleepList.size();
    }

    //EFFECTS: return the last seven days of sleep; and if days<7, return whatever we have;
    public Average getLastSeven() {
        Average list;
        list = new Average();
        for (int i = getNumber() - 1; i > getNumber() - 8; i--) {
            list.addSleep(this.sleepList.get(i));
            if (i == 0) {
                return list;
            }
        }
        return list;
    }


    //EFFECTS: return the last seven days of sleep; and if days<30, return whatever we have;
    public Average getLastMonth() {
        Average list;
        list = new Average();
        for (int i = getNumber() - 1; i > getNumber() - 31; i--) {
            list.addSleep(this.sleepList.get(i));
            if (i == 0) {
                return list;
            }
        }
        return list;
    }


    //REQUIRES: num < list.size
    //EFFECTS: return the sleep that index is num
    public Sleep returnSleep(int num) {
        return sleepList.get(num);
    }

    //EFFECTS: get the num of nap in the list
    public int getNapNum() {
        int nap = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("Nap")) {
                nap++;
            }
        }
        return nap;
    }

    //EFFECTS: get the num of overnight sleep in the list
    public int getOverNum() {
        int over = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("overnight Sleep")) {
                over++;
            }
        }
        return over;
    }

    //EFFECTS: get the num of afternoon sleep in the list
    public int getAftNum() {
        int aft = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("Afternoon Sleep")) {
                aft++;
            }
        }
        return aft;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray sleepArray = new JSONArray();

        for (Sleep sleep : sleepList) {
            sleepArray.put(sleep.toJson());
        }

        json.put("sleeps", sleepArray);
        return json;
    }
}
