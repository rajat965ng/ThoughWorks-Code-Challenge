package com.battle.entity;

import java.util.HashMap;
import java.util.Map;

public class Player {


    private Status status;
    private Map<String,Ship> inventory;

    public Player() {
        this.inventory = new HashMap<>();
        this.status = Status.PLAYABLE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, Ship> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Ship> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Player{" +
                "status=" + status +
                ", inventory=" + inventory +
                '}';
    }
}
