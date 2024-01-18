package stretagies.winningStrategy;

import exceptions.GameDrawException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RowWinningStrategy implements WinningStrategy{
    private int size;
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();

    public RowWinningStrategy(int size) {
        this.size = size;
        for (int i=0; i<size; i++){
            rowSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();


        if (checkForRowWins(row,symbol,lastMove) != null)
            return lastMovePlayer;
        return null;
    }

    private Player checkForRowWins(int row, char symbol, Move lastMove) {

        if (!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(symbol , rowSymbolCount.get(row).get(symbol)+1);

        if (rowSymbolCount.get(row).get(symbol) == size)
            return lastMove.getPlayer();
        return null;
    }
}
