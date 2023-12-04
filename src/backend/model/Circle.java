package backend.model;
public class Circle<P extends Point> extends Ellipse<P> {

    public Circle(P centerPoint, double radius) {
        super(centerPoint, radius, radius);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getsMayorAxis());
    }

}
