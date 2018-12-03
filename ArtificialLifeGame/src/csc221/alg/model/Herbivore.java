package csc221.alg.model;

abstract public class Herbivore extends Creature {

    public Herbivore(int xPosition, int yPosition, char size, int health) {
        super(xPosition, yPosition, size, health);
    }

    @Override
    public void move(int x, int y) {
        //TODO: Moves Only on grass based on vision
        //TODO: Updates entities array
    }
}
