package models;

import exceptions.InvalidBoardDimensionException;
import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayersCountException;
import exceptions.DuplicateSymbolException;
import stretagies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Player winner;
    private List<Move> moves;
    private Board board;
    private int nextPlayerIndex;
    private GameState gameState;
    private List <WinningStrategy> winningStrategies;

    private Game(Builder builder) {
        this.players = builder.players;
        this.moves = new ArrayList<Move>();
        this.board = new Board(builder.size);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = builder.winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public static Builder builder(){

        return new Builder();
    }







   public static class Builder{
        private List<Player> players;
        private  int size;
        private List<WinningStrategy> winningStrategies;



       public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setSize(int size) {

            this.size = size;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }


       public void addPlayer(Player player){
            players.add(player);
        }
        public void addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
        }

        private void validateBotCount(){
            int botCount = 0;
            for (Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if (botCount>1){
                throw new InvalidBotCountException(" Only 1 Bot allow in a game");
            }
        }

        private void validateBoardDimension(){
            if (size < 3 || size > 10){
                throw new InvalidBoardDimensionException("Size of the board should be between 3 to 10");
            }
        }

        private void validatePlayersCount(){
            if (players.size() != size-1){
                throw new InvalidPlayersCountException("Player should be 1 lesser then the Board size");
            }
        }

        private void validateUniqueSymbolOfAllPlayers(){
            HashSet<Character> set = new HashSet<>();
            for (Player player : players) {
                set.add(player.getSymbol().getSymbolChar());
            }
            for (Player player : players){
                if (set.size() != players.size()){
                    throw new DuplicateSymbolException("Symbol of each player should be unique");
                }
            }
        }

        private void validate(){
            validateBotCount();
            validateBoardDimension();
            validatePlayersCount();
            validateUniqueSymbolOfAllPlayers();
        }

        public Game build(){
            this.validate();
            return new Game(this);
        }
    }
}
