package csc221.alg.model;

public class Bush extends Entity {
    private int berryAmount;

    public Bush(int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.berryAmount = 2;
    }

    public boolean hasResources() {
        return berryAmount > 0;
    }

    public int getBerryAmount() {
        return berryAmount;
    }

    public void setBerryAmount(int berryAmount) {
        this.berryAmount = berryAmount;
    }

    public void decreaseBerryAmount(int berryAmount) {
        this.berryAmount -= berryAmount;
    }

}
