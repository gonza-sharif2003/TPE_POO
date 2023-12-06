package backend.model;

import java.util.Objects;

public abstract class Figure {
    public abstract boolean pointBelongs(Point point);

    public static Integer currentId = 1;
    private final Integer ID = currentId++;

    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Figure that
                && ID.equals(that.ID));
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}
