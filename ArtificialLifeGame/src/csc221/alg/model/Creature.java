package csc221.alg.model;

import java.util.ArrayList;

abstract public class Creature extends Entity implements Movable {
    private final int MAX_HEALTH = 100;                                 // ADDED BY Brian
    private char size;
    private int health;
    private boolean visible;
    private int visionRadius;

    public void setVisionReference(ArrayList<ArrayList<Region>> visionReference) {
        this.visionReference = new ArrayList<>();
        this.visionReference = visionReference;
    }

    private ArrayList<ArrayList<Region>> visionReference;

    private ArrayList<ArrayList<Region>> vision;

    public Creature(int xPosition, int yPosition, char size, int health, int visionRadius) {
        super(xPosition, yPosition);
        this.size = size;
        this.health = health;
        this.visible = true;
        this.visionRadius = visionRadius;
        updateVision(World.getInstance().getWorld());

    }

    @Override
    //TODO: add move(int xOffset, int yOffset) method header to Movable interface then restore @Override above
    //TODO: update health
    //Moves creature if it can move
    public void move(int xOffset, int yOffset) {                       // ADDED BY Brian
        if (canMove(this.getXPosition() + xOffset, this.getYPosition() + yOffset)) {
            this.setXPosition(getXPosition() + xOffset);
            this.setYPosition(getYPosition() + yOffset);
            updateVision(World.getInstance().getWorld());

            if(this instanceof Agent) {updateHealth();}                 // ADDED BY Brian

        }
    }

    @Override
    //Moves creature if it can move                                     // REVISED BY Brian
    public void moveLeft() {
        move(-1, 0);
    }

    @Override
    //Moves creature if it can move                                     // REVISED BY Brian
    public void moveRight() {
        move(1, 0);
    }

    @Override
    //Moves creature if it can move                                     // REVISED BY Brian
    public void moveUp() {
        move(0, -1);
    }

    @Override
    //Moves creature if it can move                                     // REVISED BY Brian
    public void moveDown() {
        move(0, 1);
    }

    //Updates the creatures vision of the world w/ the current square radius view
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

    //Updates the creatures vision of the world w/ a square radius view
    //May use later
    public void updateVision(ArrayList<ArrayList<Region>> world, int radius){
        vision = new ArrayList<>();
        int WorldYSize = world.size();
        int WorldXSize = world.get(0).size();
        for(int y = getYPosition() - radius; y <= getYPosition() + radius; y++) {
            ArrayList<Region> rowVision= new ArrayList<>();
            for (int x = getXPosition() - radius; x <= getXPosition() + radius; x++) {
                if ((x <= WorldXSize-1 && x >= 0) && (y <= WorldYSize-1 && y >= 0)) {
                    rowVision.add(world.get(y).get(x));
                } else {
                    rowVision.add(null);
                }
            }
            vision.add(rowVision);
        }
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

    public void decreaseHealth(int healthPoints) {                      // ADDED BY Brian
        if (healthPoints > this.health)
            this.health = 0;
        else
            this.health -= healthPoints;
    }

    public void increaseHealth(int healthPoints) {                      // ADDED BY Brian
        if (healthPoints > this.MAX_HEALTH - this.health)
            this.health = this.MAX_HEALTH;
        else
            this.health += healthPoints;
    }

    public void updateHealth() {                                        // ADDED BY Brian
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;

        char terrain = getVision().get(visionYCenter).get(visionXCenter).getTerrainType();  // Check terrain
        if(terrain == '.' || terrain == '~')
            decreaseHealth(2);      // Moving in Desert or Water decreases health by 2
        else
            decreaseHealth(1);      // Moving in Grass decreases health by 1
    }

    public void setVisiblity(boolean bool) {
        this.visible = bool;
    }

    public boolean isVisible() {
        return this.visible;
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

}
