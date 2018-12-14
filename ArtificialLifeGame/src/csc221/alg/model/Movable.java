package csc221.alg.model;

public interface Movable {
    boolean canMove(int x, int y);
    void move(int xOffset, int yOffset);
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
}
