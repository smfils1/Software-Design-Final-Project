package csc221.alg.model;

public class Bush extends Entity {

    public Bush(int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.berryAmount = 10;
    }
    private int berryAmount;

    public boolean hasResources() {
        return berryAmount > 0;
    }
    public int getBerryAmount() {
        return berryAmount;
    }
    public void setBerryAmount(int berryAmount) {
        this.berryAmount = berryAmount;
    }


}
