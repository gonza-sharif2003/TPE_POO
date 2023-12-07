package frontend;

import backend.model.MovableEllipse;
import backend.model.MovableFigure;
import backend.model.MovablePoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class EllipseShadowStrategy implements ShadowStrategy {
    @Override
    public void applyShadow(GraphicsContext gc, MovableFigure figure) {
        MovableEllipse ellipse = (MovableEllipse) figure;
        List<MovablePoint> movPoints = ellipse.getMovPoints();
        List<Double> axes = ellipse.getAxes();
        gc.setFill(Color.GRAY);
        gc.fillOval(movPoints.get(0).getX() - (axes.get(0) / 2) + 10.0,
                movPoints.get(0).getY() - (axes.get(1) / 2) + 10.0, axes.get(0), axes.get(1));
    }
}
