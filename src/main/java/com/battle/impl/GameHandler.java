package com.battle.impl;

import com.battle.entity.Board;
import com.battle.entity.Game;
import com.battle.entity.Player;
import com.battle.handler.IGameHandler;

public class GameHandler implements IGameHandler {

    private Game game;
    private Board battleBoard;
    Player playerOne;
    Player playerTwo;

    public GameHandler(Board battleBoard) {
        this.battleBoard = battleBoard;
        this.game = new Game();
        this.playerOne = new Player();
        this.playerTwo = new Player();
    }

    @Override
    public Game initialisePlayer(String battleShipType, String width, String height, String playerOnePosition, String playerTwoPosition) {

        playerOne = BattleShipFactory.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerOne,playerOnePosition);
        playerTwo = BattleShipFactory.valueOf(battleShipType)
                .initShip(battleBoard,Integer.parseInt(width),Integer.parseInt(height))
                .buildParts(playerTwo,playerTwoPosition);

        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        return game;
    }
}

