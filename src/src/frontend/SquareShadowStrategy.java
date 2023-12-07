package frontend;

import backend.model.MovableFigure;
import backend.model.MovableSquare;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareShadowStrategy implements ShadowStrategy {
    @Override
    public void applyShadow(GraphicsContext gc, MovableFigure figure) {
        MovableSquare square = (MovableSquare) figure;
        gc.setFill(Color.GRAY);
        gc.fillRect(square.getTopLeft().getX() + 10.0,
                square.getTopLeft().getY() + 10.0,
                Math.abs(square.getTopLeft().getX() - square.getBottomRight().getX()),
                Math.abs(square.getTopLeft().getY() - square.getBottomRight().getY()));
    }
}
