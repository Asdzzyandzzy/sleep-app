package ui;

import model.*;
import model.Event;
import model.exception.LogException;
import persistence.ReadJason;
import persistence.WriteJason;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.WindowAdapter;


//The class for running the sleepappGUI
public class SleepAppGUI implements LogPrinter {


    private static final String jsonPath = "./data/Sleep.json";
    private WriteJason jsonWriter;
    private ReadJason jsonReader;

    private SleepOverall sleepOverall;
    private Goals goal;



    private static final int WIDTH = 6000;
    private static final int HEIGHT = 7000;

    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton recordButton;
    private JButton seeAllButton;
    private JButton statButton;
    private JButton setGoalButton;
    private JButton seeGoalButton;
    private JButton categorizeButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton logButton;


    //MODIFIES: this
    // EFFECTS: runs the sleep app
    public SleepAppGUI() throws FileNotFoundException {
        startSeting();
        initiallizegraphic();
    }

    // MODIFIE: this
    // EEFECtS: sit the bengining
    private void startSeting() {
        jsonWriter = new WriteJason(jsonPath);
        jsonReader = new ReadJason(jsonPath);
        sleepOverall = new SleepOverall();
        goal = new Goals(-1, -1);
    }

    // MODIFIE: this
    // EEFECtS: sit the bengining screen
    private void initiallizegraphic() {

        frame = new JFrame("Sleep APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        URL url = SleepAppGUI.class.getResource("/DOGGG.png");



        ImageIcon originalIcon = new ImageIcon(url);
        Image originalImage = originalIcon.getImage();


        Image scaledImage = originalImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


        ImageIcon scaledIcon = new ImageIcon(scaledImage);


        JLabel imageLabel = new JLabel(scaledIcon);


        mainPanel.add(imageLabel, BorderLayout.EAST);




        textArea = new JTextArea();
        textArea.setEditable(false);
        mainPanel.add(textArea,BorderLayout.CENTER);

        inputField = new JTextField(20);

        buttons();

        strangeAction();


        frame.add(mainPanel);


        windowcheck();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //EFFECT: Create Buttons and create active listner
    public void buttons() {
        button();

        addButton();
    }

    //EFFECTS: show log when windows is closed
    public void windowcheck() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LogPrinter lp;
                try {
                    lp = new LogPrinter() {
                        @Override
                        public void printLog(EventLog el) throws LogException {
                            String ss = "";
                            for (Event next : el) {
                                ss = ss + next.toString() + "\n";
                            }
                            System.out.println(ss);
                        }
                    };
                    lp.printLog(EventLog.getInstance());
                } catch (LogException ee) {
                    System.out.println("Wrong");
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECT: Sit the buttions
    public void button() {
        recordButton = new JButton("Record Sleep");
        seeAllButton = new JButton("See All Sleep");
        statButton = new JButton("See Stats");
        setGoalButton = new JButton("Set Goal");
        seeGoalButton = new JButton("See Goal");
        categorizeButton = new JButton("Categorize Summary");
        clearButton = new JButton("Clear All Data");
        saveButton = new JButton("Save Data");
        loadButton = new JButton("Load Data");
        logButton = new JButton("See log");
        quitButton = new JButton("Quit");


    }



    //MODIFIES: this
    //EFFECTS: show the button in the screen
    public void addButton() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(recordButton);
        buttonPanel.add(seeAllButton);
        buttonPanel.add(statButton);
        buttonPanel.add(setGoalButton);
        buttonPanel.add(seeGoalButton);
        buttonPanel.add(categorizeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(logButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }


    //EFFECTS: if you click, call the method
    public void strangeAction() {

        recordButton.addActionListener(e -> record());
        seeAllButton.addActionListener(e -> seeAll());
        statButton.addActionListener(e -> stat());
        setGoalButton.addActionListener(e -> setGoal());
        seeGoalButton.addActionListener(e -> seeGoal());
        categorizeButton.addActionListener(e -> categorize());
        clearButton.addActionListener(e -> restart());
        saveButton.addActionListener(e -> savefiles());
        loadButton.addActionListener(e -> loadfiles());
        quitButton.addActionListener(e -> quitApp());
        logButton.addActionListener(e -> logthing());
    }

    // EFFECTS: record the sleep
    private void record() {
        int t = typeTime();
        int score = typeScore();
        String type = typeType();
        Date d = typeDate();

        sleepOverall.addSleep(new Sleep(t, d, score, type));
        textArea.append("SHow TEXTS");
    }


    // MODIFIES: this
    // EFFECTS: restart it
    private void restart() {
        sleepOverall = new SleepOverall();
        goal = new Goals(-1, -1);
        textArea.append("App restarted, all data clean.\n");
    }

    // EFFECTS: save files
    private void savefiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(sleepOverall, goal);
            jsonWriter.close();
            textArea.append("Data saved to " + jsonPath + "\n");
        } catch (IOException e) {
            textArea.append("Unable to write to file: " + jsonPath + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: load files
    private void loadfiles() {
        try {
            sleepOverall = jsonReader.readSleepOverall();
            goal = jsonReader.readGoals();
            textArea.append("Data loaded from " + jsonPath + "\n");
        } catch (IOException e) {
            textArea.append("Unable to read from file: " + jsonPath + "\n");
        }
    }

    // EFFECTS: quit
    private void quitApp() {
        LogPrinter lp;
        try {
            lp = new LogPrinter() {
                @Override
                public void printLog(EventLog el) throws LogException {
                    String ss = "";
                    for (Event next : el) {
                        ss = ss + next.toString() + "\n";
                    }
                    System.out.println(ss);
                }
            };
            lp.printLog(EventLog.getInstance());
        } catch (LogException e) {
            System.out.println("Wrong");
        }
        System.exit(0);
    }


    //EFFECTS: see the sleep list
    private void seeAll() {
        String s = "";
        sleepOverall.tryseeALL();
        int num = sleepOverall.getNumber();
        s = s + "You have " + num + " sleep records:\n";

        for (int i = 0; i < num; i++) {
            Sleep ss = sleepOverall.getSleep(i);
            s = s + "Sleep " + (i + 1) + ": " + ss.getDate() + " | Hour: " + ss.getTime() + " | Score: "
                    + ss.getScore() + " | Type: " + ss.getType();

            if (goal.getScore() != -1 && ss.getType().equals("overnight Sleep")) {
                if (ss.reachGoal(goal)) {
                    s = s + " Reach Goal!!!\n";
                } else {
                    s = s + " Didn't Reach Goal :(\n";
                }

            } else {
                s = s + " Goal is not for " + ss.getType() + "\n";
            }
        }

        textArea.setText(s);
    }

    //EFFECTS: see the stats
    private void stat() {
        sleepOverall.tryseeall();
        String ss = "Average hours (last 7 sleeps): " + sleepOverall.getLastSeven().calculateTime()
                + "\nAverage score (last 7 sleeps): " + sleepOverall.getLastSeven().calculateScore();
        String ms = "Average hours (last month): " + sleepOverall.getLastMonth().calculateTime()
                + "\nAverage score (last month): " + sleepOverall.getLastMonth().calculateScore();
        textArea.setText(ss + "\n\n" + ms);
    }

    //MODIFIES: this
    //EFFECTS: set the goal
    private void setGoal() {
        goal.trysetGoals();

        String s = JOptionPane.showInputDialog(frame, "Hours for goal (0-24):");
        String ss = JOptionPane.showInputDialog(frame, "score for goal (0-100):");


        int time = Integer.parseInt(s);
        int score = Integer.parseInt(ss);
        goal = new Goals(time, score);
        textArea.setText("Goal set: " + time + " hours, score: " + score);
    }



    //EFFECtS: see the goals
    private void seeGoal() {
        goal.tryseeGoals();
        if ((goal.getScore() < 0) || (goal.getTime() < 0)) {
            textArea.setText("No goals");
        } else {
            textArea.setText("Goal: Hours: " + goal.getTime() + ", Score: " + goal.getScore());
        }
    }

    //EFFECTS: see the categorize %
    private void categorize() {
        int total = sleepOverall.getNumber();
        int n = sleepOverall.getNapNum();
        int a = sleepOverall.getAftNum();
        int o = sleepOverall.getOverNum();

        String s = "Total sleeps: " + total
                + "\nNaps: " + n + " (" + percentt(n, total) + "%)"
                + "\nAfternoon Sleeps: " + a + " (" + percentt(a, total) + "%)"
                + "\nOvernight Sleeps: " + o + " (" + percentt(o, total) + "%)";
        textArea.setText(s);
    }

    //EFFECTS: get the percent
    private String percentt(int c, int t) {
        return String.format("%.2f", ((double) c / (double) t) * 100);
    }


    //EFFECTS: get the time
    private int typeTime() {
        while (2 > 1) {
            String s = JOptionPane.showInputDialog(frame, "Enter sleep hours (0-24):");

            int time = Integer.parseInt(s);
            if (time >= 0 && time <= 24) {
                return time;
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a number 0 - 24.");
            }

        }
    }

    //EFFECTS: get the score
    private int typeScore() {
        while (2 > 1) {
            String s = JOptionPane.showInputDialog(frame, "Enter sleep score (0-100):");

            int score = Integer.parseInt(s);
            if (score >= 0 && score <= 100) {
                return score;
            } else {
                JOptionPane.showMessageDialog(frame, "Please number 0 - 100.");
            }

        }
    }

    //EFFECTS: get the type
    private String typeType() {

        JComboBox<String> st = new JComboBox<>(new String[]{"overnight Sleep", "Afternoon Sleep", "Nap"});

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Select the type of sleep:"));
        myPanel.add(st);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Sleep Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return st.getSelectedItem().toString();
        } else {
            return null;
        }
    }

    //EFFECT: get the date
    private Date typeDate() {
        JTextField year = new JTextField(5);
        JTextField month = new JTextField(5);
        JTextField day = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Year:"));
        myPanel.add(year);
        myPanel.add(new JLabel("Month:"));
        myPanel.add(month);

        myPanel.add(new JLabel("Day:"));
        myPanel.add(day);

        JOptionPane.showConfirmDialog(frame, myPanel,
                "Please enter the date of the sleep", JOptionPane.OK_CANCEL_OPTION);


        int k = Integer.parseInt(day.getText());



        return new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), k);

    }

    //the main function to run the app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SleepAppGUI();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to run application: file not found",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    public void logthing() {
        LogPrinter lp;
        try {
            lp = new LogPrinter() {
                @Override
                public void printLog(EventLog el) throws LogException {
                    String ss = "";
                    for (Event next : el) {
                        ss = ss + next.toString() + "\n";
                    }
                    textArea.setText(ss);
                }
            };
            lp.printLog(EventLog.getInstance());
        } catch (LogException e) {
            System.out.println("d");
        }
    }

    @Override
    public void printLog(EventLog el) throws LogException {
        String ss = "";
        for (Event next : el) {
            ss = ss + next.toString() + "\n";
        }
        textArea.setText(ss);
    }



}

