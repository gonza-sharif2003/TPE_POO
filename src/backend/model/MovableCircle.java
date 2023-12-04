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
    public List<Double> getAxes()
    {
        List<Double> ans = new ArrayList<>();

        ans.add(getsMayorAxis());/*Radius*/

        return ans;
    }
}