package csc221.alg.model;

import java.util.ArrayList;

abstract public class Creature extends Entity implements Movable {
    private char size;
    private int health;
    private boolean visible;

    public Creature(int xPosition, int yPosition, char size, int health) {
        super(xPosition, yPosition);
        this.size = size;
        this.health = health;
        this.visible = true;
    }

    public char getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setVisiblity(boolean bool) {
        this.visible = bool;
    }

    public boolean isVisible() {
        return this.visible;
    }

}


