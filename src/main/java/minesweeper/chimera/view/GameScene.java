package minesweeper.chimera.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import minesweeper.chimera.controller.GameHandler;
import minesweeper.chimera.model.GameInput;
import minesweeper.chimera.model.GameOutput;
import minesweeper.chimera.model.dto.GameCellDto;
import minesweeper.chimera.model.dto.GameMapDto;
import minesweeper.chimera.model.enumeration.Difficulty;
import minesweeper.chimera.model.enumeration.GameInputType;
import minesweeper.chimera.model.enumeration.GameOutputType;

public class GameScene extends Scene {

    private GameHandler handler;
    private boolean end;
    private Button[][] buttons;
    private Label label;

    public GameScene(Difficulty diff, int n, Runnable back, StartCommand reset){
        this(new BorderPane(), diff, n, back, reset);
    }

    private GameScene(BorderPane root, Difficulty diff, int n, Runnable back, StartCommand reset){
        super(root, 40*n < 300 ? 300 : 40*n, 40*n+50);
        this.handler = new GameHandler(n, diff);
        GridPane grid = new GridPane();
        buttons = new Button[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                Button button = new Button();
                button.setStyle("-fx-background-radius: 0;");
                buttons[row][col] = button;
                button.setPrefSize(40, 40);
                int x = row;
                int y = col;
                button.setOnMouseClicked(event -> {
                    if(end) return;
                    boolean isShow = event.getButton().toString().equals("PRIMARY");
                    GameInput input = new GameInput(x, y, isShow ? GameInputType.SHOW : GameInputType.FLAG);
                    GameOutput output = handler.execute(input);
                    if(output.type() != GameOutputType.NOT_EXECUTED){
                        update(output.type());
                    }
                });
                grid.add(button, col, row);
            }
        }
        label = new Label();
        GameMapDto map = handler.getMap();
        label.setText("Hai a disposizione " + map.FlagNum + " bandiere.");
        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(event -> reset.run(n, diff));
        resetBtn.setStyle("-fx-margin-left: 10px;");
        Button backBtn = new Button("Indietro");
        backBtn.setOnAction(event -> back.run());
        backBtn.setStyle("-fx-margin-right: 10px;");
        root.setCenter(grid);
        BorderPane comands = new BorderPane();
        comands.setLeft(backBtn);
        comands.setCenter(label);
        comands.setRight(resetBtn);
        root.setBottom(comands);
    }

    private void update(GameOutputType out){
        String color = "#2F4F4F";
        GameMapDto map = handler.getMap();
        if(out == GameOutputType.LOOSE || out == GameOutputType.WIN){
            label.setText(out == GameOutputType.LOOSE ? "Hai perso" : "Hai vinto");
            end = true;
            color = out == GameOutputType.LOOSE ? "red;" : "blue;";
        }else{
            label.setText("Hai a disposizione " + map.FlagNum + " bandiere.");
        }
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                GameCellDto cell = map.cells[i][j];
                if(cell.flag()){
                    buttons[i][j].setText("\u2691");
                }else if(cell.show()){
                    buttons[i][j].setStyle("-fx-background-radius: 0;-fx-background-color: " + color + "; -fx-text-fill: white;");
                    buttons[i][j].setText(cell.num() == 0 ? "" : cell.num() + "");
                }else{
                    buttons[i][j].setText("");
                }
                if(out == GameOutputType.LOOSE && cell.bomb()){
                    buttons[i][j].setStyle("-fx-background-radius: 0;-fx-background-color: " + color + "; -fx-text-fill: white;");
                    buttons[i][j].setText("\u2B1C");
                }
            }
        }
    }

}
