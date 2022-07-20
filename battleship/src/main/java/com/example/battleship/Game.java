package com.example.battleship;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {

    private Player comp, human;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Button btInit = new Button("Initialize game");
        Label lbMoves = new Label("Moves history:");
        TextArea taMoves = new TextArea();


        HBox hbMove = new HBox(10);
        Button btPlay = new Button("Play move");
        Label lbX = new Label("x:");
        TextField tfX = new TextField();
        tfX.setMaxWidth(50);
        Label lbY = new Label("y:");
        TextField tfY = new TextField();
        tfY.setMaxWidth(50);
        hbMove.getChildren().addAll(lbX, tfX, lbY, tfY, btPlay);

        root.getChildren().addAll(btInit, lbMoves, taMoves, hbMove);

        //Start the game
        btInit.setOnAction(actionEvent -> {
            human = new Human();
            comp = new Computer();
            human.setOpponent(comp);
            comp.setOpponent(human);

            taMoves.setText("Human:\n");
            for (Ship s : human.getShips().keySet())
                taMoves.appendText(s.toString());

            taMoves.appendText("\nComputer:\n");
            for (Ship s : comp.getShips().keySet())
                taMoves.appendText(s.toString());


            taMoves.appendText("----------------------------------\n");
        });

        //Play next move
        btPlay.setOnAction(actionEvent -> {
            int x, y;
            Random random = new Random();
            try {
                x = Integer.parseInt(tfX.getText());
                y = Integer.parseInt(tfY.getText());

                if(x < 0 || x > 9 || y < 0 || y > 9)
                    throw new Exception();

                Field f = new Field(x, y);
                boolean p = human.playMove(f);
                taMoves.appendText("Human targets " + f + " and " + (p ? "hits" : "misses") + "!\n");

                if(p && human.won()){
                    taMoves.appendText("Human won!\n");
                    return;
                }

                x = random.nextInt(10);
                y = random.nextInt(10);
                f = new Field(x, y);

                p = comp.playMove(f);
                taMoves.appendText("Computer targets " + f + " and " + (p ? "hits" : "misses") + "!\n");
                taMoves.appendText("----------------------------------\n");

                if(p && comp.won()){
                    taMoves.appendText("Computer won!\n");
                }
            }catch (Exception e){
                taMoves.appendText("Coordinates not valid!");
            }

        });

        stage.setScene(new Scene(root, 400, 350));
        stage.setTitle("Battleship!");
        stage.show();
    }
}
