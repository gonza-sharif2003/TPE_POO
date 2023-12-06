package frontend;

import backend.CanvasState;
import backend.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPane extends BorderPane {

	private static final int INITIAL_DIM = 3;

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color defaultFillColor = Color.YELLOW;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	ToggleButton circleButton = new ToggleButton("Círculo");
	ToggleButton squareButton = new ToggleButton("Cuadrado");
	ToggleButton ellipseButton = new ToggleButton("Elipse");
	ToggleButton deleteButton = new ToggleButton("Borrar");
	ToggleButton groupButton = new ToggleButton("Agrupar");
	ToggleButton ungroupButton = new ToggleButton("Desagrupar");
	ToggleButton turnRightButton = new ToggleButton("Girar D");
	ToggleButton horizontalButton = new ToggleButton("Voltear H");
	ToggleButton verticalButton = new ToggleButton("Voltear V");
	ToggleButton biggerButton = new ToggleButton("Escalar +");
	ToggleButton smallerButton = new ToggleButton("Escalar -");
	CheckBox shadowButton = new CheckBox();
	CheckBox beveledButton = new CheckBox();
	CheckBox gradientButton = new CheckBox();

	// Selector de color de relleno
	ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);

	// Dibujar una figura
	MovablePoint startPoint;
	MovablePoint endPoint;
	boolean isSelecting = false;

	// Seleccionar una figura
	MovableFigure selectedFigure;

	// Selección múltiple
	List<MovableFigure> selectedFigures = new ArrayList<>();

	// StatusBar
	StatusPane statusPane;

	// Colores de relleno de cada figura
	Map<MovableFigure, Color> figureColorMap = new HashMap<>();
	Map<ToggleButton, MovableFigure> figureMap = new HashMap<>();
	ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton, groupButton, ungroupButton, turnRightButton, horizontalButton, verticalButton, biggerButton, smallerButton};

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(fillColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		HBox effectBar = new HBox();
		effectBar.getChildren().add(new Label("Efectos: "));
		effectBar.getChildren().add(shadowButton);
		effectBar.getChildren().add(new Label("Sombra"));
		effectBar.getChildren().add(gradientButton);
		effectBar.getChildren().add(new Label("Gradiente"));
		effectBar.getChildren().add(beveledButton);
		effectBar.getChildren().add(new Label("Biselado"));

		effectBar.setAlignment(Pos.CENTER);
		effectBar.setSpacing(10);
		effectBar.setStyle("-fx-background-color: #999; -fx-border-color: #999; -fx-border-width: 1px;");

		canvas.setOnMousePressed(event -> {
			startPoint = new MovablePoint(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			endPoint = new MovablePoint(event.getX(), event.getY());
			if (startPoint == null) {
				return;
			}
			if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return;
			}

			figureMap.put(rectangleButton, new MovableRectangle(startPoint, endPoint));
			figureMap.put(squareButton, new MovableSquare(startPoint, Math.abs(endPoint.x - startPoint.x)));
			figureMap.put(circleButton, new MovableCircle(startPoint, Math.abs(endPoint.x - startPoint.x)));
			figureMap.put(ellipseButton, new MovableEllipse(new MovablePoint(Math.abs(endPoint.x + startPoint.x) / 2, (Math.abs((endPoint.y + startPoint.y)) / 2)), Math.abs(endPoint.getX() - startPoint.getX()), Math.abs(endPoint.getY() - startPoint.getY())));

			ToggleButton selectedButton = getSelectedButton();

			MovableFigure newFigure = figureMap.getOrDefault(selectedButton, null);
			if (newFigure == null) {
				return;
			}
			figureColorMap.put(newFigure, fillColorPicker.getValue());
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			MovablePoint eventPoint = new MovablePoint(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for (MovableFigure figure : canvasState.figures()) {
				if (figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if (found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				MovablePoint eventPoint = new MovablePoint(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (MovableFigure figure : canvasState.figures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if (selectionButton.isSelected()) {

				double diffX = event.getX() - startPoint.getX();
				double diffY = event.getY() - startPoint.getY();

				for (MovableFigure figure : canvasState.figures()) {
					if (figure == selectedFigure) {
						figure.move(diffX, diffY);
					}
				}
				startPoint = new MovablePoint(event.getX(), event.getY());
				redrawCanvas();
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setTop(effectBar);
		setRight(canvas);
	}

	private ToggleButton getSelectedButton() {
		for (ToggleButton button : toolsArr) {
			if (button.isSelected()) {
				return button;
			}
		}
		return null;
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (MovableFigure figure : canvasState.figures()) {
			ColoreableFigure newColoreableFigure = new ColoreableFigure(figure, gc);
			newColoreableFigure.setColor(figureColorMap.get(figure));
			if (selectedFigure == figure) {
				newColoreableFigure.setIsSelected();
			}
			newColoreableFigure.draw();
		}
	}

	private void applyEffects1(Boolean[] effects, Color color, Point topLeft, Point bottomRight) {
        if (effects[0]) {
			gc.setFill(Color.GRAY);
			gc.fillRect(topLeft.getX() + 10.0,
					topLeft.getY() + 10.0,
					Math.abs(topLeft.getX() - bottomRight.getX()),
					Math.abs(topLeft.getY() - bottomRight.getY()));
		}
		gc.setFill(color);
		if (effects[1]) {
			LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
					CycleMethod.NO_CYCLE,
					new Stop(0, color),
					new Stop(1, color.invert()));
			gc.setFill(linearGradient);
		}
		gc.fillRect(topLeft.getX(), topLeft.getY(),
			Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
		gc.strokeRect(topLeft.getX(), topLeft.getY(),
			Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
	}

	private void applyEffects2(Boolean[] effects, Color color, Point centerPoint, double minorAxis, double mayorAxis) {
		if (effects[0]) {
			gc.setFill(Color.GRAY);
			gc.fillOval(centerPoint.getX() - (mayorAxis / 2) + 10.0,
					centerPoint.getY() - (minorAxis / 2) + 10.0, mayorAxis, minorAxis);
		}
		gc.setFill(color);
		if (effects[1]) {
			RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
					CycleMethod.NO_CYCLE,
					new Stop(0, color),
					new Stop(1, color.invert()));
			gc.setFill(radialGradient);
		}
		gc.strokeOval(centerPoint.getX() - (mayorAxis / 2), centerPoint.getY() - (minorAxis / 2), mayorAxis, minorAxis);
		gc.fillOval(centerPoint.getX() - (mayorAxis / 2), centerPoint.getY() - (minorAxis / 2), mayorAxis, minorAxis);
	}

	private boolean figureBelongs(MovableFigure figure, MovablePoint eventPoint) {
		boolean found = false;
		if (figure instanceof Rectangle rectangle) {
			found = eventPoint.getX() > rectangle.getTopLeft().getX() && eventPoint.getX() < rectangle.getBottomRight().getX() &&
					eventPoint.getY() > rectangle.getTopLeft().getY() && eventPoint.getY() < rectangle.getBottomRight().getY();
		} else if (figure instanceof Circle circle) {
			found = Math.sqrt(Math.pow(circle.getCenterPoint().getX() - eventPoint.getX(), 2) +
					Math.pow(circle.getCenterPoint().getY() - eventPoint.getY(), 2)) < circle.getsMayorAxis();
		} else if (figure instanceof Square square) {
			found = eventPoint.getX() > square.getTopLeft().getX() && eventPoint.getX() < square.getBottomRight().getX() &&
					eventPoint.getY() > square.getTopLeft().getY() && eventPoint.getY() < square.getBottomRight().getY();
		} else if (figure instanceof Ellipse ellipse) {
			// Nota: Fórmula aproximada. No es necesario corregirla.
			found = ((Math.pow(eventPoint.getX() - ellipse.getCenterPoint().getX(), 2) / Math.pow(ellipse.getsMayorAxis(), 2)) +
					(Math.pow(eventPoint.getY() - ellipse.getCenterPoint().getY(), 2) / Math.pow(ellipse.getsMinorAxis(), 2))) <= 0.30;
		}
		return found;
	}

	private boolean figureBelongsInRectangle(MovableFigure figure, Rectangle<MovablePoint> rectangle) {
		return figureBelongs(figure, rectangle.getTopLeft()) || figureBelongs(figure, rectangle.getBottomRight());
	}

}
