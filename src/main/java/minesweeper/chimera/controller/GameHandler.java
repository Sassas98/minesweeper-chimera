package minesweeper.chimera.controller;

import java.time.LocalDateTime;

import minesweeper.chimera.model.GameInput;
import minesweeper.chimera.model.GameMap;
import minesweeper.chimera.model.GameOutput;
import minesweeper.chimera.model.dto.GameMapDto;
import minesweeper.chimera.model.enumeration.Difficulty;
import minesweeper.chimera.model.enumeration.GameInputType;
import minesweeper.chimera.model.enumeration.GameOutputType;

public class GameHandler {

    private Difficulty difficulty;
    private GameMap map;
    private boolean started;
    private BombsAdder adder;
    
    public GameHandler(int gridDimention, Difficulty difficulty, boolean safeFirstMove) {
        if(gridDimention < 2)
            gridDimention = 2;
        this.difficulty = difficulty;
        this.map = new GameMap(gridDimention);
        adder = new BombsAdder(map, difficulty);
        started = !safeFirstMove;
        if(started){
            adder.run(-1, -1);
        }
    }

    public int getGridDimention() {
        return map.getGridDimention();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameMapDto getMap(){
        return new GameMapDto(map);
    }

    public synchronized GameOutput execute(GameInput input){
        boolean exe = false;
        if(input.type() == GameInputType.FLAG){
            exe = map.setFlag(input.x(), input.y());
        }
        if(input.type() == GameInputType.SHOW){
            if(!started){
                started = true;
                adder.run(input.x(), input.y());
            }
            exe = map.show(input.x(), input.y());
        }
        GameOutputType t = map.isWin() ? GameOutputType.WIN
                         : map.isLoose() ? GameOutputType.LOOSE
                         : exe ? GameOutputType.EXECUTED : GameOutputType.NOT_EXECUTED;
        return new GameOutput(t, LocalDateTime.now());
    }

}
