package stretagies.winningStrategy;

import exceptions.GameDrawException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColWinningStrategy implements WinningStrategy{
    private int size;
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();

    public ColWinningStrategy(int size) {
        this.size = size;
        for (int i=0; i<size; i++){
            colSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int col = lastMove.getCell().getCol();


        if (checkForColWins(col,symbol,lastMove) != null)
            return lastMovePlayer;
        return null;
    }

    private Player checkForColWins(int col, char symbol, Move lastMove){

        if (!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(symbol , colSymbolCount.get(col).get(symbol)+1);

        if (colSymbolCount.get(col).get(symbol) == size)
            return lastMove.getPlayer();
        return null;
    }
}
