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

    public void run(int x, int y){
        int cells = map.getGridDimention()*map.getGridDimention();
        int div = diff.getBombDivisor();
        int bombNum = cells / div;
        while(bombNum >= 1){
            int a = getRandomCoordinate(), b = getRandomCoordinate();
            if(x != -1 && y != -1 && a >= x-1 && a <= x+1 && b >= y-1 && b <=y+1)
                continue;
            if(map.addBomb(a, b))
                bombNum--;
        }
        map.CountBombs();
    }

    private int getRandomCoordinate(){
        return rnd.nextInt(map.getGridDimention());
    }
    
}
