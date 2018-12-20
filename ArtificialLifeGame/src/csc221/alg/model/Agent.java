package csc221.alg.model;

public class Agent extends Creature{
    final private Backpack backpack;

    public Agent(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'M', 100,3);
        backpack = new Backpack();
    }

    public void attack() {          // In the controller section press 'A'.
        // TODO: Test and debug
        // This method attacks a carnivore or hunts a herbivore within vision.
        boolean wasAttack = false;
        // Check for carnivore within vision area.
        for (int y = 0; y < getVision().size(); y++) {
            for (int x = 0; x < getVision().get(0).size(); x++) {
                Region region = getVision().get(y).get(x);
                if ((backpack.getSpearAmount() > 0) &&
                        (region.getEntity() instanceof Carnivore) &&
                        ((Carnivore)region.getEntity()).isAlive()) {
                    Carnivore carnivore = (Carnivore)(region.getEntity());
                    carnivore.decreaseHealth(20); // A spear attack inflicts 10 points of damage.
                    wasAttack = true;
                    break; // Stop checking the rest of the vision once an attack has been done.
                }
            }
            if (wasAttack) {break;} // Stop checking the rest of the vision once an attack has been done.
        }
        // Check for herbivore within vision area only if no carnivore was found first.
        if(!wasAttack) {
            for (int y = 0; y < getVision().size(); y++) {
                for (int x = 0; x < getVision().get(0).size(); x++) {
                    Region region = getVision().get(y).get(x);
                    if ((region.getEntity() instanceof Herbivore) &&
                            ((Herbivore)region.getEntity()).isAlive()) {
                        Herbivore herbivore = (Herbivore)(region.getEntity());
                        herbivore.decreaseHealth(20); // A spear attack inflicts 10 points of damage.back
                        if (herbivore.isDead()) {
                            backpack.addSteak(1); // An herbivore is equal to a steak.
                        }
                        wasAttack = true;
                        break; // Stop checking the rest of the vision once an attack has been done.
                    }
                }
                if (wasAttack) {
                    break; // Stop checking the rest of the vision once an attack has been done.
                }
            }
        }
        decreaseHealth(1);  // Attacking or hunting decreases agents's health by 1.
    }

    public void buildBase() {       //In the controller section press 'B'.
        //TODO: Implement
    }

    public void buildNewBase() {   // May be used by buildBase()
        //TODO: Implement
    }

    public void strengthenBase() { // May be used by buildBase()
        //TODO: Implement
    }

    public void collectResource() { // In the controller section press 'C'.
        //TODO: Test and debug
        // The method attempts to collect resources (berry, stone, wood) in 4 adjacent regions.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        collectResource(visionXCenter - 1, visionYCenter);      // Collect adjacent left.
        collectResource(visionXCenter + 1, visionYCenter);      // Collect adjacent right.
        collectResource(visionXCenter, visionYCenter - 1);      // Collect adjacent up.
        collectResource(visionXCenter, visionYCenter + 1);      // Collect adjacent down.
    }

    private void collectResource(int x, int y) {
        //TODO: Test and debug
        Region region = getVision().get(y).get(x);
        // Check for Entity (Bush, Rock, Tree) to collect resource (berry, stone, wood)
        if(region.getEntity() instanceof Bush) {
            Bush bush = (Bush)(region.getEntity());
            if(bush.hasResources()) {
                if(backpack.addBerry(1)) {      // berryAmount of 1 represents a handful of berries.
                    bush.decreaseBerryAmount(1);
                };
            }
        } else if(region.getEntity() instanceof Rock) {
            Rock rock = (Rock)(region.getEntity());
            if(rock.getSize() == 's') {                     // A stone is a small Rock.
                if(backpack.addStone(1)) {
                    // TODO: Remove stone from 'vision';
                };
            }
        } else if(region.getEntity() instanceof Tree) {     // woodAmount of 1 represents an armful of wood.
            Tree tree = (Tree)(region.getEntity());
            if(tree.hasResources()) {
                if(backpack.addWood(1)) {
                    tree.decreaseWoodAmount(1);
                };
            }
            decreaseHealth(1);  // Chopping wood decreases agents's health by 1.
        }
    }

    public void eat() {             // In the controller section press 'E'.
        //TODO: Test and debug
        // The method allows agent to eat berries or steak if any is in backpack.
        if(backpack.getBerryAmount() > 0) { // If Backpack contains berries and steak, eat berries first.
            backpack.removeBerry();
            increaseHealth(10);         // Eating berries increases health by 10 points.
        } else if(backpack.getSteakAmount() > 0) {
            backpack.removeSteak();
            increaseHealth(20);         // Eating steak increases health by 20 points.
        }
    }

    public void hide() {            // In the controller section press 'H'.
        //TODO: Test and debug
        // The method allows agent to hide if there is a Rock in one of the 4 adjacent regions.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Rock.)
        if( isRock(visionXCenter - 1, visionYCenter) ||
                isRock(visionXCenter + 1, visionYCenter) ||
                isRock(visionXCenter, visionYCenter - 1) ||
                isRock(visionXCenter, visionYCenter + 1) ) {
        setVisiblity(false); //TODO: Restore visibility once the agent starts moving again.
        }
    }

    private boolean isRock(int x, int y) { // Private helper method used by hide().
        Region region = getVision().get(y).get(x);
        return region.getEntity() instanceof Rock;
    }

    public void rest() {            // In the controller section press 'R'.
        // This method allows agent to sleep at base or rest under a tree.
        //TODO: Test and debug
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Rock.)
        if( isBase(visionXCenter - 1, visionYCenter) ||
                isBase(visionXCenter + 1, visionYCenter) ||
                isBase(visionXCenter, visionYCenter - 1) ||
                isBase(visionXCenter, visionYCenter + 1) ) {
            if(true) { // TODO: Check how long it's been since the last time agent slept at base.
                increaseHealth(20); // Resting at base increases health by 20 points.
            }
        } else if( isTree(visionXCenter - 1, visionYCenter) ||
                isTree(visionXCenter + 1, visionYCenter) ||
                isTree(visionXCenter, visionYCenter - 1) ||
                isTree(visionXCenter, visionYCenter + 1) ) {
            if(true) { // TODO: Check how long it's been since the last time agent rested under tree.
                increaseHealth(10); // Resting under a tree increases health by 10 points.
            }
        }
    }

    private boolean isTree(int x, int y) { // Private helper method used by rest().
        Region region = getVision().get(y).get(x);
        return region.getEntity() instanceof Tree;
    }

    private boolean isBase(int x, int y) { // Private helper method used by rest().
        Region region = getVision().get(y).get(x);
        return region.getEntity() instanceof Base;
    }

    @Override
    public void updateHealth() { // This method updates agent's health for each move depending on terrain.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;

        char terrain = getVision().get(visionYCenter).get(visionXCenter).getTerrainType();  // Check terrain
        if(terrain == '.' || terrain == '~')
            decreaseHealth(1);      // Moving in Desert or Water decreases health by 1
        else
            decreaseHealth(0.5);      // Moving in Grass decreases health by 0.5

        //TODO: call private function that check if agent is next to an carnivore to decrease health
    }

    @Override
    public boolean canMove(int x, int y) {
        //New Direction relative to current Direction
        int xDirection = x - getXPosition();
        int yDirection = y - getYPosition();
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        boolean rule1= getHealth() > 0; //Has health
        boolean rule2= (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection) != null); //Region exists
        if( rule1 && rule2){
            boolean rule3 = (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getEntity() == null); //Region is empty
            return rule3;
        }
        return false;
    }
}
