package frontend;

import backend.model.MovableFigure;
import backend.model.MovableRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShadowStrategy implements ShadowStrategy {
    @Override
    public void applyShadow(GraphicsContext gc, MovableFigure figure) {
        MovableRectangle rectangle = (MovableRectangle) figure;
        gc.setFill(Color.GRAY);
        gc.fillRect(rectangle.getTopLeft().getX() + 10.0,
                rectangle.getTopLeft().getY() + 10.0,
                Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()),
                Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
    }
}

