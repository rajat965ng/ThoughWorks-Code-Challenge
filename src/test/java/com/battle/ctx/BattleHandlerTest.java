package com.battle.ctx;

import com.battle.handler.IBattleBoardHandler;
import com.battle.impl.BattleBoardHandler;
import org.junit.Test;

public class BattleHandlerTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_battle_board_out_of_bound_width() {
        IBattleBoardHandler boardHandler = new BattleBoardHandler("10","H",4);
        boardHandler.init();
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void test_battle_board_out_of_bound_height() {
        IBattleBoardHandler boardHandler = new BattleBoardHandler("7","$",4);
        boardHandler.init();
    }

    @Test
    public void test_battle_board_init_success() {
        IBattleBoardHandler boardHandler = new BattleBoardHandler("8","Z",4);
        boardHandler.init();
    }


    @Test(expected = IllegalStateException.class)
    public void test_battle_board_number_of_Ships_failure(){
        IBattleBoardHandler boardHandler = new BattleBoardHandler("8","Z",4000);
        boardHandler.init();
    }


    @Test
    public void test_battle_board_number_of_Ships_success(){
        IBattleBoardHandler boardHandler = new BattleBoardHandler("8","Z",2);
        boardHandler.init();
    }

}
