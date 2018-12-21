package csc221.alg.model;

public class Agent extends Creature {
    final private Backpack backpack;
    private boolean resting;

    public Agent(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'M', 100,3);
        resting = false;
        backpack = new Backpack();
    }

    public void attack() {          // In the controller section press 'A'.
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
                    carnivore.decreaseHealth(20); // A spear attack inflicts 20 points of damage.
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
                        if(backpack.getSpearAmount() > 0) {
                            herbivore.decreaseHealth(20); // A spear attack inflicts 20 points of damage.
                            wasAttack = true;
                        } else if(herbivore.getSize() == 'S') { // Can only attack with hands on Small herbivores.
                            herbivore.decreaseHealth(10); // A hand attack inflicts 10 points of damage.
                            wasAttack = true;
                        }
                        if (herbivore.isDead()) {
                            switch(herbivore.getSize()) {
                                case 'L':
                                    if(backpack.addSteak(3)) { // A Large herbivore is equal to 3 steaks.
                                        break; // If 3 steaks don't fit in backpack then try to fit 2 steaks.
                                    }
                                case 'M':
                                    if(backpack.addSteak(2)) { // A Medium herbivore is equal to 2 steaks.
                                        break; // If 2 steaks don't fit in backpack then try to fit 1 steak.
                                    }
                                case 'S':
                                    backpack.addSteak(1); // A Small herbivore is equal to 1 steak.
                                    break;
                            }
                        }
                        if(wasAttack){
                            break; // Stop checking the rest of the vision once an attack has been done.
                        }
                    }
                }
                if (wasAttack) {
                    break; // Stop checking the rest of the vision once an attack has been done.
                }
            }
        }
        decreaseHealth(1);  // Attacking or hunting decreases agents's health by 1.
    }

    public void buildBase() {       // In the controller section press 'B'.
        // This method builds up the Base if agent has wood and is next to the Base.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Base.)
        int x = visionXCenter;
        int y = visionYCenter;
        if(backpack.getWoodAmount() > 0) {  // Must have some wood in order to build up base.
            boolean baseFound = false;
            if(isBase(visionXCenter - 1, visionYCenter)) {        // Check adjacent left for Base.
                x--;
                baseFound = true;
            } else if(isBase(visionXCenter + 1, visionYCenter)) { // Check adjacent right for Base.
                x++;
                baseFound = true;
            } else if(isBase(visionXCenter, visionYCenter - 1)) { // Check adjacent up for Base.
                y--;
                baseFound = true;
            } else if(isBase(visionXCenter, visionYCenter + 1)) { // Check adjacent down for Base.
                y++;
                baseFound = true;
            }
            if(baseFound) { // Check if adjacent Base was found.
                Region region = getVision().get(y).get(x);
                Base base = (Base)(region.getEntity());
                base.increaseStrength(5); // Each unit of wood increases Base strength by 5 points.
                backpack.removeWood();    // Remove a unit of wood from backpack after it's used.
                decreaseHealth(1);  // Building decreases agents's health by 1.
            }
        }
    }

    public void collectResource() { // In the controller section press 'C'.
        // This method attempts to collect resources (berry, stone, wood) in 4 adjacent regions.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        collectResource(visionXCenter - 1, visionYCenter);      // Collect adjacent left.
        collectResource(visionXCenter + 1, visionYCenter);      // Collect adjacent right.
        collectResource(visionXCenter, visionYCenter - 1);      // Collect adjacent up.
        collectResource(visionXCenter, visionYCenter + 1);      // Collect adjacent down.
    }

    private void collectResource(int x, int y) {
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
            if(rock.getSize() == 'S') {                     // A stone is a small Rock.
                if(backpack.addStone(1)) {
                    // TODO: Remove stone from 'vision' and/or 'world';
                };
            }
        } else if(region.getEntity() instanceof Tree) {     // woodAmount of 1 represents an armful of wood.
            Tree tree = (Tree)(region.getEntity());
            if(tree.hasResources()) {
                if(backpack.addWood(1)) {
                    tree.decreaseWoodAmount(1);
                };
            }
        }
        decreaseHealth(1);  // Collecting resources decreases agents's health by 1.
    }

    public void eat() {             // In the controller section press 'E'.
        // The method allows agent to eat berries or steak if any is in backpack.
        // TODO: Check if enough time elapsed since last time agent ate.
        if(backpack.getBerryAmount() > 0) { // If Backpack contains berries and steak, eat berries first.
            backpack.removeBerry();
            increaseHealth(10);         // Eating berries increases health by 10 points.
        } else if(backpack.getSteakAmount() > 0) {
            backpack.removeSteak();
            increaseHealth(20);         // Eating steak increases health by 20 points.
        }
    }

    public void hide() {            // In the controller section press 'H'.
        // The method allows agent to hide if there is a Rock in one of the 4 adjacent regions.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Rock.)
        if( isRock(visionXCenter - 1, visionYCenter) ||
                isRock(visionXCenter + 1, visionYCenter) ||
                isRock(visionXCenter, visionYCenter - 1) ||
                isRock(visionXCenter, visionYCenter + 1) ) {
        setVisiblity(false);        // Visibility will be restored in the updateHealth() method once agent moves again.
        }
    }

    public void rest() {            // In the controller section press 'R'.
        // This method allows agent to sleep at base or rest under a tree.
        // TODO: Check if enough time elapsed since last time agent rested.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Base or Tree.)
        if( isBase(visionXCenter - 1, visionYCenter) || isBase(visionXCenter + 1, visionYCenter)
                || isBase(visionXCenter, visionYCenter - 1) || isBase(visionXCenter, visionYCenter + 1) ) {
            increaseHealth(20); // Resting at base increases health by 20 points.
        } else if( isTree(visionXCenter - 1, visionYCenter) || isTree(visionXCenter + 1, visionYCenter)
                || isTree(visionXCenter, visionYCenter - 1) || isTree(visionXCenter, visionYCenter + 1) ) {
            increaseHealth(10); // Resting under a tree increases health by 10 points.
        }
    }

    public boolean isResting() {
        return resting;
    }

    private boolean isBase(int x, int y) { // Private helper method used by buildBase() and rest().
        Region region = getVision().get(y).get(x);
        return region.getEntity() instanceof Base;
    }

    private boolean isRock(int x, int y) { // Private helper method used by hide().
        Region region = getVision().get(y).get(x);
        return (region.getEntity() instanceof Rock) && (((Rock)region.getEntity()).getSize() == 'L');
    }

    private boolean isTree(int x, int y) { // Private helper method used by rest().
        Region region = getVision().get(y).get(x);
        return region.getEntity() instanceof Tree;
    }

    @Override
    public void updateHealth() { // This method updates agent's health for each move depending on terrain.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;

        char terrain = getVision().get(visionYCenter).get(visionXCenter).getTerrainType();  // Check terrain
        if(terrain == '.' || terrain == '~') {
            decreaseHealth(1);          // Moving in Desert or Water decreases agent's health by 1
        } else {
            decreaseHealth(0.5);        // Moving in Grass decreases agent's health by 0.5
        }
        setVisiblity(true); // Restores visibility if agent was currently hiding behind a Rock.
        resting = false;    // Agent cannot be resting while moving!

        /*TODO: call private function that check if agent is next to an carnivore to decrease health (this may need to be implemented somewhere else
        because I think updateHealth() is only called after the agent moves, but carnivores should be able to attack the agent even when he's standing still).
         */
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

    public Backpack getBackpack(){
        return backpack;
    }

}
