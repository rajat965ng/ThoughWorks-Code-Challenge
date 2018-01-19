package com.battle.entity;

import java.util.Map;

public class Ship {

    private Board battleBoard;
    private int width;
    private int height;
    private Map<String,Integer> parts;

    public Ship(Board battleBoard, int width, int height) {
        this.battleBoard = battleBoard;
        this.width = width;
        this.height = height;
    }

    public Board getBattleBoard() {
        return battleBoard;
    }

    public void setBattleBoard(Board battleBoard) {
        this.battleBoard = battleBoard;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<String, Integer> getParts() {
        return parts;
    }

    public void setParts(Map<String, Integer> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "battleBoard=" + battleBoard +
                ", width=" + width +
                ", height=" + height +
                ", parts=" + parts +
                '}';
    }
}
