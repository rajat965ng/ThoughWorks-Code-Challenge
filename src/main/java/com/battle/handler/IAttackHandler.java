package com.battle.handler;

import com.battle.entity.Attack;
import com.battle.entity.Player;

public interface IAttackHandler {

    public void loadCannon(String[] ammos, Player player);

    public boolean isAttackSuccessFull(Attack attack,Player opponent);

}
