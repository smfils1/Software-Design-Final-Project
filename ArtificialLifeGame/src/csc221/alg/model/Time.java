package csc221.alg.model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

//Time Class that uses a night & day cycle system
public class Time {
    private final int startTime;
    private final int endTime;
    private int currentTime;
    private boolean day;
    int cycle;
    private Timeline timeLoop;


    public Time(int cycle, int endTime) {
        this.timeLoop = new Timeline();
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

    public void startTime() {

    timeLoop = new Timeline();
    timeLoop.setCycleCount(Timeline.INDEFINITE);
    KeyFrame kf = new KeyFrame(
            Duration.seconds(1),
            new EventHandler<ActionEvent>() {
                int halfDay = 0;
                @Override
                public void handle(ActionEvent event) {
                    currentTime++;
                    if (halfDay == cycle / 2) {
                        halfDay = 0;
                        day = !day; //Toggles Night & Day
                    }
                    halfDay++;
                    if (currentTime == endTime) {
                        timeLoop.stop(); // Rescue team arrivals
                    }
                }
            } );
    timeLoop.getKeyFrames().add( kf );
    timeLoop.play();
}


    public int getEndTime() {
        return endTime;
    }

    public boolean hasEnd(){
        return currentTime == endTime;
    }

    public void pause(){
        timeLoop.pause();
    }

    public void stop(){
        timeLoop.stop();
    }

    public void resume(){
        timeLoop.play();
    }
}

