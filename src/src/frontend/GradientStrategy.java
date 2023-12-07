package frontend;

import backend.model.MovableFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface GradientStrategy {
    void applyGradient(GraphicsContext gc, MovableFigure figure, Color color);
}
