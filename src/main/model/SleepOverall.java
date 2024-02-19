package model;

import java.util.ArrayList;

public class SleepOverall {
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
        for (int i = getNumber() - 1; i >= getNumber() - 8; i--) {
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
        for (int i = getNumber() - 1; i >= getNumber() - 31; i--) {
            list.addSleep(this.sleepList.get(i));
            if (i == 0) {
                return list;
            }
        }
        return list;
    }


    public Sleep returnSleep(int num) {
        return sleepList.get(num);
    }

    public int getNapNum() {
        int nap = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("Nap")) {
                nap++;
            }
        }
        return nap;
    }

    public int getOverNum() {
        int over = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("overnight Sleep")) {
                over++;
            }
        }
        return over;
    }

    public int getAftNum() {
        int aft = 0;
        for (int i = 0; i < getNumber(); i++) {
            if (sleepList.get(i).getType().equals("Afternoon Sleep")) {
                aft++;
            }
        }
        return aft;
    }
}
