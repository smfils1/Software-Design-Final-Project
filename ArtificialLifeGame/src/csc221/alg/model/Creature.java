package csc221.alg.model;

import java.util.ArrayList;

abstract public class Creature extends Entity implements Movable {
    private char size;
    private double health;
    private boolean visible;
    private int visionRadius;
    private final int MAX_HEALTH = 100;
    private ArrayList<ArrayList<Region>> vision;
    //TODO: May have a speed that relates to how many times it can move a second. Implement LATER

    public Creature(int xPosition, int yPosition, char size, int health, int visionRadius) {
        super(xPosition, yPosition);
        this.size = size;
        this.health = health;
        this.visible = true;
        this.visionRadius = visionRadius;
        updateVision(World.getInstance().getWorld());
    }

    @Override
    //Moves creature if it can move
    public void moveLeft() {
        move(-1, 0);
    }

    @Override
    //Moves creature if it can move
    public void moveRight() {
        move(1, 0);
    }

    @Override
    //Moves creature if it can move
    public void moveUp() {
        move(0, -1);
    }

    @Override
    //Moves creature if it can move
    public void moveDown() {
        move(0, 1);
    }

    private void move(int xOffset, int yOffset) {
        if (canMove(this.getXPosition() + xOffset, this.getYPosition() + yOffset)) {
            this.setXPosition(getXPosition() + xOffset);
            this.setYPosition(getYPosition() + yOffset);
            updateVision(World.getInstance().getWorld());
            updateHealth();
        }
    }

    public char getSize() {
        return size;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(double healthPoints) {
        if (healthPoints > this.health)
            this.health = 0;
        else
            this.health -= healthPoints;
    }

    public void increaseHealth(double healthPoints) {
        if (healthPoints > this.MAX_HEALTH - this.health)
            this.health = this.MAX_HEALTH;
        else
            this.health += healthPoints;
    }

    //Default health updates for all Creatures
    public void updateHealth() {
        decreaseHealth(0.5);
    }

    public void setVisiblity(boolean bool) {
        this.visible = bool;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isDead() {
        return this.health == 0;
    }

    public boolean isAlive() {
        return this.health != 0;
    }

    public ArrayList<ArrayList<Region>> getVision() {
        return vision;
    }

    public int getVisionRadius() {
        return visionRadius;
    }

    public void setVisionRadius(int visionRadius) {
        this.visionRadius = visionRadius;
    }

    //Updates the creatures vision of the world w/ a square radius view
    public void updateVision(ArrayList<ArrayList<Region>> world){
        int WorldYSize = world.size();
        int WorldXSize = world.get(0).size();
        vision = new ArrayList<>();
        for(int y = getYPosition() - visionRadius; y <= getYPosition() + visionRadius; y++) {
            ArrayList<Region> rowVision= new ArrayList<>();
            for (int x = getXPosition() - visionRadius; x <= getXPosition() + visionRadius; x++) {
                if ((x <= WorldXSize - 1 && x >= 0) && (y <= WorldYSize -1 && y >= 0)) {
                    rowVision.add(world.get(y).get(x));
                } else {
                    rowVision.add(null);
                }
            }
            vision.add(rowVision);
        }
    }

}
