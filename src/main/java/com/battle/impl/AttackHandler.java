package com.battle.impl;

import com.battle.entity.Attack;
import com.battle.entity.Player;
import com.battle.entity.Ship;
import com.battle.handler.IAttackHandler;
import com.battle.validator.BattleValidatorStrategy;

import java.util.Arrays;

/**
 * Handler for managing attacks
 */
public class AttackHandler implements IAttackHandler {

    /**
     * Load the cannon
     *
     * @param ammos
     * @param player
     */
    @Override
    public void loadCannon(String[] ammos, Player player) {
        BattleValidatorStrategy.NULL_CHECK.validate(ammos);
        Arrays.stream(ammos).map(ammo -> {
            char[] coordinates = ammo.toCharArray();
            return new Attack(""+coordinates[0],""+coordinates[1]);
        }).forEach(attack -> {player.getCannon().add(attack);});
    }

    /**
     * Checking if the attack is Hit or Miss
     * @param attack
     * @param opponent
     *
     */
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

    /**
     * For checking if Opponent still exist
     * @param opponent
     */
    @Override
    public boolean isOpponentExist(Player opponent) {
       return opponent.getInventory().entrySet().stream()
                .anyMatch(inventory -> inventory.getValue()
                        .getParts().entrySet().stream()
                        .anyMatch(ship -> ship.getValue()>0));
    }
}
