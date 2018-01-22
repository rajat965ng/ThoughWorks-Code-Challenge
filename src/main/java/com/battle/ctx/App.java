package com.battle.ctx;

import com.battle.entity.Board;
import com.battle.entity.Game;
import com.battle.entity.Player;
import com.battle.entity.Status;
import com.battle.handler.IBattleBoardHandler;
import com.battle.handler.IGameHandler;
import com.battle.impl.BattleBoardHandler;
import com.battle.impl.GameHandler;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Battle Ship Game
 * @author Rajat Nigam
 *
 */
public class App
{
    private String input;

    public App(){
    }

    private void setInput(String str){
        input = str;
    }

    public static void main( String[] args )
    {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Dimensions of Battle Area, X(1-9), Y(A-Z):");
        app.setInput(sc.nextLine());
        if (app.input!=null && app.input.split(" ").length==2) {
            System.out.println("Dimensions: " + Arrays.toString(app.input.split(" ")));
            String[] dimension = app.input.split(" ");
            String width = dimension[0];
            String height = dimension[1];
            System.out.println("Enter number of Battle Ships:");
            app.setInput(sc.nextLine());
            boolean isNum = app.input.chars().allMatch(Character::isDigit);
            if(!isNum){
                System.out.println("Enter valid number of Battle Ships");
                System.exit(0);
            }
            int numOfShips = Integer.parseInt(app.input);
            IBattleBoardHandler battleBoardHandler = new BattleBoardHandler(width,height,numOfShips);
            Board battleBoard = battleBoardHandler.init();
            IGameHandler gameHandler = new GameHandler(battleBoard);
            try {
                System.out.println("Enter battleship type, dimensions (width and height) & positions (Y\n" +
                        "coordinate and X coordinate) for Player-1 and then for Player-2 [separated\n" +
                        "by space]:");
                Game game = null;
                for (int i = 0; i < numOfShips; i++) {
                    app.setInput(sc.nextLine());
                    String inputs[] = app.input.split(" ");
                    game = gameHandler.initialisePlayer(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]);
                }
                if (game != null) {
                    System.out.println("Attack Plan by Player One:");
                    app.setInput(sc.nextLine());
                    String[] playerOne = app.input.split(" ");
                    gameHandler.armsInitializer(playerOne, game.getPlayerOne());
                    System.out.println("Attack Plan by Player Two:");
                    app.setInput(sc.nextLine());
                    String[] playerTwo = app.input.split(" ");
                    gameHandler.armsInitializer(playerTwo, game.getPlayerTwo());
                    game.getPlayerOne().setStatus(Status.ATTACK);
                    Player winner = gameHandler.attack(game.getPlayerOne(), game.getPlayerTwo());
                    if (winner != null) {
                        System.out.println(winner.getName() + ":" + winner.getStatus());
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                sc.close();
            }
        }else {
            System.out.println("Please, Enter valid dimensions");
            System.exit(0);
        }
    }
}
