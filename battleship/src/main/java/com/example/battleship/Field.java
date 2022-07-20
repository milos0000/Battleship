package com.example.battleship;

public class Field implements Comparable<Field>{
    private int x, y;

    public Field(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    //X coordinate ascending, then y coordinate ascending
    @Override
    public int compareTo(Field o) {
        if (this.getX() != o.getX())
            return this.getX() - o.getX();
        else
            return this.getY() - o.getY();
    }
}
