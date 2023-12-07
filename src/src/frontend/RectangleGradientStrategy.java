package frontend;

import backend.model.MovableFigure;
import backend.model.MovablePoint;
import backend.model.MovableRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;

public class RectangleGradientStrategy implements GradientStrategy {
    @Override
    public void applyGradient(GraphicsContext gc, MovableFigure figure, Color color) {
        MovableRectangle rectangle = (MovableRectangle) figure;
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(1, color.invert()));
        gc.setFill(linearGradient);
        List<MovablePoint> movPoints = rectangle.getMovPoints();
                gc.fillRect(movPoints.get(0).getX(), movPoints.get(0).getY(),
                        Math.abs(movPoints.get(0).getX() - movPoints.get(1).getX()), Math.abs(movPoints.get(0).getY() - movPoints.get(1).getY()));
        gc.strokeRect(movPoints.get(0).getX(), movPoints.get(0).getY(),
                Math.abs(movPoints.get(0).getX() - movPoints.get(1).getX()), Math.abs(movPoints.get(0).getY() - movPoints.get(1).getY()));
    }
}
