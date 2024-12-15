package minesweeper.chimera.model;

public class GameMap {

    private int gridDimention;
    private GameCell[][] cells;
    private int flagNum, notBombNum;
    private boolean win, loose;

    public GameMap(int gridDimention) {
        this.gridDimention = gridDimention;
        cells = new GameCell[gridDimention][gridDimention];
        resetMatrix();
        flagNum = 0;
        notBombNum = gridDimention*gridDimention;
        win = false;
        loose = false;
    }

    private void resetMatrix(){
        for (int i = 0; i < gridDimention; i++) {
            for (int j = 0; j < gridDimention; j++) {
                cells[i][j] = new GameCell(i, j, this);            
            }
        }
    }

    public int getGridDimention() {
        return gridDimention;
    }

    public boolean addBomb(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        if(cell.isBomb())
            return false;
        cell.setBomb(true);
        flagNum++;
        notBombNum--;
        return true;
    }

    public void CountBombs(){
        for (int i = 0; i < gridDimention; i++) {
            for (int j = 0; j < gridDimention; j++) {
                cells[i][j].CountBombs();
            }
        }
    }

    public boolean isBomb(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        return cell.isBomb();
    }

    public int getFlagNum(){
        return flagNum;
    }

    public boolean setFlag(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        if(cell.isFlagged()){
            cell.setFlagged(false);
            flagNum++;
            return true;
        } else {
            if(cell.isShow() || flagNum < 1)
                return false;
            cell.setFlagged(true);
            flagNum--;
            return true;
        }
    }

    public boolean isFlagged(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        return cell.isFlagged();
    }

    public boolean isShow(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        return cell.isShow();
    }

    public boolean show(int x, int y){
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return false;
        GameCell cell = cells[x][y];
        if(cell.isFlagged() || cell.isShow())
            return false;
        cell.setShow(true);
        if(cell.isBomb())
            loose = true;
        else{
            notBombNum--;
            if(notBombNum < 1)
                win = true;
        }
        return true;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLoose() {
        return loose;
    }

    public int getCellNum(int x, int y) {
        if(x < 0 || y < 0 || x >= gridDimention || y >= gridDimention)
            return 0;
        GameCell cell = cells[x][y];
        return cell.getBombNumber();
    }

}
