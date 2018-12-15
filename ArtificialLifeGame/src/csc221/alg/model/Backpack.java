package csc221.alg.model;

//Backpack class that uses a weight system
public class Backpack {
    //TODO: We can use an enum for the constants. Do LATER.
    private final int WOOD_WEIGHT  = 8;
    private final int BERRY_WEIGHT = 2;
    private final int STEAK_WEIGHT = 4;
    private final int STONE_WEIGHT = 6;
    private final int SPEAR_WEIGHT = 5;
    private final int FLASHLIGHT_WEIGHT = 3;

    private int weight;
    private int space;

    private int limit;
    private int woodAmount;
    private int berryAmount;
    private int steakAmount;
    private int stoneAmount;
    private int spearAmount;
    private int flashlightAmount;

    public Backpack(){
        this(25,0,0,0,0,1,1);
    }

    public Backpack(int limit, int wood, int berry, int steak,int stone, int spear, int flashlight) {
        this.limit = limit;
        woodAmount = wood;
        berryAmount = berry;
        steakAmount = steak;
        stoneAmount = stone;
        spearAmount = spear;
        flashlightAmount = flashlight;

        updateWeight();
        if(weight > this.limit) {     // If initial weight exceeds limit then limit is increased to fit all items
            this.limit = weight;
        }
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public boolean addWood(int woodAmount) {
        if(woodAmount * WOOD_WEIGHT <= space) {
            this.woodAmount += woodAmount;
            updateWeight();
            return true;
        } else {
            return false;
        }
    }

    public void removeWood() {
        if(woodAmount > 0) {
            woodAmount--;
            updateWeight();
        }
    }

    public int getBerryAmount() {
        return berryAmount;
    }

    public boolean addBerry(int berryAmount) {
        if(berryAmount * BERRY_WEIGHT <= space) {
            this.berryAmount += berryAmount;
            updateWeight();
            return true;
        } else {
            return false;
        }
    }

    public void removeBerry() {
        if(berryAmount > 0) {
            berryAmount--;
            updateWeight();
        }
    }

    public int getSteakAmount() {
        return steakAmount;
    }

    public boolean addSteak(int steakAmount) {
        if(steakAmount * STEAK_WEIGHT <= space) {
            this.steakAmount += steakAmount;
            updateWeight();
            return true;
        } else {
            return false;
        }
    }

    public void removeSteak() {
        if(steakAmount > 0) {
            steakAmount--;
            updateWeight();
        }
    }

    public int getstoneAmount() {
        return stoneAmount;
    }

    public boolean addStone(int stoneAmount) {
        if(stoneAmount * STONE_WEIGHT <= space) {
            this.stoneAmount += stoneAmount;
            updateWeight();
            return true;
        } else {
            return false;
        }
    }

    public void removeStone() {
        if(stoneAmount > 0) {
            stoneAmount--;
            updateWeight();
        }
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

    public int getWeight() {                                            // ADDED BY Brian
        return weight;
    }

    public int getSpace() {                                             // ADDED BY Brian
        return space;
    }

    private void updateWeight() {
        weight =  woodAmount  * WOOD_WEIGHT
                + berryAmount * BERRY_WEIGHT
                + steakAmount * STEAK_WEIGHT
                + stoneAmount * STONE_WEIGHT
                + spearAmount * SPEAR_WEIGHT
                + flashlightAmount * FLASHLIGHT_WEIGHT;
        space = limit - weight;
    }
}
