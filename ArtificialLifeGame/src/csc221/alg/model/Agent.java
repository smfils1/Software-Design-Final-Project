package csc221.alg.model;

public class Agent extends Creature{

    final private Backpack backpack;
    public Agent(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'M', 100,2);
        backpack = new Backpack();
    }

    public void hide(){
        setVisiblity(false);
    }


    //TODO: Implement
    //In the controller section press E
    public void eat(){
    }

    //TODO: Implement
    //In the controller section press H
    public void hunt(){
    }

    //TODO: Implement
    //In the controller section press B
    public void buildNewBase(){
    }

    //TODO: Implement
    //In the controller section press S (assuming arrow keys are used to move agent)
    public void strenghtBase(){
    }


    @Override
    //Checks if entity can Move
    public boolean canMove(int x, int y) {
        //New Direction relative to current Direction
        int xDirection = x - getXPosition();
        int yDirection = y - getYPosition();
        //Vision Center
        int visionYCenter = (getVision().size() - 1) / 2;
        int visionXCenter = (getVision().get(0).size() - 1) / 2;
        boolean rule1= getHealth() > 0;//Has health
        boolean rule2= (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection) != null);//Region exists
        if( rule1 && rule2){
            boolean rule3 = (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getEntity() == null); //Region is empty
            return rule3;
        }
        return false;
    }
}
