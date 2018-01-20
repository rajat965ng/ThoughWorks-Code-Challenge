package com.battle.impl;

import com.battle.entity.*;
import com.battle.handler.IAttackHandler;
import com.battle.handler.IGameHandler;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class GameHandler implements IGameHandler {

    private Game game;
    private Board battleBoard;
    Player playerOne;
    Player playerTwo;
    IAttackHandler attackHandler;

    public GameHandler(Board battleBoard) {
        this.battleBoard = battleBoard;
        this.game = new Game();
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.attackHandler = new AttackHandler();
    }

    @Override
    public Game initialisePlayer(String battleShipType, String width, String height, String playerOnePosition, String playerTwoPosition) {

        playerOne = BattleShipFactory.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerOne,playerOnePosition);
        playerOne.setName("Player-1");
        playerTwo = BattleShipFactory.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerTwo,playerTwoPosition);
        playerTwo.setName("Player-2");
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
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
                if (!attackHandler.isOpponentExist(playerTwo)){
                    System.out.println(playerOne.getName()+": Won !!");
                    return playerOne;
                }
                System.out.println(playerOne.getName()+": Successfully Hit the target "+attacklbl);
                return attack(playerOne,playerTwo);
            }}else {
                System.out.println(playerOne.getName()+": Out of ammo ");
            }
        }
        System.out.println(playerOne.getName()+": Miss the target "+attacklbl);
        playerOne.setStatus(Status.WAIT);
        playerTwo.setStatus(Status.ATTACK);
        return attack(playerTwo,playerOne);
    }
}

