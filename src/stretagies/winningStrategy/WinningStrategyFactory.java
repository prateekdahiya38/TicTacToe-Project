package stretagies.winningStrategy;

import exceptions.InvalidWinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningStrategyFactory {

    public static List<WinningStrategy> selectWinningStrategyForGame(List<Integer> winningStrategies,int size){
        Set<WinningStrategy> winningStrategiesForGame = new HashSet<>();
        for (int winningStrategy : winningStrategies){
            if (winningStrategy == 1){
                winningStrategiesForGame.add(new RowWinningStrategy(size));
            }else if (winningStrategy == 2){
                winningStrategiesForGame.add(new ColWinningStrategy(size));
            }else if (winningStrategy == 3){
                winningStrategiesForGame.add(new DiagonalWinningStrategy(size));
            }else if (winningStrategy == 4){
                winningStrategiesForGame.add(new CornersWinningStrategy(size));
            }else {
                throw new InvalidWinningStrategy("Please Enter the Strategy correctly as mention above");
            }
        }
        List<WinningStrategy> listOfWinningStrategy = new ArrayList<>();
        for (WinningStrategy winningStrategy : winningStrategiesForGame){
            listOfWinningStrategy.add(winningStrategy);
        }
        return listOfWinningStrategy;
    }
}
