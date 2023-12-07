package backend.model;

public interface FigureVisitor {
    void visitRectangle(MovableRectangle rectangle);
    void visitSquare(MovableSquare square);
    void visitCircle(MovableCircle circle);
    void visitEllipse(MovableEllipse ellipse);
}
