package com.battle.ctx;

import com.battle.entity.Board;
import com.battle.entity.Game;
import com.battle.handler.IGameHandler;
import com.battle.impl.GameHandler;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class GameHandlerTest {

    IGameHandler gameHandler;

    @Before
    public void setup(){
        gameHandler = new GameHandler(new Board(7,'D',20));
    }

    @Test
    public void test_initialisePlayer_success(){
      Game game = gameHandler.initialisePlayer("Q","3","4",
                 "A2","A5");
      assertNotNull(game);
    }

    @Test(expected = NoSuchElementException.class)
    public void test_initialisePlayer_invalid_ship_type(){
        Game game = gameHandler.initialisePlayer("H","3","4",
                "A2","A5");
        assertNotNull(game);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_initialisePlayer_invalid_Height(){
        Game game = gameHandler.initialisePlayer("Q","5","5",
                "A2","A5");
        assertNotNull(game);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void test_initialisePlayer_invalid_Width(){
        Game game = gameHandler.initialisePlayer("Q","8","4",
                "A2","A5");
        assertNotNull(game);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_initialisePlayer_invalid_Y_Coordinates(){
        Game game = gameHandler.initialisePlayer("Q","3","4",
                "A2","B5");
        assertNotNull(game);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_initialisePlayer_invalid_X_Coordinates(){
        Game game = gameHandler.initialisePlayer("Q","3","4",
                "A2","A6");
        assertNotNull(game);
    }

    @Test(expected = IllegalStateException.class)
    public void test_initialisePlayer_battleShip_overlap(){
        Game game = gameHandler.initialisePlayer("Q","3","3",
                "A2","B3");
        assertNotNull(game);
    }

}
