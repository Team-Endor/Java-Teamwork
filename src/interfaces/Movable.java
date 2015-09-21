package interfaces;

public interface Movable {
    boolean isMovingUp();

    boolean isMovingDown();

    boolean isMovingLeft();

    boolean isMovingRight();

    int getVelocity();

    void move(int deltaX, int deltaY);
}
