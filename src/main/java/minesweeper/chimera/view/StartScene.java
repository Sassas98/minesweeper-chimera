package minesweeper.chimera.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import minesweeper.chimera.model.enumeration.Difficulty;

public class StartScene extends Scene {

    private int numericInput = 5;
    private String dropdown1Selection = "EASY";

    public StartScene(StartCommand submit){
        this(new VBox(10), submit);
    }

    private StartScene(VBox root, StartCommand submit){
        super(root, 300, 100);

        TextField numericField = new TextField("5");
        numericField.setPromptText("Dimensione campo minato:");
        numericField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                numericInput = Integer.parseInt(newValue);
            } catch (NumberFormatException e) {
                numericField.setText(oldValue);
            }
        });

        ComboBox<String> dropdown1 = new ComboBox<>();
        dropdown1.getItems().addAll("EASY", "MEDIUM", "HARD", "IMPOSSIBLE");
        dropdown1.setValue("EASY");
        dropdown1.valueProperty().addListener((observable, oldValue, newValue) -> dropdown1Selection = newValue);

        // Submit button
        Button submitButton = new Button("Gioca!");
        submitButton.setOnAction(event -> {
            numericInput = numericInput < 2 ? 2 : numericInput > 20 ? 20 : numericInput;
            submit.run(numericInput, Difficulty.getDifficulty(dropdown1Selection));
        });

        root.getChildren().addAll(numericField, dropdown1, submitButton);
    }

}
