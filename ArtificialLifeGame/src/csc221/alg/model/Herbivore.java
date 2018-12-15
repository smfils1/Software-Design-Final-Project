package csc221.alg.model;

abstract public class Herbivore extends Creature {

    public Herbivore(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'S', 100, 2);
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

        boolean rule1= getHealth() > 0;//Has health
        boolean rule2= (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection) != null); //Region exists
        if( rule1 && rule2){
            boolean rule3 = (getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getEntity() == null); //Region is empty
            boolean rule4= getVision().get(visionYCenter + yDirection).get(visionXCenter + xDirection).getTerrainType() ==','; //Region is Green

            return rule3 && rule4 ;
        }
        return false;
    }
}
