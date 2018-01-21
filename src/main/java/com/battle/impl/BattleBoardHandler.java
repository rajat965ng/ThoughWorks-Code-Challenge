package com.battle.impl;

import com.battle.entity.Board;
import com.battle.handler.IBattleBoardHandler;

import java.util.stream.IntStream;

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

        if(IntStream.range((int) 'A',((int)'Z')+1).noneMatch(e->{
            return e==(int) height.charAt(0);
        })){
            throw new IndexOutOfBoundsException("Height can only be in between A to Z");
        }
        if(IntStream.range(1,10).noneMatch(e->{
            return e==Integer.parseInt(width);
        })){
            throw new IndexOutOfBoundsException("Width can only be in between 0 to 9");
        }
        return new Board(Integer.parseInt(width),height.charAt(0),numOfShips);
    }
}
