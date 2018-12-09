package csc221.alg.model;

abstract public class Entity {
    private int xPosition;
    private int yPosition;


    public Entity(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

}

