package com.battle.entity;

public class Attack {

    private String xCoordinate;
    private String yCoordinate;

    public Attack(String xCoordinate, String yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "xCoordinate='" + xCoordinate + '\'' +
                ", yCoordinate='" + yCoordinate + '\'' +
                '}';
    }
}
