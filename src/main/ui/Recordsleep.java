package ui;

import model.Date;
import model.Sleep;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import model.SleepOverall;

public class Recordsleep extends Printer {

    int count;
    int t;
    Date d;
    int score;
    String type;


    public Recordsleep() {
        count = 0;
        t = 0;
        score = 0;
        type = "";
        d = new Date(1,2,3);
    }

    //EFFECTS: run method to record a sleep
    public Sleep recordd() {


        typeWord("<html>Type how long you sleep (0-24):<html>");
        t = typeTime(Integer.parseInt(""));



        typeWord("<html>Type the score of your sleep (0-100):<html>");
        score = typeScore(Integer.parseInt(submitInput()));



        typeWord("<html>Type the data of the sleep:<br>year(1000-9999):<html>");
        int y = Integer.parseInt(submitInput());



        typeWord("<html> month(1 - 12):<html>");
        int m = Integer.parseInt(submitInput());



        typeWord("<html>date(0-31):<html>");
        int date = Integer.parseInt(submitInput());




        d = new Date(y,m,date);

        type = chooseType();



        return new Sleep(t,d,score,type);


    }

    //EFFECTS: create a time for sleep
    private int typeTime(int t) {


        if ((t < 0) || (t > 24)) {
            System.out.println("what are you typing???");
            System.out.println("Set Hour to 8 automatic");
            t = 8;
        }
        return t;


    }



    //EFFECTS: create a score for sleep
    private int typeScore(int score) {

        if ((score < 0) || (score > 100)) {
            System.out.println("what are you typing???");
            System.out.println("Set score to 50 automatic");
            score = 50;
        }

        return score;
    }

    //EFFECTS: create a type for sleep
    private String chooseType() {
        String s = "";
        System.out.println("choose Type of your sleep:");
        System.out.println("press n to choose Nao;");
        System.out.println("press o to choose overnight Sleep;");
        System.out.println("press a to choose Afternoon Sleep;");
        System.out.print("Type:");

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



}
