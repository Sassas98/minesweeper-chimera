package minesweeper.chimera;

import javafx.application.Application;
import javafx.stage.Stage;
import minesweeper.chimera.model.enumeration.Difficulty;
import minesweeper.chimera.view.GameScene;
import minesweeper.chimera.view.StartScene;

public class App extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Campo Fiorito");
        reset();
        primaryStage.show();
    }

    private void play (int dim, Difficulty diff){
        stage.setScene(new GameScene(diff, dim, () -> reset(), (a, b) -> play(a, b)));
    }

    private void reset(){
        stage.setScene(new StartScene((dim, diff) -> play(dim, diff)));
    }

    public static void run(String[] args) {
        launch();
    }
}
