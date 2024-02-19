package ui;

import model.*;


import java.util.Scanner;


public class SleepApp {

    private SleepOverall sleepOverall;
    private Goals goal;
    private Scanner input;
    private boolean keepGoing = true;

    //EFFECTS: run the sleep app;
    public SleepApp() {
        runSleep();
    }

    //MODIFIES: this
    //EFFECTS: progress the user input
    private void runSleep() {
        startSet();
        welcomeWord();

        while (keepGoing) {
            showScreen();
            chooseThing();

        }
    }

    //MODIFIES: this
    private void startSet() {
        sleepOverall = new SleepOverall();
        goal = new Goals(-1,-1);
    }

    private void welcomeWord() {
        String s;
        System.out.println("Welcome to sleep app");
        System.out.println("press any button to start app");
        System.out.println("press q to quit the app");
        System.out.println("Please type:");
        input = new Scanner(System.in);
        s = input.next();
        if (s.equals("q")) {
            keepGoing = false;
            System.out.println("You quit.");
        }
    }

    private void showScreen() {
        System.out.println("___________________________________________________________");
        System.out.println("Record sleep: press r");
        System.out.println("See all the sleep: all");
        System.out.println("See the stat: press s");
        System.out.println("Set Goal: press g");
        System.out.println("See the Goal: press t");
        System.out.println("See the categorize summarize: press c");
        System.out.println("Clear all the data and restart: press R");
        System.out.println("Type:");
    }

    private void record() {
        int t;
        Date d;
        int score;
        String type;

        System.out.println("_________________________________________________");

        t = typeTime();
        score = typeScore();
        d = createDate();
        type = chooseType();
        sleepOverall.addSleep(new Sleep(t,d,score,type));
        System.out.println("Record successfully!!");
        toContinue();
    }

    private int typeTime() {
        int t;

        System.out.println("Type how long you sleep (0-24):");
        input = new Scanner(System.in);
        t = input.nextInt();
        if ((t < 0) || (t > 24)) {
            System.out.println("what are you typing???");
            System.out.println("Set Hour to 8 automatic");
            t = 8;
        }
        return t;
    }

    private int typeScore() {
        int score;

        System.out.println("Type the score of your sleep (0-100):");
        input = new Scanner(System.in);

        score = input.nextInt();
        if ((score < 0) || (score > 100)) {
            System.out.println("what are you typing???");
            System.out.println("Set score to 50 automatic");
            score = 50;
        }

        return score;
    }

    private String chooseType() {
        String s;
        System.out.println("choose Type of your sleep:");
        System.out.println("press n to choose Nao;");
        System.out.println("press o to choose overnight Sleep;");
        System.out.println("press a to choose Afternoon Sleep;");
        System.out.print("Type:");
        input = new Scanner(System.in);
        s = input.next();
        if (s.equals("n")) {
            return "Nap";
        }
        if (s.equals("o")) {
            return "overnight Sleep";
        }
        if (s.equals("a")) {
            return "Afternoon Sleep";
        }

        System.out.println("what are you typing???");
        System.out.println("Set type to overnight Sleep automatic");
        return "overnight Sleep";
    }

    private Date createDate() {
        int y;
        int m;
        int d;

        System.out.println("Type the data of the sleep:");
        System.out.println("year(1000-9999):");
        input = new Scanner(System.in);
        y = input.nextInt();
        System.out.println("month(1 - 12):");
        input = new Scanner(System.in);
        m = input.nextInt();
        System.out.println("date(0-31):");
        input = new Scanner(System.in);
        d = input.nextInt();
        return new Date(y,m,d);
    }

    private void seeAll() {
        int num;

        num = sleepOverall.getNumber();
        System.out.println("You have " + num + " sleep has been recorded");

        for (int i = 0; i < num; i++) {
            Sleep s;
            s = sleepOverall.returnSleep(i);
            System.out.print("Sleep " + (i + 1) + " " + s.getDate().getYear() + "/");
            System.out.print(s.getDate().getMonth() + "/" + s.getDate().getDay() + " | ");
            System.out.print(" Hour: " + s.getTime() + " | " + " Score: " + s.getScore() + " | ");
            System.out.print(" Type: " + s.getType() + " | ");
            if (goal.getScore() != -1) {
                if (s.reachGoal(goal)) {
                    System.out.print(" Reach Goal!!!");
                }
                System.out.println(" Didn't Reach Goal :(");
            } else {
                System.out.print(" No Goals");
            }
            System.out.println(" ");
        }
        toContinue();
    }

    private void goal() {
        int score;
        int time;
        System.out.println("________________________________________________");
        System.out.println("Please type the hours you want to sleep(0-24): ");
        input = new Scanner(System.in);
        time = input.nextInt();

        System.out.println("Please type the score you want have(0-100): ");
        input = new Scanner(System.in);
        score = input.nextInt();
        goal = new Goals(time,score);
        System.out.println("Successfully Set your Goal!!");
        toContinue();
    }

    private void seeGoal() {

    }

    private void stat() {

    }

    private void categorize() {

    }

    private void restart() {

    }

    private void chooseThings(String s) {

        if (s.equals("r")) {
            record();
        }

        if (s.equals("all")) {
            seeAll();
        }

        if (s.equals("s")) {
            stat();
        }

        if (s.equals("g")) {
            goal();
        }

        if (s.equals("t")) {
            seeGoal();
        }

        if (s.equals("c")) {
            categorize();
        }

        if (s.equals("R")) {
            restart();
        }
    }

    private void chooseThing() {
        String s;
        input = new Scanner(System.in);
        s = input.next();
        chooseThings(s);
    }

    private void toContinue() {
        String s;
        System.out.println("Press c to continue:");
        input = new Scanner(System.in);
        s = input.next();
        if (!s.equals("c")) {
            System.out.println("Don't want continue? No way.");
        }
    }
}
