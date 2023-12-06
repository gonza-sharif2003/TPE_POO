package backend.model;

public class Rectangle<P extends Point> extends Figure {
    protected final P topLeft, bottomRight;

    public Rectangle(P topLeft, P bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean pointBelongs(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX() &&
                point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }

    public P getTopLeft() {
        return topLeft;
    }

    public P getBottomRight() {
        return bottomRight;
    }
}
