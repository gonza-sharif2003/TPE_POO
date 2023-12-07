package backend.model;

import java.util.ArrayList;
import java.util.List;

public class MovableEllipse extends Ellipse<MovablePoint> implements MovableFigure {
    public MovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
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
        ans.add(getsMayorAxis()); // [0] = Mayor Axis
        ans.add(getsMinorAxis()); // [1] = Minor Axis

        return ans;
    }

    @Override
    public boolean belongs(MovablePoint point) {
        return ((Math.pow(point.getX() - getCenterPoint().getX(), 2) / Math.pow(getsMayorAxis(), 2)) +
                (Math.pow(point.getY() - getCenterPoint().getY(), 2) / Math.pow(getsMinorAxis(), 2))) <= 0.30;
    }

    @Override
    public void accept(FigureVisitor visitor) {
        visitor.visitEllipse(this);
    }
}