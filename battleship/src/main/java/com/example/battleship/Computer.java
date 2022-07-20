package com.example.battleship;

import java.util.Random;

public class Computer extends Player{

    private static Random random = new Random();

    @Override
    public boolean initialize() {
        //randomly generated ships positions having in mind restrictions for coordinates and intersections
        int i = 0;

        while(i < 5) {
            i++;
            char dir = random.nextBoolean() ? 'd' : 'r';

            Field f = genCoordinate(dir, i);

            while(hasIntersections(f))
                f = genCoordinate(dir, i);

            this.addShip(new Ship(f, dir, i));
        }

        return true;
    }

    private Field genCoordinate(char dir, int size){
        int x, y;
        if (dir == 'd'){
            x = random.nextInt(10 - size);
            y = random.nextInt(10);
        }
        else{
            x = random.nextInt(10);
            y = random.nextInt(10 - size);
        }
        return new Field(x, y);
    }

    private boolean hasIntersections(Field f){

        for(Ship s : getShips().keySet()){
            if (s.getFieldsTaken().keySet().contains(f))
                return true;
        }

        return false;
    }
}
