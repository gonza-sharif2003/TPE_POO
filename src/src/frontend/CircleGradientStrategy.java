package frontend;

import backend.model.MovableCircle;
import backend.model.MovableFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.List;

public class CircleGradientStrategy implements GradientStrategy {
    @Override
    public void applyGradient(GraphicsContext gc, MovableFigure figure, Color color) {
        MovableCircle circle = (MovableCircle) figure;
        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(1, color.invert()));
        gc.setFill(radialGradient);
        List<Double> axes = circle.getAxes();
        gc.fillOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
        gc.strokeOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
    }
}
