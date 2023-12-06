package frontend;

import javafx.scene.control.ToggleButton;

public abstract class FigureButton {
    private final ColoreableFigure figure;
    private final ToggleButton button;
    private boolean isSelected;

    public FigureButton(ColoreableFigure figure, ToggleButton button) {
        this.figure = figure;
        this.button = button;
    }

    public ToggleButton getButton() {
        return button;
    }

    public ColoreableFigure getFigure() {
        return figure;
    }

    public boolean isSelected() {
        return isSelected;
    }

}