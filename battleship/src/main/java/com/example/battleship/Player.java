package com.example.battleship;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Player {

    //true if shot, false if not
    private Map<Ship, Boolean> ships;
    private Player opponent;

    //fields already shot
    private Set<Field> targeted;

    public Player() {
        this.ships = new TreeMap<>();
        this.targeted = new TreeSet<>();
        initialize();
    }

    public Map<Ship, Boolean> getShips() {
        return ships;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void addShip(Ship s){
        ships.put(s, false);
    }

    public void shootShip(Ship s){
        ships.put(s, true);
    }

    public boolean playMove(Field f){
        if(!targeted.contains(f)){
            targeted.add(f);
            for(Ship s : opponent.getShips().keySet()){
                if(s.isShipHere(f)){
                    s.fieldHit(f);
                    opponent.shootShip(s);
                    return true;
                }
            }
        }
        return false;

    }

    public boolean won(){
        for(boolean b : opponent.getShips().values()){
            if(!b){
                return false;
            }
        }
        return true;
    }

    public abstract boolean initialize();
}
