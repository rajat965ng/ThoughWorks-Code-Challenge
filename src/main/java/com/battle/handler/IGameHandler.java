package com.battle.handler;

import com.battle.entity.Game;
import com.battle.entity.Player;

public interface IGameHandler {

    public Game initialisePlayer(String battleShipType, String width,
                                   String height, String playerOnePosition,
                                   String playerTwoPosition);

    public void loadCannon(String[] ammos, Player player);
}
