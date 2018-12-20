package csc221.alg.model;


public class Tree extends Entity {
    private int woodAmount;

    public Tree(int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.woodAmount = 3;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public boolean hasResources() {
        return woodAmount > 0;
    }

    public void decreaseWoodAmount(int woodAmount) {
        this.woodAmount -= woodAmount;
    }

}
