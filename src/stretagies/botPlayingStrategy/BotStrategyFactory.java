package stretagies.botPlayingStrategy;

import models.BotDifficultyLevel;

public class BotStrategyFactory {

    public static BotPlayingStrategy selectBotStrategy(String level){
        BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.valueOf(level);
        return switch (botDifficultyLevel){
            case E -> new RandomBotPlayingStrategy();
            case M -> new RandomBotPlayingStrategy();
            case H -> new RandomBotPlayingStrategy();
        };
    }
}
