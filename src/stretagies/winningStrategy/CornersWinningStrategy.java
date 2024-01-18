package stretagies.winningStrategy;

import exceptions.GameDrawException;
import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class CornersWinningStrategy implements WinningStrategy{
    private int size;
    private int symbolCount;
    private HashMap<Character,Integer> cornersSymbolCount = new HashMap<>();

    public CornersWinningStrategy(int size) {
        this.size = size;
    }

    public boolean isCornerCell(int row, int col){
        if (row == 0 || row == size-1){
            return (col == 0 || col == size-1);
        }
        return false;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();


        if (checkForCornersWins(row,col,symbol,lastMove) != null)
            return lastMovePlayer;
        return null;
    }

    private Player checkForCornersWins(int row, int col, char symbol, Move lastMove){
        if (isCornerCell(row,col)){
            if (!cornersSymbolCount.containsKey(symbol)){
                cornersSymbolCount.put(symbol,0);
            }
            cornersSymbolCount.put(symbol , cornersSymbolCount.get(symbol)+1);

            if (cornersSymbolCount.get(symbol) == 4)
                return lastMove.getPlayer();

        }
        return null;
    }
}
