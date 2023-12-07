package backend.model;

import java.util.ArrayList;
import java.util.List;

public class MovableCircle extends Circle<MovablePoint> implements MovableFigure{
    public MovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public List<MovablePoint> getMovPoints() {

        List<MovablePoint> ans = new ArrayList<>();

        ans.add(getCenterPoint());

        return ans;
    }

    @Override
    public List<Double> getAxes() {
        List<Double> ans = new ArrayList<>();
        ans.add(getsMayorAxis()); // Radius
        return ans;
    }

    @Override
    public boolean belongs(MovablePoint point) {
        List<Double> axes = getAxes();
        return Math.sqrt(Math.pow(getCenterPoint().getX() - point.getX(), 2) +
                Math.pow(getCenterPoint().getY() - point.getY(), 2)) < getsMayorAxis();
    }

    @Override
    public void accept(FigureVisitor visitor) {
        visitor.visitCircle(this);
    }
}