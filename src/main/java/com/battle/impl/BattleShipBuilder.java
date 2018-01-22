package com.battle.impl;

import com.battle.entity.Board;
import com.battle.entity.Player;
import com.battle.entity.Ship;
import com.battle.validator.BattleValidatorStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public enum BattleShipBuilder {

    Q{
        @Override
        public BattleShipBuilder initShip(Board battleBoard, int width, int height) {
            Ship ship = new Ship(battleBoard, width, height);
            BattleValidatorStrategy.BATTLE_SHIP_DIMENSIONS.validate(ship);
            setShip(ship);
            return Q;
        }

        @Override
        public Player buildParts(Player player,String playerLocation) {
            char[] locArr = playerLocation.toCharArray();
            return createArmouredShip(2,locArr,player);
        }
    },
    P{
        @Override
        public BattleShipBuilder initShip(Board battleBoard, int width, int height) {
            Ship ship = new Ship(battleBoard, width, height);
            BattleValidatorStrategy.BATTLE_SHIP_DIMENSIONS.validate(ship);
            setShip(ship);
            return P;
        }

        @Override
        public Player buildParts(Player player,String playerLocation) {
            char[] locArr = playerLocation.toCharArray();
            return createArmouredShip(1,locArr,player);
        }
    };

    private Ship ship;
    public abstract BattleShipBuilder initShip(Board battleBoard, int width, int height);
    public abstract Player buildParts(Player player, String playerLocation);

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Player createArmouredShip(int armour,char[] locationIndex,Player player){
        Map<String,Integer> parts = new HashMap<>();
        for(int i=locationIndex[0];i<locationIndex[0]+getShip().getHeight();i++){
            if (!player.getInventory().containsKey(""+(char)i+"")){
                player.getInventory().put(""+(char)i+"",null);
            }
            int xCoordnate = Integer.parseInt(""+locationIndex[1]);
            for (int j= xCoordnate;j<xCoordnate+getShip().getWidth();j++){
                parts.put(""+j+"",armour);
            }
            getShip().setParts(parts);
            player.getInventory().put(""+(char)i+"",getShip());
        }
        return player;
    }
}
