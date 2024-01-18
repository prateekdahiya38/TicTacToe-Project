package controller;

import models.Game;
import models.GameState;
import models.Move;
import models.Player;
import stretagies.winningStrategy.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public Game createGame(int size, List<Player> players,List<Integer> listOfWinningStrategy){
        try {
            return Game.builder()
                    .setSize(size)
                    .setPlayers(players)
                    .setWinningStrategies(WinningStrategyFactory.selectWinningStrategyForGame(listOfWinningStrategy,size))
                    .build();
        }catch (Exception e){
            System.out.println("Could not build the Game, Something went wrong");
        }
        return null;
    }

    public void displayBoard(Game game){
        game.getBoard().printBoard();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public Move executeMove(Game game, Player player){
        Move move = player.makeMove(game.getBoard());
        updateGameMoves(game,move);
        return move;
    }

    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);

    }

    public Player checkWinner(Game game, Move recentMove){
        for (WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game.getBoard(),recentMove);
            if (player != null)
                return player;
        }
        return null;
    }

    public String getWinner(Game game){
        return game.getWinner().getName();
    }
}
