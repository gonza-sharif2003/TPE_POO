package backend.model;

public class MovablePoint extends Point implements Movable {
    public MovablePoint(double x, double y) {
        super(x, y);
    }
    @Override
    public void move(double diffX, double diffY){
        x += diffX;
        y += diffY;
    }
}
