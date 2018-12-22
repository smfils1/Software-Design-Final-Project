package csc221.alg.model;

abstract class Carnivore extends Creature {

    public Carnivore(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'L', 100, 4);
    }

    private void attackAdjacent() { // A Carnivore can attack Agent or Base.
        if(!attackAgent()) {
            attackBase();
        }
    }

    private boolean attackAgent() { // This method allows a Carnivore to attack an Agent.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Base.)
        int x = visionXCenter;
        int y = visionYCenter;
        boolean agentFound = false;
        if(getSize() == 'M' || getSize() == 'L') { // Medium and large Carnivores can attack Agent.
            if(isAgent(visionXCenter - 1, visionYCenter)) {        // Check adjacent left for Agent.
                x--;
                agentFound = true;
            } else if(isAgent(visionXCenter + 1, visionYCenter)) { // Check adjacent right for Agent.
                x++;
                agentFound = true;
            } else if(isAgent(visionXCenter, visionYCenter - 1)) { // Check adjacent up for Agent.
                y--;
                agentFound = true;
            } else if(isAgent(visionXCenter, visionYCenter + 1)) { // Check adjacent down for Agent.
                y++;
                agentFound = true;
            }
            if(agentFound) { // Check if adjacent Agent was found.
                Region region = getVision().get(y).get(x);
                Agent agent = (Agent)(region.getEntity());
                if(agent.isVisible()) { // Check is Agent is hiding behind a Rock.
                    agent.decreaseHealth(10); // Each attack takes 10 hp from agent.
                    decreaseHealth(1);  // Attacking also decreases Carnivore's health by 1.
                }
            }
        }
        return agentFound;
    }

    private void attackBase() { // This method allows a Carnivore to attack a Base.
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        // Check adjacent left, right, up, and down for a Base.)
        int x = visionXCenter;
        int y = visionYCenter;
        boolean baseFound = false;
        if(getSize() == 'L') { // Only large Carnivores can attack Base.
            if(isBase(visionXCenter - 1, visionYCenter)) {        // Check adjacent left for Agent.
                x--;
                baseFound = true;
            } else if(isBase(visionXCenter + 1, visionYCenter)) { // Check adjacent right for Agent.
                x++;
                baseFound = true;
            } else if(isBase(visionXCenter, visionYCenter - 1)) { // Check adjacent up for Agent.
                y--;
                baseFound = true;
            } else if(isBase(visionXCenter, visionYCenter + 1)) { // Check adjacent down for Agent.
                y++;
                baseFound = true;
            }
            if(baseFound) { // Check if adjacent Base was found.
                Region region = getVision().get(y).get(x);
                Base base = (Base)(region.getEntity());
                base.decreaseStrength(5); // Each attack takes 5 strength points from Base.
                decreaseHealth(1);  // Attacking also decreases Carnivore's health by 1.
            }
        }
    }

    private void attackWithinVision() { // A Carnivore can attack Agent or Base.
        if(!attackAgentWithinVision()) {
            attackBaseWithinVision();
        }
    }

    private boolean attackAgentWithinVision() { // This method allows a Carnivore to attack an Agent.
        boolean wasAttack = false;
        // Check for agent within vision area.
        for (int y = 0; y < getVision().size(); y++) {
            for (int x = 0; x < getVision().get(0).size(); x++) {
                Region region = getVision().get(y).get(x);
                if( (getSize() == 'M' || getSize() == 'L')
                        && region != null
                        && region.getEntity() instanceof Agent
                        && ((Agent)region.getEntity()).isVisible() ) {
                    Agent agent = (Agent)(region.getEntity());
                    agent.decreaseHealth(3); // Each attack takes 3 hp from agent.
                    decreaseHealth(1);  // Attacking also decreases Carnivore's health by 1.
                    wasAttack = true;
                    break; // Stop checking the rest of the vision once an attack has been done.
                }
            }
            if (wasAttack) {break;} // Stop checking the rest of the vision once an attack has been done.
        }
        return wasAttack;
    }

    private void attackBaseWithinVision() { // This method allows a Carnivore to attack a Base.
        boolean wasAttack = false;
        // Check for base within vision area.
        for (int y = 0; y < getVision().size(); y++) {
            for (int x = 0; x < getVision().get(0).size(); x++) {
                Region region = getVision().get(y).get(x);
                if( (getSize() == 'L')
                        && region != null
                        && region.getEntity() instanceof Base
                        && ((Base)region.getEntity()).getStrength() > 0 ) {
                    Base base = (Base)(region.getEntity());
                    base.decreaseStrength(3); // Each attack takes 3 strength points from base.
                    decreaseHealth(1);  // Attacking also decreases Carnivore's health by 1.
                    wasAttack = true;
                    break; // Stop checking the rest of the vision once an attack has been done.
                }
            }
            if (wasAttack) {break;} // Stop checking the rest of the vision once an attack has been done.
        }
    }

    private boolean isAgent(int x, int y) { // Private helper method used by attack().
        Region region = getVision().get(y).get(x);
        if(region == null) {
            return false;
        } else {
            return region.getEntity() instanceof Agent;
        }
    }

    private boolean isBase(int x, int y) { // Private helper method used by attack().
        Region region = getVision().get(y).get(x);
        if(region == null) {
            return false;
        } else {
            return region.getEntity() instanceof Base;
        }
    }

    @Override
    public void updateHealth() {
        decreaseHealth(0);
        // attackAdjacent(); // attackAdjacent is disabled in favor of the attackWithinVision method.
        attackWithinVision();
    }

    @Override
    //Checks if carnivore can Move
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
            boolean rule4= getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getTerrainType() ==','; //Region is Green
            boolean rule5= getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getTerrainType() =='.'; //Region is Desert
            return rule3 && (rule4 || rule5);
        }
        return false;

    }
}
