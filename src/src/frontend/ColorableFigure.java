package frontend;

import backend.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

import java.util.List;

public class ColorableFigure {
    private final MovableFigure figure;
    private Color color = Color.YELLOW;
    private final GraphicsContext gc;
    private boolean isSelected = false;

    public ColorableFigure(MovableFigure figure, GraphicsContext gc) {
        this.figure = figure;
        this.gc = gc;
    }

    public void draw() {
        gc.setFill(this.color);
        if (isSelected) {
            gc.setStroke(Color.RED);
        } else {
            gc.setStroke(Color.BLACK);
        }

        // Dibuja la figura según el tipo de MovableFigure
        if (figure instanceof MovableRectangle) {
            drawRectangle((MovableRectangle) figure);
        } else if (figure instanceof MovableCircle) {
            drawCircle((MovableCircle) figure);
        } else if (figure instanceof MovableSquare) {
            drawSquare((MovableSquare) figure);
        } else if (figure instanceof MovableEllipse) {
            drawEllipse((MovableEllipse) figure);
        }

    }

    private void drawRectangle(MovableRectangle rectangle) {
        List<Double> axes = rectangle.getAxes();
        // Obtiene las coordenadas y dimensiones del rectángulo y dibuja en el canvas
        gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
        gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
    }

    private void drawCircle(MovableCircle circle) {
        List<Double> axes = circle.getAxes();
        // Obtiene las coordenadas y el radio del círculo y dibuja en el canvas
        gc.fillOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
        gc.strokeOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
    }

    private void drawSquare(MovableSquare square) {
        List<Double> axes = square.getAxes();
        gc.fillRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
        gc.strokeRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
    }

    private void drawEllipse(MovableEllipse ellipse) {
        List<Double> axes = ellipse.getAxes();
        // Obtiene las coordenadas y el radio del círculo y dibuja en el canvas
        gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
    }

    public void setIsSelected() {
        isSelected = true;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

