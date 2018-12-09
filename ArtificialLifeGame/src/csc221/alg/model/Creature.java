package csc221.alg.model;

import java.util.ArrayList;

abstract public class Creature extends Entity implements Movable {
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
    //TODO: update health
    //Moves creature if it can move
    public void moveLeft() {
        if (canMove(this.getXPosition()-1,this.getYPosition())) {
            this.setXPosition(getXPosition() - 1);
            updateVision(World.getInstance().getWorld());
        }
    }

    @Override
    //TODO: update health
    //Moves creature if it can move
    public void moveRight() {
        if (canMove(this.getXPosition()+1,this.getYPosition())) {
            this.setXPosition(getXPosition() + 1);
            updateVision(World.getInstance().getWorld());
        }
    }

    @Override
    //TODO: update health
    //Moves creature if it can move
    public void moveUp() {
        if (canMove(this.getXPosition(),this.getYPosition()-1)) {
            this.setYPosition(getYPosition() - 1);
            updateVision(World.getInstance().getWorld());
        }
    }

    @Override
    //TODO: update health
    //Moves creature if it can move
    public void moveDown() {
        if (canMove(this.getXPosition(),this.getYPosition()+1)) {
            this.setYPosition(getYPosition() + 1);
            updateVision(World.getInstance().getWorld());
        }
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


