package com.example.battleship;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Human extends Player{

    @Override
    public boolean initialize() {
        //ships locations is in file
        Path path = Paths.get("ships.txt");

        try (Scanner sc = new Scanner(path)){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                Field f = new Field(Integer.parseInt(tokens[0].trim()), Integer.parseInt(tokens[1].trim()));
                Ship s = new Ship(f, tokens[2].trim().charAt(0), Integer.parseInt(tokens[3].trim()));
                this.addShip(s);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
