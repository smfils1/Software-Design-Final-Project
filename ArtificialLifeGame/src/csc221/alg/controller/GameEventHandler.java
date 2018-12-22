package csc221.alg.controller;

public interface GameEventHandler {
    void exitEvent();
    void startGameEvent();
    void moveLeftEvent();
    void moveRightEvent();
    void moveUpEvent();
    void moveDownEvent();
    void attackEvent();
    void buildBaseEvent();
    void collectResourceEvent();
    void eatEvent();
    void hideEvent();
    void restEvent();
    void pauseEvent();
    void resumeEvent();
}
