package persistence;

import model.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Goals;
import model.SleepOverall;
import org.json.*;

//from JsonSerializationDemo
public class ReadJason {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public ReadJason(String source) {
        this.source = source;
    }


    //EFFECTS: Reads the SleepOverall data from the JSON file
    public SleepOverall readSleepOverall() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSleepOverall(jsonObject.getJSONObject("sleepOverall"));
    }

    //EFFECTS: Reads the Goals data from the JSON file
    public Goals readGoals() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGoals(jsonObject.getJSONObject("goals"));
    }

    //EFFECTS: read the file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    //EFFECTS: change sleepoverall to sleepoverall
    private SleepOverall parseSleepOverall(JSONObject jsonObject) {
        SleepOverall sleepOverall = new SleepOverall();
        JSONArray sleepsArray = jsonObject.getJSONArray("sleeps");
        for (int i = 0; i < sleepsArray.length(); i++) {
            JSONObject sleepJson = sleepsArray.getJSONObject(i);
            sleepOverall.addSleep(parseSleep(sleepJson));
        }
        return sleepOverall;
    }

    //EFFECTS:change sleep to sleep
    private Sleep parseSleep(JSONObject jsonObject) {
        int time = jsonObject.getInt("sleep_time");
        Date date = parseDate(jsonObject.getJSONObject("sleep_date"));
        int score = jsonObject.getInt("sleep_score");
        String type = jsonObject.getString("sleep_type");

        return new Sleep(time, date, score, type);
    }

    //EFFECTS: change date to date
    private Date parseDate(JSONObject jsonObject) {
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");

        return new Date(year, month, day);
    }

    //change goal to goal
    private Goals parseGoals(JSONObject jsonObject) {
        int timeGoal = jsonObject.getInt("goal_time");
        int scoreGoal = jsonObject.getInt("goal_score");
        Goals goals = new Goals(timeGoal, scoreGoal); // Adjust this based on your Goals class constructor
        return goals;
    }


}
