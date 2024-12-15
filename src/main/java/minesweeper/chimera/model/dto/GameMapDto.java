package minesweeper.chimera.model.dto;

import minesweeper.chimera.model.GameMap;

public class GameMapDto {

    public final GameCellDto[][] cells;
    public final int FlagNum;

    public GameMapDto(GameMap map){
        this.FlagNum = map.getFlagNum();
        int dim = map.getGridDimention();
        cells = new GameCellDto[dim][dim];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                boolean bomb = map.isBomb(i, j);
                boolean show = map.isShow(i, j);
                boolean flag = map.isFlagged(i, j);
                int num = map.getCellNum(i, j);
                cells[i][j] = new GameCellDto(flag, show, bomb, num);
            }
        }
    }

}
