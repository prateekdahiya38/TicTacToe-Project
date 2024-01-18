package models;

import stretagies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name,Symbol symbol, BotDifficultyLevel botLevel, BotPlayingStrategy botPlayingStrategy) {
        super(name, PlayerType.BOT, symbol);
        this.botLevel = botLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public Move makeMove(Board board){
    Move move = botPlayingStrategy.makeMove(board,this);
    move.setPlayer(this);
    return move;
    }
    public BotDifficultyLevel getBotLevel() {
        return botLevel;
    }

    public void setBotLevel(BotDifficultyLevel botLevel) {
        this.botLevel = botLevel;
    }

    public BotPlayingStrategy getBotPlayingStretegy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
}
