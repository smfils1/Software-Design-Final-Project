package csc221.alg.model;
//TODO: add Functions should should check limit
public class Backpack {
    private int limit;
    private int woodAmount;
    private int berryAmount;
    private int steakAmount;
    private int stoneAmount;
    private int spearAmount;
    private int flashlightAmount;

    public Backpack(){
        this(25,10,0,0,0,1,1);
    }

    public Backpack(int limit, int woodAmount, int berryAmount, int steakAmount, int rockAmount, int spear, int flashlight) {

        this.limit = limit;
        this.woodAmount = woodAmount;
        this.berryAmount = berryAmount;
        this.steakAmount = steakAmount;
        this.stoneAmount = stoneAmount;
        this.spearAmount = spear;
        this.flashlightAmount = flashlight;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public void addWood(int woodAmount) {
        this.woodAmount = woodAmount;
    }

    public int getBerryAmount() {
        return berryAmount;
    }

    public void addBerry(int berryAmount) {
        this.berryAmount += berryAmount;
    }

    public int getSteakAmount() {
        return steakAmount;
    }

    public void addSteak() {
        this.steakAmount ++;
    }

    public int getstoneAmount() {
        return stoneAmount;
    }

    public void addStone() {
        this.stoneAmount ++;
    }

    public int getSpearAmount() {
        return spearAmount;
    }

    public int getFlashlightAmount() {
        return flashlightAmount;
    }

    public int getLimit() {
        return limit;
    }
}
