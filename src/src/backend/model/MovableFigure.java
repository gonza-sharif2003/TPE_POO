package backend.model;

import java.util.List;

public interface MovableFigure extends Movable {
    List<MovablePoint> getMovPoints();
    List<Double> getAxes();
    boolean belongs(MovablePoint point);
    void accept(FigureVisitor visitor);

    @Override
    default void move(double dx, double dy) {
        for (MovablePoint points : getMovPoints()) {
            points.move(dx, dy);
        }
    }
}
