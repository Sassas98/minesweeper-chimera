package minesweeper.chimera.controller;

import java.util.Random;

import minesweeper.chimera.model.GameMap;
import minesweeper.chimera.model.enumeration.Difficulty;

public class BombsAdder {
    private GameMap map;
    private Difficulty diff;
    private Random rnd;

    public BombsAdder(GameMap map, Difficulty diff) {
        this.map = map;
        this.diff = diff;
        this.rnd = new Random();
    }

    public void run(){
        int cells = map.getGridDimention()*map.getGridDimention();
        int div = diff.getBombDivisor();
        int bombNum = cells / div;
        while(bombNum >= 1){
            int x = getRandomCoordinate(), y = getRandomCoordinate();
            if(map.addBomb(x, y))
                bombNum--;
        }
    }

    private int getRandomCoordinate(){
        return rnd.nextInt(map.getGridDimention());
    }
    
}
