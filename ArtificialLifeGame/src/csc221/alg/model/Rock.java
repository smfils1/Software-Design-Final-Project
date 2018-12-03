package csc221.alg.model;

public class Rock extends Entity {
    private char size;
    public Rock(int xPosition, int yPosition, char size) {
        super(xPosition, yPosition);
        this.size = size;
    }

    public char getSize() {
        return size;
    }
}
