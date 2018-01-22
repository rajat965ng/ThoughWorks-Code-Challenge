package com.battle.validator;

import com.battle.entity.Board;
import com.battle.entity.Game;
import com.battle.entity.Player;
import com.battle.entity.Ship;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BattleValidatorStrategy {
    BATTLE_AREA_HEIGHT{
        @Override
        public void validate(Object input) {
            NULL_CHECK.validate(input);
            if(IntStream.range((int) 'A',((int)'Z')+1).noneMatch(e->{
                return e==(int) (((String)input).charAt(0));
            })){
                throw new IndexOutOfBoundsException("Height can only be in between A to Z");
            }
        }
    },
    BATTLE_AREA_WIDTH{
        @Override
        public void validate(Object input) {
            if(IntStream.range(1,10).noneMatch(e->{
                return e==Integer.parseInt((String) input);
            })){
                throw new IndexOutOfBoundsException("Width can only be in between 0 to 9");
            }
        }
    },
    BATTLE_SHIP_HEIGHT{
        @Override
        public void validate(Object input) {
            Ship ship = (Ship) input;
            Board board = ship.getBattleBoard();
            int height = ship.getHeight();
            if(!(height<=(((int)board.getHeight()-(int)'A')+1) && height>=1)){
                throw new IndexOutOfBoundsException("Ship Height can only be in between 1 and "+(((int)board.getHeight()-(int)'A')+1));
            }
        }
    },
    BATTLE_SHIP_WIDTH{
        @Override
        public void validate(Object input) {
            Ship ship = (Ship) input;
            Board board = ship.getBattleBoard();
            int width = ship.getWidth();
            if(!(width<=board.getWidth() && width>=1)){
                throw new IndexOutOfBoundsException("Ship Width can only be in between 1 and "+board.getWidth());
            }
        }
    },
    BATTLE_SHIP_DIMENSIONS{
        @Override
        public void validate(Object input) {
            Ship ship = (Ship) input;
            BATTLE_SHIP_HEIGHT.validate(ship);
            BATTLE_SHIP_WIDTH.validate(ship);
        }
    },
    BATTLE_SHIP_Y_COORD{
        @Override
        public void validate(Object input) {
            Player player = (Player) input;
            for (Map.Entry<String,Ship> entry:player.getInventory().entrySet()){
                String height = entry.getKey();
                int upperlimit = (int)entry.getValue().getBattleBoard().getHeight();
                if (!((int)height.charAt(0)<=upperlimit && (int)height.charAt(0)>=(int)'A')){
                    throw new IndexOutOfBoundsException("Y Coordinate of Ship is beyond board height "+upperlimit);
                }
            }
        }
    },
    BATTLE_SHIP_X_COORD{
        @Override
        public void validate(Object input) {
            Player player = (Player) input;
            for (Map.Entry<String,Ship> entry:player.getInventory().entrySet()){
                Ship ship = entry.getValue();
                int upperlimit = ship.getBattleBoard().getWidth();
                for (String xCoord:ship.getParts().keySet()){
                    if (!(Integer.parseInt(xCoord)<=upperlimit && Integer.parseInt(xCoord)>=1)){
                        throw new IndexOutOfBoundsException("X Coordinate of Ship is beyond board width "+
                                upperlimit);
                    }
                }
            }
        }
    },
    BATTLE_SHIP_COORDINATES{
        @Override
        public void validate(Object input) {
            Player player = (Player) input;
            BATTLE_SHIP_X_COORD.validate(player);
            BATTLE_SHIP_Y_COORD.validate(player);
        }
    },
    BATTLE_BOARD_SHIP_NUM{
        @Override
        public void validate(Object input) {
            NULL_CHECK.validate(input);
            Board board = (Board) input;
            NULL_CHECK.validate(board.getNosOfBattleShip());

            int upperLimit = (((int) board.getHeight()-(int)('A'))+1)*board.getWidth();
            if (!(board.getNosOfBattleShip()<=upperLimit && board.getNosOfBattleShip()>=1)){
                throw new IllegalStateException("Number of Ships:"+board.getNosOfBattleShip()+" cannot exceed "+upperLimit);
            }
        }
    },
    BATTLE_SHIP_TYPE{
        @Override
        public void validate(Object input) {
            NULL_CHECK.validate(input);
            String type = (String)input;
            if(Arrays.binarySearch(new String[]{"P","Q"},type)<0){
                throw new NoSuchElementException("The BattleShip type can only of 'P' or 'Q'");
            }
        }
    },
    BATTLE_SHIP_OVERLAP{
        @Override
        public void validate(Object input) {
            Game game = (Game) input;
            Player pOne = game.getPlayerOne();
            Player pTwo = game.getPlayerTwo();

            List<String> unionArea = new ArrayList<>();

            for (Map.Entry<String,Ship> entry: pOne.getInventory().entrySet()){
                entry.getValue().getParts().forEach((k,v)->{
                    unionArea.add(entry.getKey()+k);
                });
            }
            for (Map.Entry<String,Ship> entry: pTwo.getInventory().entrySet()){
                entry.getValue().getParts().forEach((k,v)->{
                    unionArea.add(entry.getKey()+k);
                });
            }


            Set<String> commonArea =  unionArea.stream().filter(part -> {
                return Collections.frequency(unionArea,part)>1;
            }).collect(Collectors.toSet());

            if (commonArea.size()>0){
                throw new IllegalStateException("Players cannot park ship in common at "+Arrays.toString(commonArea.toArray()));
            }

        }
    },
    NULL_CHECK{
        @Override
        public void validate(Object input) {
            if (input==null)
                throw new NullPointerException("Input cannot be Empty");
        }
    };

    public abstract void validate(Object input);

};


