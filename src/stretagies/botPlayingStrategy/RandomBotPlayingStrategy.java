package stretagies.botPlayingStrategy;

import models.*;

import java.util.Random;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    private Random random;
    @Override
    public Move makeMove(Board board, Player player) {
        random = new Random();
        for (int i=0; i< (board.getSize() * board.getSize()); i++){
            int row = random.nextInt(0, board.getSize());
            int col = random.nextInt(0, board.getSize());
            Cell currentCell = board.getBoard().get(row).get(col);
            if (currentCell.getCellState().equals(CellState.EMPTY)) {
                currentCell.setPlayer(player);
                currentCell.setCellState(CellState.FILLED);
                return new Move(new Cell(row,col),player);
            }
        }
        return null;
    }
}
