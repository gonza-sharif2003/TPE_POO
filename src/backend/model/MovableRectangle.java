package backend.model;

import java.util.ArrayList;
import java.util.List;

public class MovableRectangle extends Rectangle<MovablePoint> implements MovableFigure {

    public MovableRectangle(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public List<MovablePoint> getMovPoints() {
        List<MovablePoint> ans = new ArrayList<>();
        ans.add(getTopLeft()); ans.add(getBottomRight());
        return ans;
    }

    @Override
    public List<Double> getAxes()
    {
        List<Double> ans = new ArrayList<>();

        ans.add(Math.abs(getTopLeft().getX()-getBottomRight().getX()));/*Base*/
        ans.add(Math.abs(getTopLeft().getY()-getBottomRight().getY()));/*Height*/

        return ans;
    }

}