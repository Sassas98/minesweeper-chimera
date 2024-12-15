package minesweeper.chimera.model;

public class GameCell {

    private int x, y, num;
    private GameMap map;
    private boolean flagged, show, bomb;

    public GameCell(int x, int y, GameMap map) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.flagged = false;
        this.show = false;
        this.bomb = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        if(this.show) return;
        this.show = show;
        if(num == 0){
            for (int i = x-1; i < x+2; i++) {
                for (int j = y-1; j < y+2; j++) {
                    if(!map.isBomb(i, j)){
                        map.show(i, j);
                    }
                }
            }
        }
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getBombNumber(){
        return num;
    }

    public void CountBombs(){
        if(bomb) return;
        this.num = 0;
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if(this.map.isBomb(i, j)){
                    this.num++;
                }
            }
        }
    }

}
