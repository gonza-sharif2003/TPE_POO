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
        ans.add(getTopLeft());
        ans.add(getBottomRight());
        return ans;
    }

    @Override
    public List<Double> getAxes() {
        List<Double> ans = new ArrayList<>();
        ans.add(Math.abs(getTopLeft().getX() - getBottomRight().getX())); // width
        ans.add(Math.abs(getTopLeft().getY() - getBottomRight().getY())); // height
        return ans;
    }

    @Override
    public boolean belongs(MovablePoint point) {
        return point.getX() > getTopLeft().getX() && point.getX() < getBottomRight().getX() &&
                point.getY() > getTopLeft().getY() && point.getY() < getBottomRight().getY();
    }

    @Override
    public void accept(FigureVisitor visitor) {
        visitor.visitRectangle(this);
    }

}
