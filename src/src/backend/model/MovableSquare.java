package backend.model;

import java.util.ArrayList;
import java.util.List;

public class MovableSquare extends Square<MovablePoint> implements MovableFigure {
    public MovableSquare(MovablePoint topLeft, double size) {
        super(topLeft, size);
    }
    @Override
    public List<MovablePoint> getMovPoints() {
        List<MovablePoint> ans = new ArrayList<>();
        ans.add(getTopLeft());
        ans.add(getBottomRight());
        return ans;
    }

    @Override
    public List<Double> getAxes() {
        List<Double> ans = new ArrayList<>();
        ans.add(Math.abs(getTopLeft().getX() - getBottomRight().getX())); // size
        return ans;
    }

}
