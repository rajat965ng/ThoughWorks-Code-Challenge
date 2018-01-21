package com.battle.ctx;


import com.battle.entity.Board;
import com.battle.entity.Game;
import com.battle.entity.Player;
import com.battle.entity.Status;
import com.battle.handler.IBattleBoardHandler;
import com.battle.handler.IGameHandler;
import com.battle.impl.BattleBoardHandler;
import com.battle.impl.GameHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AppTest {

    Scanner sc;
    String width,height;
    Integer numOfShips;
    String input;
    Game game;
    IGameHandler gameHandler;
    @Before
    public void setup(){
        System.out.println("Enter Dimensions of Battle Area:");
        setInput("5 E");
        System.out.println("Dimensions: "+ Arrays.toString(input.split(" ")));
        String[] dimension =  input.split(" ");
        width = dimension[0]; height = dimension[1];
        System.out.println("Enter number of Battle Ships:");
        numOfShips = 2;
        System.out.println(numOfShips);
    }

    @Test
    public void test_1_initialize_game() {
        IBattleBoardHandler battleBoardHandler = new BattleBoardHandler(width,height,numOfShips);
        Board battleBoard = battleBoardHandler.init();
        assertNotNull(battleBoard);
        gameHandler = new GameHandler(battleBoard);

        System.out.println("Enter battleship type, dimensions (width and height) & positions (Y\n" +
                "coordinate and X coordinate) for Player-1 and then for Player-2 [separated\n" +
                "by space]:");
        setInput("Q 1 1 A1 B2");
        String inputs[] = input.split(" ");
        game =  gameHandler.initialisePlayer(inputs[0],inputs[1],inputs[2],inputs[3],inputs[4]);
        assertTrue(game.getPlayerOne().getInventory().get("A").getParts().containsKey("1"));
        assertTrue(game.getPlayerTwo().getInventory().get("B").getParts().containsKey("2"));
        setInput("P 2 1 D4 C3");
        inputs = input.split(" ");
        game = gameHandler.initialisePlayer(inputs[0],inputs[1],inputs[2],inputs[3],inputs[4]);
        assertTrue(game.getPlayerOne().getInventory().get("D").getParts().containsKey("4"));
        assertTrue(game.getPlayerTwo().getInventory().get("C").getParts().containsKey("3"));

        assertEquals(2,game.getPlayerOne().getInventory().size());
        assertEquals(2,game.getPlayerTwo().getInventory().size());

        System.out.println("Attack Plan by Player One:");
        setInput("A1 B2 B2 B3");
        String[] playerOne = input.split(" ");
        gameHandler.armsInitializer(playerOne,game.getPlayerOne());
        assertEquals(4,game.getPlayerOne().getCannon().size());
        System.out.println("Attack Plan by Player Two:");
        setInput("A1 B2 B3 A1 D1 E1 D4 D4 D5 D5");
        String[] playerTwo = input.split(" ");
        gameHandler.armsInitializer(playerTwo,game.getPlayerTwo());
        assertEquals(10,game.getPlayerTwo().getCannon().size());
        game.getPlayerOne().setStatus(Status.ATTACK);
        Player winner = gameHandler.attack(game.getPlayerOne(),game.getPlayerTwo());
        assertEquals(game.getPlayerTwo().getName(),winner.getName());
    }


    private void setInput(String str){
        System.out.println(str);
        input = str;
    }
}
