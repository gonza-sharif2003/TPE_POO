package frontend;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class EffectBar extends HBox {

    public EffectBar() {
        // Crear el contenedor para cada efecto
        HBox sombra = createEffectBox("Sombra");
        HBox gradiente = createEffectBox("Gradiente");
        HBox biselado = createEffectBox("Biselado");

        Label effectLabel = new Label("Efectos: ");
        effectLabel.setFont(new Font("Arial", 12));

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #999; -fx-border-color: #999; -fx-border-width: 1px;");
        this.getChildren().addAll(effectLabel, sombra, gradiente, biselado);
    }
    private HBox createEffectBox(String effectName) {
        ToggleButton button = new ToggleButton();
        button.setPrefSize(5, 5);

        // Crear la etiqueta con el nombre del efecto
        Label label = new Label(effectName);
        label.setMinWidth(30);
        label.setFont(new Font("Arial", 12));

        HBox effectBox = new HBox(5, button, label);
        effectBox.setAlignment(Pos.CENTER);
        return effectBox;
    }
}
