package backend.model;

public class Square extends Figure {

    private final Point topLeft, bottomRight;

    public Square(Point topLeft, double size) {
        this.topLeft = topLeft;
        this.bottomRight = new Point(topLeft.getX() + size, topLeft.getY() + size);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void move(double diffX, double diffY) {
        topLeft.x += diffX;
        bottomRight.x += diffX;
        topLeft.y += diffY;
        bottomRight.y += diffY;
    }

}
