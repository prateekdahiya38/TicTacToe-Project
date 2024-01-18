package stretagies.winningStrategy;

import exceptions.GameDrawException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy{
    private int size;
    private HashMap<Character,Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> topRightDiagonalSymbolCount = new HashMap<>();

    public DiagonalWinningStrategy(int size) {
        this.size = size;
    }

    public boolean isCellTopLeftDiagonal(int row, int col){
        return row == col;
    }

    public boolean isCellTopRightDiagonal(int row, int col){
        return (row + col) == size-1;
    }
    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();


        if (checkForDiagonalWins(row,col,symbol,lastMove) != null)
            return lastMovePlayer;
        return null;
    }

    private Player checkForDiagonalWins(int row, int col,char symbol, Move lastMove){
        /* this is for Top Left Diagonal */

        if (isCellTopLeftDiagonal(row,col)){
            if (!topLeftDiagonalSymbolCount.containsKey(symbol)){
                topLeftDiagonalSymbolCount.put(symbol,0);
            }
            topLeftDiagonalSymbolCount.put(symbol , topLeftDiagonalSymbolCount.get(symbol)+1);

            if (topLeftDiagonalSymbolCount.get(symbol) == size)
                return lastMove.getPlayer();

        }

        /* this is for Top Right Diagonal */

        if (isCellTopRightDiagonal(row,col)){
            if (!topRightDiagonalSymbolCount.containsKey(symbol)){
                topRightDiagonalSymbolCount.put(symbol,0);
            }
            topRightDiagonalSymbolCount.put(symbol , topRightDiagonalSymbolCount.get(symbol)+1);

            if (topRightDiagonalSymbolCount.get(symbol) == size)
                return lastMove.getPlayer();

        }
        return null;

    }
}
