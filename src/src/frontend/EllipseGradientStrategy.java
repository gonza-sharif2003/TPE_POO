package frontend;

import backend.model.MovableEllipse;
import backend.model.MovableFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.List;

public class EllipseGradientStrategy implements GradientStrategy {
    @Override
    public void applyGradient(GraphicsContext gc, MovableFigure figure, Color color) {
        MovableEllipse ellipse = (MovableEllipse) figure;
        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(1, color.invert()));
        gc.setFill(radialGradient);
        List<Double> axes = ellipse.getAxes();
        gc.strokeOval(ellipse.getCenterPoint().getX() - (axes.get(0) / 2), ellipse.getCenterPoint().getY() - (axes.get(1) / 2), axes.get(0), axes.get(1));
        gc.fillOval(ellipse.getCenterPoint().getX() - (axes.get(0) / 2), ellipse.getCenterPoint().getY() - (axes.get(1) / 2), axes.get(0), axes.get(1));
    }

    //gc.strokeOval(centerPoint.getX() - (mayorAxis / 2), centerPoint.getY() - (minorAxis / 2), mayorAxis, minorAxis);
	//	gc.fillOval(centerPoint.getX() - (mayorAxis / 2), centerPoint.getY() - (minorAxis / 2), mayorAxis, minorAxis);
}
