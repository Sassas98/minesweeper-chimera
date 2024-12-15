package minesweeper.chimera;

import javafx.application.Application;
import javafx.stage.Stage;
import minesweeper.chimera.view.GameOptions;
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

    private void play (GameOptions opt){
        stage.setScene(new GameScene(opt, () -> reset(), (o) -> play(o)));
    }

    private void reset(){
        stage.setScene(new StartScene((opt) -> play(opt)));
    }

    public static void run(String[] args) {
        launch();
    }
}
