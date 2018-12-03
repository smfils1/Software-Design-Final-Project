package csc221.alg.model;

public class Tree extends Entity {
    private int woodAmount;

    public Tree(int xPosition, int yPosition, int woodAmount) {
        super(xPosition, yPosition);
        this.woodAmount = woodAmount;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public void decreaseWoodAmount(int woodAmount) {
        this.woodAmount -= woodAmount;
    }

}
