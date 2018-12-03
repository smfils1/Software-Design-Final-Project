package csc221.alg.model;

public class Base extends Entity {
    private int strength;

    public Base(int xPosition, int yPosition) {
        this(xPosition, yPosition, 100);
    }

    public Base(int xPosition, int yPosition, int strength) {
        super(xPosition, yPosition);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void increaseStrength(int strength) {
        this.strength += strength;
    }

    public void decreaseStrength(int strength) {
        this.strength -= strength;
    }

}
