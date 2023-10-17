package com.example.demo;

import Algorithm.KnightTour;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;


public class KnightsTour extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Knight's Tour");

        GridPane grid = new GridPane();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle square = new Rectangle();
                square.setWidth(100);
                square.setHeight(100);
                square.setFill((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                grid.add(square, i, j);
            }
        }

        ComboBox<Integer> xPos = new ComboBox<>();
        xPos.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);

        ComboBox<Integer> yPos = new ComboBox<>();
        yPos.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);

        Text t1 = new Text("Posicion inicial x: ");
        Text t2 = new Text("Posicion inicial y: ");

        VBox xBox = new VBox();
        xBox.getChildren().addAll(t1, xPos);
        xBox.setSpacing(5);

        VBox yBox = new VBox();
        yBox.getChildren().addAll(t2, yPos);
        yBox.setSpacing(5);

        Button startButton = new Button("Start");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(xBox, yBox);
        hbox.setSpacing(10);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(hbox, grid);
        // Set padding and alignment
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setAlignment(Pos.CENTER);

        HBox max = new HBox();
        max.getChildren().addAll(hbox2, hbox);
        xPos.valueProperty().addListener((obs, oldVal, newVal) -> {
            KnightTour.setStartx(xPos.getValue()-1);
            System.out.println("asdasd");
        });

        yPos.valueProperty().addListener((obs, oldVal, newVal) -> {
            KnightTour.setStarty(yPos.getValue()-1);
            System.out.println("asdasd");
        });

        startButton.setOnAction(e -> {
            int[][] tour = KnightTour.SolveA();
            KnightTour.printSolution(tour);
        });
        startButton.setBackground(Background.fill(Color.BLUEVIOLET));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(max, startButton);
        primaryStage.setScene(new Scene(vbox, 2000, 2000));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

