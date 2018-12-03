package csc221.alg.model;

public class Carnivore extends Creature {

    public Carnivore(int xPosition, int yPosition, char size, int health) {
        super(xPosition, yPosition, size, health);
    }

    public void attack(){
        //TODO: Implement
    }

    @Override
    public void move(int x, int y) {
        //TODO: Moves Only on grass & dirt based on vision
        //TODO: Updates entities array
    }
}
