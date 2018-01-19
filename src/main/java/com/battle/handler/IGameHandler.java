package com.battle.handler;

import com.battle.entity.Game;

public interface IGameHandler {

    public Game initialisePlayer(String battleShipType, String width,
                                   String height, String playerOnePosition,
                                   String playerTwoPosition);

}
