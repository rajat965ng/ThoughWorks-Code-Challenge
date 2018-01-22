package com.battle.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Player {

    private String name;
    private Status status;
    private Map<String,Ship> inventory;
    private Queue<Attack> cannon;

    public Player(String name) {
        this.name = name;
        this.inventory = new HashMap<>();
        this.status = Status.PLAYABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Queue<Attack> getCannon() {
        return cannon;
    }

    public void setCannon(Queue<Attack> cannon) {
        this.cannon = cannon;
    }

    @Override
    public String toString() {
        return "Player{" +
                "status=" + status +
                ", inventory=" + inventory +
                '}';
    }
}
