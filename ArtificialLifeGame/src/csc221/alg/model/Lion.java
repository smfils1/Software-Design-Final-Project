package csc221.alg.model;

//Not much differ from an Herbivore Object
//TODO: Maybe add an img path String field w/ an getter
public class Lion extends Carnivore {
    public Lion(int xPosition, int yPosition) {
        super(xPosition, yPosition, 'L', 100);
    }
}
