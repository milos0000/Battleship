package com.example.battleship;

import java.util.Map;
import java.util.TreeMap;

public class Ship implements Comparable<Ship>{
    private Field start, last;
    private char direction;
    private int shipSize;
    private Map<Field, Boolean> fieldsTaken;

    public Ship(Field starting, char dir, int ssize){
        start = starting;
        direction = dir;
        shipSize = ssize;

        int x = start.getX();
        int y = start.getY();

        fieldsTaken = new TreeMap<>();


        if(direction == 'r'){
            for (int i = 0; i < shipSize; i++) {
                Field taken = new Field(x, y + i);
                fieldsTaken.put(taken, false);
                if(i == shipSize-1){
                    last = new Field(taken.getX(), taken.getY());
                }
            }

        }else{
            for (int i = 0; i < shipSize; i++) {
                Field taken = new Field(x + i, y);
                fieldsTaken.put(taken, false);
                if(i == shipSize-1){
                    last = new Field(taken.getX(), taken.getY());
                }
            }
        }
    }

    public Field getStart() {
        return start;
    }

    boolean isShipHere (Field k) {
        return fieldsTaken.keySet().contains(k);
    }

    public Map<Field, Boolean> getFieldsTaken() {
        return fieldsTaken;
    }

    void fieldHit(Field k){
        fieldsTaken.put(k, true);
    }

    @Override
    public String toString() {
        return start.toString() + " - " + last.toString() + " (" + shipSize + ")\n";
    }

    //Right oriented ships first then based on position
    @Override
    public int compareTo(Ship o) {
        if (this.direction > o.direction)
            return -1;
        else if (this.direction < o.direction)
            return 1;
        else
            return this.getStart().compareTo(o.getStart());
    }
}
