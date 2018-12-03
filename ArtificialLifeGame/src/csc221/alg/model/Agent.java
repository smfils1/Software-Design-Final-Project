package csc221.alg.model;

public class Agent extends Creature{

    final private Backpack backpack;
    public Agent(int xPosition, int yPosition, char size, int health) {
        super(xPosition, yPosition, size, health);
        backpack = new Backpack();
    }

    public void hide(){
        setVisiblity(false);
    }

    public void eat(){//In the controller section press E
        //TODO: Implement
    }
    public void hunt(){//In the controller section press H
        //TODO: Implement
    }
    public void buildNewBase(){//In the controller section press B
        //TODO: Implement
    }
    public void strenghtBase(){//In the controller section press S
        //TODO: Implement
    }


    @Override
    public void move(int x, int y) {
        //TODO: Moves freely based on vision and health
        //TODO: Updates entities array

    }


}
