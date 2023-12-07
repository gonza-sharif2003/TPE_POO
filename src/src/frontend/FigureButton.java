package frontend;

import javafx.scene.control.ToggleButton;

public abstract class FigureButton {
    private final ColorableFigure figure;
    private final ToggleButton button;
    private boolean isSelected;

    public FigureButton(ColorableFigure figure, ToggleButton button) {
        this.figure = figure;
        this.button = button;
    }

    public ToggleButton getButton() {
        return button;
    }

    public ColorableFigure getFigure() {
        return figure;
    }

    public boolean isSelected() {
        return isSelected;
    }

}