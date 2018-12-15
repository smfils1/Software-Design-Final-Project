package csc221.alg.model;

import java.util.Timer;
import java.util.TimerTask;

//Time Class that uses a night & day cycle system
public class Time {

    private final int startTime;
    private final int endTime;
    private int currentTime;
    private boolean day;
    int cycle;

    public Time(int cycle, int endTime) {
        this.startTime = 0;
        this.cycle = cycle;
        this.currentTime = 0;
        this.day = true;
        this.endTime = endTime; //Time of rescue team arrival
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public boolean isDay() {
        return day;
    }

    public boolean isNight() {
        return !day;
    }

    private void updateDay(){
        day = !day;
    }

    public void startTime(){
        Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            int halfDay = 0;
            @Override
            public void run() {
                currentTime++;
                if(halfDay == cycle/2){
                    halfDay = 0;
                    day = !day; //Toggles Night & Day
                }
                halfDay++;
                if(currentTime == endTime){
                    this.cancel(); // Rescue team arrivals
                }
            }},0,1000);
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean hasEnd(){
        return currentTime == endTime;
    }

}
