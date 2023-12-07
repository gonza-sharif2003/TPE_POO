package frontend;

import backend.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorableFigure {
    private final MovableFigure figure;
    private Color color = Color.YELLOW;
    private final GraphicsContext gc;
    private boolean isSelected = false;

    private boolean drawShadow = false;

    private final Map<Class<? extends MovableFigure>, ShadowStrategy> shadowStrategies = new HashMap<>();


    public ColorableFigure(MovableFigure figure, GraphicsContext gc) {
        this.figure = figure;
        this.gc = gc;

        shadowStrategies.put(MovableRectangle.class, new RectangleShadowStrategy());
        shadowStrategies.put(MovableSquare.class, new SquareShadowStrategy());
        shadowStrategies.put(MovableCircle.class, new CircleShadowStrategy());
        shadowStrategies.put(MovableEllipse.class, new EllipseShadowStrategy());
    }

    public void draw() {
        gc.setFill(this.color);
        if (isSelected) {
            gc.setStroke(Color.RED);
        } else {
            gc.setStroke(Color.BLACK);
        }

        if (drawShadow) {
            ShadowStrategy shadowStrategy = shadowStrategies.getOrDefault(figure.getClass(), null);
            if (shadowStrategy != null) {
                shadowStrategy.applyShadow(gc, figure);
            }
        }

        DrawingVisitor drawingVisitor = new DrawingVisitor(gc, color);
        figure.accept(drawingVisitor);
    }

    public void setIsSelected() {
        isSelected = true;
    }

    public void setShadow() {
        this.drawShadow = true;
    }


    public void setColor(Color color) {
        this.color = color;
    }
}

