import controller.GameController;
import exceptions.GameDrawException;
import models.*;
import stretagies.botPlayingStrategy.BotStrategyFactory;

import javax.sound.midi.Soundbank;
import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        /* Size of the board taken by the user*/
        System.out.println("Please enter the dimension of the game");
        int size = sc.nextInt();
    //---------------------------------------------------------------------------------------
        /* Ask to user that we have a Bot in this game or not*/
        System.out.println("Will there be any Bot in the game ? Y/N");
        char isBotPresent = sc.next().charAt(0);
    //---------------------------------------------------------------------------------------

        /* player list taken by user*/
        List<Player> players = new ArrayList<>();
        int playerStandardSize = size-1;
        if (isBotPresent == 'Y'){
            playerStandardSize = size-2;
        }
        for (int i=0; i<playerStandardSize; i++){
            System.out.println("What is the name of the player number : " + (i+1));
            String playerName = sc.next();
            System.out.println("What is the character symbol of the player number : " + (i+1));
            char characterSymbol = sc.next().charAt(0);

            players.add(new Player(playerName, PlayerType.HUMAN, new Symbol(characterSymbol)));
        }
    //---------------------------------------------------------------------------------------

        /* if Bot present add it in the player list*/
        if(isBotPresent == 'Y'){
            System.out.println("What is the name of the Bot : ");
            String playerName = sc.next();
            System.out.println("What is the character symbol of the Bot : ");
            char characterSymbol = sc.next().charAt(0);
            System.out.println("Please Enter the Level of the Bot as easy,medium or hard --> E/M/H : ");
            String botLevel = String.valueOf(sc.next().charAt(0));
            players.add(new Bot(playerName, new Symbol(characterSymbol),BotDifficultyLevel.valueOf(botLevel),BotStrategyFactory.selectBotStrategy(botLevel)));
        }
    //---------------------------------------------------------------------------------------

        /* Apply number of Strategies Entered by user for game*/
        System.out.println("We have currently 4 winning Conditions :ROW wise,COLUMN Wise, DIAGONAL wise, CORNERS wise");
        System.out.println("Please enter the total number of condtions you want to add: ");
        int StrategyCount = sc.nextInt();
        System.out.println("Enter 1 to add Row wise winning condition");
        System.out.println("Enter 2 to add Column wise winning condition");
        System.out.println("Enter 3 to add Diagonals wise winning condition");
        System.out.println("Enter 4 to add Corners wise winning condition");
        System.out.println("To add multiple conditions,enter space in each entry");
        List<Integer> applyWinningStrategyForGame = new ArrayList<>();
        for (int i=0; i<StrategyCount; i++){
            applyWinningStrategyForGame.add(sc.nextInt());
        }
    //---------------------------------------------------------------------------------------

        /*randomise the list for first move*/
        Collections.shuffle(players);
    //---------------------------------------------------------------------------------------

        /*create the game */
        Game game = gameController.createGame(size,players,applyWinningStrategyForGame);
    //---------------------------------------------------------------------------------------

        /* Execute the moves and check the result */
        int playerIndex = 0;
        int symbolCount = 0;
        while(game.getGameState().equals(GameState.IN_PROGRESS)) {
            System.out.println("CURRENT BOARD STATUS");
            gameController.displayBoard(game);
            if (symbolCount == (size * size)){
                throw new GameDrawException("Game is Drawn as cells are filled completely");
            }
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game,players.get(playerIndex));
            symbolCount++;
            Player winner = gameController.checkWinner(game, movePlayed);
            if (winner != null){
                gameController.displayBoard(game);
                System.out.println("Winner is: " + winner.getName());
                break;
            }
        }
    }
}