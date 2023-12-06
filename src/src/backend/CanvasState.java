package backend;

import backend.model.MovableFigure;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<MovableFigure> list = new ArrayList<>();

    public void addFigure(MovableFigure figure) {
        list.add(figure);
    }

    public void deleteFigure(MovableFigure figure) {
        list.remove(figure);
    }

    public Iterable<MovableFigure> figures() {
        return new ArrayList<>(list);
    }

}
