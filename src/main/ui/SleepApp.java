package ui;

import model.*;
import persistence.ReadJason;
import persistence.WriteJason;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.awt.event.ActionListener;

public class SleepApp extends Printer {

    //Jason files
    private static final String jasonPath = "./data/Sleep.json";
    private WriteJason jsonWriter;
    private ReadJason jsonReader;

    private Printer print;

    private SleepOverall sleepOverall;
    private Goals goal;
    private Scanner input;
    private boolean keepGoing = true;

    //EFFECTS: run the sleep app;
    public SleepApp() throws FileNotFoundException {
        super();


        jsonWriter = new WriteJason(jasonPath);
        jsonReader = new ReadJason(jasonPath);
        runSleep();
    }





    //MODIFIES: this
    //EFFECTS: progress the user input.
    private void runSleep() {

        startSet();

        welcomeWord();

       // while (keepGoing) {
        Showscreen showscreen;
        showscreen = new Showscreen();

      //  }
    }




    //MODIFIES: this
    private void startSet() {
        sleepOverall = new SleepOverall();
        goal = new Goals(-1,-1);
    }

    //MODIFIES: this
    //EFFECTS: print the welcome sentence; user can use q to quit
    private void welcomeWord() {

        Object[] options = {"Start", "Quit"};
        int choice = JOptionPane.showOptionDialog(null,
                "Welcome to the sleep app.\nPress Start to begin or Quit to exit.",
                "Welcome",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // 处理用户的选择
        if (choice == JOptionPane.NO_OPTION) {
            keepGoing = false;
            JOptionPane.showMessageDialog(null, "You chose to quit.", "Exiting", JOptionPane.INFORMATION_MESSAGE);
            // 这里可以放置退出程序的代码，例如 System.exit(0);
            System.exit(0);
        } else {
            // 这里可以继续程序的其他部分
            JOptionPane.showMessageDialog(null, "Starting the app...", "Start", JOptionPane.INFORMATION_MESSAGE);
            // 启动应用程序的其他部分
        }




    }



    public void record() {
        Recordsleep r;
        r = new Recordsleep();
        sleepOverall.addSleep(r.recordd());
        System.out.println("Record successfully!!");
    }


    //EFFECTS: show a list of all the sleep
    private void seeAll() {
        int num;

        num = sleepOverall.getNumber();
        System.out.println("You have " + num + " sleep has been recorded");

        for (int i = 0; i < num; i++) {
            Sleep s = sleepOverall.returnSleep(i);
            System.out.print("Sleep " + (i + 1) + " " + s.getDate().getYear() + "/" + s.getDate().getMonth() + "/");
            System.out.print(s.getDate().getDay() + " | " + " Hour: " + s.getTime() + " | " + " Score: ");
            System.out.print(s.getScore() + " | " + " Type: " + s.getType() + " | ");
            if (goal.getScore() != -1) {
                if (s.getType().equals("overnight Sleep")) {
                    if (s.reachGoal(goal)) {
                        System.out.println(" Reach Goal!!!");
                    } else {
                        System.out.println(" Didn't Reach Goal :(");
                    }
                } else {
                    System.out.println(" Goal is not for " + s.getType());
                }
            } else {
                System.out.println(" No Goals");
            }
        }
        toContinue();
    }

    //MODIFIES: this
    //EFFECTS: set Goal
    private void goal() {
        int score;
        int time;
        System.out.println("________________________________________________");
        System.out.println("Please type the hours you want to sleep(overnight)(0-24): ");
        input = new Scanner(System.in);
        time = input.nextInt();

        System.out.println("Please type the score you want have(0-100): ");
        input = new Scanner(System.in);
        score = input.nextInt();
        goal = new Goals(time,score);
        System.out.println("Successfully Set your Goal!!");
        toContinue();
    }

    //EFFECTS: show the goal
    private void seeGoal() {
        System.out.println("________________________________________________");
        if ((goal.getScore() < 0) | (goal.getTime() < 0)) {
            System.out.println("You haven't set goals");
        } else {
            System.out.println("Your goal:");
            System.out.println("Hours for a overnight sleep: " + goal.getTime());
            System.out.println("Score for a overnight sleep: " + goal.getScore());
        }
        toContinue();
    }

    //EFFECTS: show the stat for the sleep
    private void stat() {
        System.out.println("________________________________________________");
        System.out.println("Your average for last 7 sleep: ");
        System.out.println("Average hours is " + sleepOverall.getLastSeven().calculateTime());
        System.out.println("Average score is " + sleepOverall.getLastSeven().calculateTime());
        System.out.println(" ");
        System.out.println("Your average for last 7 sleep: ");
        System.out.println("Average hours is " + sleepOverall.getLastMonth().calculateTime());
        System.out.println("Average score is " + sleepOverall.getLastMonth().calculateTime());
    }

    //EFFECTS: show the category data for the sleep
    private void categorize() {
        int total = sleepOverall.getNumber();
        int nap = sleepOverall.getNapNum();
        int aft = sleepOverall.getAftNum();
        int over = sleepOverall.getOverNum();
        double nt = ((double) nap / total) * 100;
        double at = ((double) aft / total) * 100;
        double ot = ((double) over / total) * 100;
        System.out.println("________________________________________________");
        System.out.println("You have sleep " + total + " times");
        System.out.println("Nap: " + nap + " (" +  String.format("%.2f", nt) + "%)");
        System.out.println("Nap: " + aft + " (" +  String.format("%.2f", at) + "%)");
        System.out.println("Nap: " + over + " (" +  String.format("%.2f", ot) + "%)");
        toContinue();
    }

    //EFFECTS: restart the app
    private void restart() {
        runSleep();
    }



    //EFFECTS: give a time to users that they can read information
    private void toContinue() {
        String s;
        System.out.println("Press c to continue:");
        input = new Scanner(System.in);
        s = input.next();
        if (!s.equals("c")) {
            System.out.println("Don't want continue? No way.");
        }
    }

    // EFFECTS: saves the workroom to file
    private void savefiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(sleepOverall,goal);
            jsonWriter.close();
            System.out.println("Saved " + "files" + " to " + jasonPath);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jasonPath);
        }
        toContinue();
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadfiles() {
        try {
            sleepOverall = jsonReader.readSleepOverall();
            goal = jsonReader.readGoals();
            System.out.println("Loaded " + "file" + " from " + jasonPath);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jasonPath);
        }
        toContinue();
    }





}
