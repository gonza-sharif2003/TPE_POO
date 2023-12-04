package backend.model;

public abstract class Figure {
    Boolean[] effectsAr = {false, false, false};
    public void setShadow() {
        effectsAr[0] = true;
    }
    public void setGradient() {
        effectsAr[1] = true;
    }
    public void setBeveled() {
        effectsAr[2] = true;
    }
    public Boolean[] getEffects() {
        return effectsAr;
    }
    public abstract void move(double diffX, double diffY);


}
