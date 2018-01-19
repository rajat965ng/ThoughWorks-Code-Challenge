package com.battle.impl;

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
    public Board init() {
        return new Board(Integer.parseInt(width),height.charAt(0),numOfShips);
    }
}
