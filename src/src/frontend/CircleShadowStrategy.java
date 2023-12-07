package frontend;

import backend.model.MovableCircle;
import backend.model.MovableFigure;
import backend.model.MovablePoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class CircleShadowStrategy implements ShadowStrategy {
    @Override
    public void applyShadow(GraphicsContext gc, MovableFigure figure) {
        MovableCircle circle = (MovableCircle) figure;
        List<MovablePoint> movPoints = circle.getMovPoints();
        List<Double> axes = circle.getAxes();
        gc.setFill(Color.GRAY);
        gc.fillOval(movPoints.get(0).getX() - axes.get(0) + 10.0,
                movPoints.get(0).getY() - axes.get(0) + 10.0, axes.get(0) * 2, axes.get(0) * 2);
    }
}
