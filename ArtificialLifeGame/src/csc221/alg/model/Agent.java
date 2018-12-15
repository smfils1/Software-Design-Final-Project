package csc221.alg.model;

public class Agent extends Creature{
    final private Backpack backpack;

    public Agent(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'M', 100,3);
        backpack = new Backpack();
    }

    public void hide(){
        setVisiblity(false);
    }

    public void eat(){
        //TODO: Implement
        //In the controller section press E
    }

    public void collectResource(){
        //TODO: Implement
        //In the controller section press C
    }


    public void hunt(){
        //TODO: Implement
        //In the controller section press H
    }


    public void buildNewBase(){
        //TODO: Implement
        //In the controller section press B
    }


    public void strenghtBase(){
        //TODO: Implement
        //In the controller section press S (assuming arrow keys are used to move agent)
    }

    @Override

    public void updateHealth() {
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
