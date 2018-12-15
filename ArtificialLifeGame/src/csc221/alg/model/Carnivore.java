package csc221.alg.model;

abstract class Carnivore extends Creature {

    public Carnivore(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'L', 100, 4);
    }

    public void attack(){
        //TODO: Implement based on Carnivore's vision
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
