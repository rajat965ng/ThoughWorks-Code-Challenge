package com.battle.impl;

import com.battle.entity.Attack;
import com.battle.entity.Player;
import com.battle.entity.Ship;
import com.battle.handler.IAttackHandler;

import java.util.Arrays;

public class AttackHandler implements IAttackHandler {

    @Override
    public void loadCannon(String[] ammos, Player player) {
        Arrays.stream(ammos).map(ammo -> {
            char[] coordinates = ammo.toCharArray();
            return new Attack(""+coordinates[0],""+coordinates[1]);
        }).forEach(attack -> {player.getCannon().add(attack);});
    }

    @Override
    public boolean isAttackSuccessFull(Attack attack, Player opponent) {
        boolean flag = false;
        Ship ship;
       if((ship = opponent.getInventory().get(attack.getxCoordinate()))!=null){
            if (ship.getParts().containsKey(attack.getyCoordinate())){
                Integer armour = ship.getParts().get(attack.getyCoordinate());
                if (armour>0){
                    ship.getParts().put(attack.getyCoordinate(),--armour);
                    flag = true;
                }
            }
       }
        return flag;
    }
}
