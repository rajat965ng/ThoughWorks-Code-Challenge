package com.battle.impl;

import com.battle.entity.Board;
import com.battle.entity.Player;
import com.battle.entity.Ship;

import java.util.HashMap;
import java.util.Map;

public enum BattleShipFactory {

    Q{
        @Override
        public BattleShipFactory initShip(Board battleBoard,int width, int height) {
            setShip(new Ship(battleBoard, width, height));
            return Q;
        }

        @Override
        public Player buildParts(Player player,String playerLocation) {
            char[] locArr = playerLocation.toCharArray();
            Map<String,Integer> parts = new HashMap<>();

            for(int i=locArr[0];i<locArr[0]+getShip().getHeight();i++){
                if (!player.getInventory().containsKey(""+(char)i+"")){
                    player.getInventory().put(""+(char)i+"",null);
                }
                int xCoordnate = Integer.parseInt(""+locArr[1]);
                for (int j= xCoordnate;j<xCoordnate+getShip().getWidth();j++){
                    parts.put(""+j+"",2);
                }
                getShip().setParts(parts);
                player.getInventory().put(""+(char)i+"",getShip());
            }
            return player;
        }
    },
    P{
        @Override
        public BattleShipFactory initShip(Board battleBoard,int width, int height) {
            setShip(new Ship(battleBoard, width, height));
            return P;
        }

        @Override
        public Player buildParts(Player player,String playerLocation) {
            char[] locArr = playerLocation.toCharArray();
            Map<String,Integer> parts = new HashMap<>();
            for(int i=locArr[0];i<locArr[0]+getShip().getHeight();i++){
                if (!player.getInventory().containsKey(""+(char)i+"")){
                    player.getInventory().put(""+(char)i+"",null);
                }
                int xCoordnate = Integer.parseInt(""+locArr[1]);
                for (int j= xCoordnate;j<xCoordnate+getShip().getWidth();j++){
                    parts.put(""+j+"",1);
                }
                getShip().setParts(parts);
                player.getInventory().put(""+(char)i+"",getShip());
            }
            return player;
        }
    };

    private Ship ship;
    public abstract BattleShipFactory initShip(Board battleBoard,int width, int height);
    public abstract Player buildParts(Player player, String playerLocation);

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
