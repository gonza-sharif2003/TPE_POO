package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class EffectBox extends HBox {
    private final String name;

    public EffectBox(String name) {
        this.name = name;
        ToggleButton button = new ToggleButton();
        Label label = new Label(name);
        label.setMinWidth(30);
        label.setFont(new Font("Arial", 12));
        HBox effectBox = new HBox(5, button, label);
        effectBox.setAlignment(Pos.CENTER);
    }
    @Override
    public String toString() {
        return "%s".formatted(name);
    }
}
