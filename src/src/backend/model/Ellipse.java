package backend.model;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected final double  sMinorAxis, sMayorAxis;
    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return "Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]".formatted(centerPoint, getsMayorAxis(), sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }
    @Override
    public void move(double diffX, double diffY) {
        centerPoint.x += diffX;
        centerPoint.y += diffY;
    }

}
