package backend.model;

public class Square<P extends Point> extends Rectangle<MovablePoint> {


    public Square(MovablePoint topLeft, double size) {
        super(topLeft, new MovablePoint(topLeft.getX() + size, topLeft.getY() + size));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
