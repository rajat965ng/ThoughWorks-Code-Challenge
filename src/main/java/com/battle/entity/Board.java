package com.battle.entity;

public class Board {

    private int width;
    private char height;
    private int nosOfBattleShip;

    public Board(int width, char height, int nosOfBattleShip) {
        this.width = width;
        this.height = height;
        this.nosOfBattleShip = nosOfBattleShip;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char getHeight() {
        return height;
    }

    public void setHeight(char height) {
        this.height = height;
    }

    public int getNosOfBattleShip() {
        return nosOfBattleShip;
    }

    public void setNosOfBattleShip(int nosOfBattleShip) {
        this.nosOfBattleShip = nosOfBattleShip;
    }

    @Override
    public String toString() {
        return "Board{" +
                "width=" + width +
                ", height=" + height +
                ", nosOfBattleShip=" + nosOfBattleShip +
                '}';
    }
}
