package com.battle.impl;

import com.battle.validator.BattleValidatorStrategy;
import com.battle.entity.Board;
import com.battle.handler.IBattleBoardHandler;

public class BattleBoardHandler implements IBattleBoardHandler {

    private String width,height;
    private Integer numOfShips;

    public BattleBoardHandler(String width, String height, Integer numOfShips) {
        this.width = width;
        this.height = height;
        this.numOfShips = numOfShips;
    }

    @Override
    public Board init(){

        BattleValidatorStrategy.BATTLE_AREA_HEIGHT.validate(height);
        BattleValidatorStrategy.BATTLE_AREA_WIDTH.validate(width);
        Board board = new Board(Integer.parseInt(width),height.charAt(0),numOfShips);
        BattleValidatorStrategy.BATTLE_BOARD_SHIP_NUM.validate(board);
        return board;
    }
}
