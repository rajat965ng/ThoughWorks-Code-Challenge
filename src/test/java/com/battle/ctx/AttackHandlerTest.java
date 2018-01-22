package com.battle.ctx;

import com.battle.entity.Attack;
import com.battle.entity.Player;
import com.battle.entity.Ship;
import com.battle.handler.IAttackHandler;
import com.battle.impl.AttackHandler;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class AttackHandlerTest {

    IAttackHandler attackHandler;
    Player playerOne,playerTwo;

    @Before
    public void setup(){
        attackHandler = new AttackHandler();
        playerOne = new Player("Player-1"){{
            setInventory(new HashMap<String, Ship>(){{
                Ship ship = new Ship();
                ship.setParts(new HashMap<String, Integer>(){{
                    put("1",1);
                }});
                put("A",ship);
            }});
        }};
        playerTwo = new Player("Player-2");
    }

    @Test
    public void test_attach_opponent_exist_succes(){
        assertTrue(attackHandler.isOpponentExist(playerOne));
    }


    @Test
    public void test_attach_opponent_exist_failure(){
        assertFalse(attackHandler.isOpponentExist(playerTwo));
    }

    @Test
    public void test_isAttackSuccessFull_success(){
        Ship ship = new Ship(){{
            setParts(new HashMap<String, Integer>(){{
                put("4",2);
            }});
        }};
        playerTwo.setInventory(new HashMap<String, Ship>(){{
            put("D",ship);
        }});
        Attack attack = new Attack("D","4");
       assertTrue(attackHandler.isAttackSuccessFull(attack,playerTwo));
    }


    @Test
    public void test_isAttackSuccessFull_fail(){
        Attack attack = new Attack("E","5");
        assertFalse(attackHandler.isAttackSuccessFull(attack,playerTwo));
    }

    @Test(expected = NullPointerException.class)
    public void test_loadCannon(){
        attackHandler.loadCannon(null,playerTwo);
    }

}
