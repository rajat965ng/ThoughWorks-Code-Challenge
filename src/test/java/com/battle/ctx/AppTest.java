package com.battle.ctx;


import com.battle.entity.Board;
import com.battle.handler.IBattleBoardHandler;
import com.battle.handler.IGameHandler;
import com.battle.impl.BattleBoardHandler;
import static org.junit.Assert.*;

import com.battle.impl.GameHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class AppTest {

    Scanner sc;
    String width,height;
    Integer numOfShips;
    String input;

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
    public void test_one_initialize_game(){
        IBattleBoardHandler battleBoardHandler = new BattleBoardHandler(width,height,numOfShips);
        Board battleBoard = battleBoardHandler.init();
        assertNotNull(battleBoard);
        IGameHandler gameHandler = new GameHandler(battleBoard);

        System.out.println("Enter battleship type, dimensions (width and height) & positions (Y\n" +
                "coordinate and X coordinate) for Player-1 and then for Player-2 [separated\n" +
                "by space]:");
        setInput("Q 1 1 A1 B2");
        String inputs[] = input.split(" ");
        System.out.println(gameHandler.initialisePlayer(inputs[0],inputs[1],inputs[2],inputs[3],inputs[4]));
        setInput("P 2 1 D4 C3");
        inputs = input.split(" ");
        System.out.println(gameHandler.initialisePlayer(inputs[0],inputs[1],inputs[2],inputs[3],inputs[4]));
    }

    private void setInput(String str){
        System.out.println(str);
        input = str;
    }
}
