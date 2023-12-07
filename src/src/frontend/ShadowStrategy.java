package frontend;

import backend.model.MovableFigure;
import javafx.scene.canvas.GraphicsContext;

public interface ShadowStrategy {
    void applyShadow(GraphicsContext gc, MovableFigure figure);
}
