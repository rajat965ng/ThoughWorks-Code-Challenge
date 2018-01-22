package com.battle.impl;

import com.battle.entity.*;
import com.battle.handler.IAttackHandler;
import com.battle.handler.IGameHandler;
import com.battle.validator.BattleValidatorStrategy;

import java.util.concurrent.LinkedBlockingQueue;

public class GameHandler implements IGameHandler {

    private Game game;
    private Board battleBoard;
    Player playerOne;
    Player playerTwo;
    IAttackHandler attackHandler;

    public GameHandler(Board battleBoard) {
        this.battleBoard = battleBoard;
        this.game = new Game();
        this.playerOne = new Player("Player-1");
        this.playerTwo = new Player("Player-2");
        this.attackHandler = new AttackHandler();
    }

    @Override
    public Game initialisePlayer(String battleShipType, String width,String height,
                                 String playerOnePosition,String playerTwoPosition) {

        BattleValidatorStrategy.BATTLE_SHIP_TYPE.validate(battleShipType);
        playerOne = BattleShipBuilder.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerOne,playerOnePosition);

        BattleValidatorStrategy.BATTLE_SHIP_COORDINATES.validate(playerOne);

        playerTwo = BattleShipBuilder.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerTwo,playerTwoPosition);

        BattleValidatorStrategy.BATTLE_SHIP_COORDINATES.validate(playerTwo);

        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        BattleValidatorStrategy.BATTLE_SHIP_OVERLAP.validate(game);
        return game;
    }

    @Override
    public void armsInitializer(String[] ammos, Player player) {
        player.setCannon(new LinkedBlockingQueue<>());
        attackHandler.loadCannon(ammos,player);
    }

    @Override
    public Player attack(Player playerOne, Player playerTwo) {
        boolean success = false;
        String attacklbl = "";
        if (playerOne.getCannon().isEmpty() && playerTwo.getCannon().isEmpty()){
            System.out.println("Match Draw");
            return null;
        }
        if (playerOne.getStatus()== Status.ATTACK){
            Attack attack = playerOne.getCannon().poll();

            if (attack!=null){
                attacklbl = attack.getxCoordinate()+attack.getyCoordinate();
                success = attackHandler.isAttackSuccessFull(attack,playerTwo);
                if (success){
                    System.out.println(playerOne.getName()+": fires a missile with target "+attacklbl+" which got hit");
                    if (!attackHandler.isOpponentExist(playerTwo)){
                        playerOne.setStatus(Status.WINNER);
                        playerTwo.setStatus(Status.LOOSER);
                        System.out.println(playerOne.getName()+": Won the battle ");
                        return playerOne;
                    }
                   return attack(playerOne,playerTwo);
                }else {
                    System.out.println(playerOne.getName() + ": fires a missile with target "+attacklbl+" which got miss");
                }
            }else {
                System.out.println(playerOne.getName()+": has no more missiles left to launch ");
            }
        }
        playerOne.setStatus(Status.WAIT);
        playerTwo.setStatus(Status.ATTACK);
        return attack(playerTwo,playerOne);
    }
}

