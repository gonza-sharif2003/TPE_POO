package frontend;

import backend.model.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

import java.util.List;

public class DrawingVisitor implements FigureVisitor {
    private final GraphicsContext gc;
    private final Color color;
    private boolean shadow;
    private boolean gradient;
    private boolean beveled;
    public DrawingVisitor(GraphicsContext gc, Color color) {
        this.gc = gc;
        this.color = color;
    }

    @Override
    public void visitRectangle(MovableRectangle rectangle) {
        if (gradient) {
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, color),
                    new Stop(1, color.invert()));
            gc.setFill(linearGradient);
            List<Double> axes = rectangle.getAxes();
            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
            gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
        } else {
            gc.setFill(color);
            List<Double> axes = rectangle.getAxes();
            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
            gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), axes.get(0), axes.get(1));
        }
    }

    @Override
    public void visitSquare(MovableSquare square) {
        if (gradient) {
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, color),
                    new Stop(1, color.invert()));
            gc.setFill(linearGradient);
            List<Double> axes = square.getAxes();
            gc.fillRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
            gc.strokeRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
        } else {
            gc.setFill(color);
            List<Double> axes = square.getAxes();
            gc.fillRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
            gc.strokeRect(square.getTopLeft().getX(), square.getTopLeft().getY(), axes.get(0), axes.get(0));
        }
    }

    @Override
    public void visitCircle(MovableCircle circle) {
        if (gradient) {
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, color),
                    new Stop(1, color.invert()));
            gc.setFill(radialGradient);
            List<Double> axes = circle.getAxes();
            gc.fillOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
            gc.strokeOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
        } else {
            gc.setFill(color);
            List<Double> axes = circle.getAxes();
            gc.fillOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
            gc.strokeOval(circle.getCenterPoint().getX() - axes.get(0), circle.getCenterPoint().getY() - axes.get(0), axes.get(0) * 2, axes.get(0) * 2);
        }
    }

    @Override
    public void visitEllipse(MovableEllipse ellipse) {
        if (gradient) {
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, color),
                    new Stop(1, color.invert()));
            gc.setFill(radialGradient);
            List<Double> axes = ellipse.getAxes();
            gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
            gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        } else {
            gc.setFill(color);
            List<Double> axes = ellipse.getAxes();
            gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
            gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        }
    }
}
