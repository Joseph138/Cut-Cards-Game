/*
Assignment: Homework 3
Professor: Dr.Lutz
Course: ITEC 3150 Advance Programming
Date: 3/14/2023
Attribute: Paul Berger
 */
package com.example.homework3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class CutCards extends Application {

    public static final String TOTAL_WINS_FORMAT = "%s Total Wins : %d";
    public static final String WINNER_FMT = "%s Wins";
    public static final String TIE = "Tie";
    public static final String EMP = "";
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage mainStage){
        GameController gc = new GameController("Joseph", "The Machine");
        //Player UI objects which is instantiated from the GameController object
        PlayerUI playerUI1 = new PlayerUI(gc.p1);
        PlayerUI playerUI2 = new PlayerUI(gc.p2);

        //Making the buttons for the application
        Button cutButton = new Button("Joseph Cuts");
        Button startOverButton = new Button("Start Over!");
        //The button will be disabled with a greyish color to signify it
        startOverButton.setDisable(true);
        Label st = new Label("");

        //Creating the pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.setVgap(15);
        gridPane.setHgap(15);
        //The placement of the text will be placed in the application
        gridPane.add(playerUI1.score,0,0);
        gridPane.add(playerUI1.pane,0,1);
        gridPane.add(playerUI2.score,1,0);
        gridPane.add(playerUI2.pane,1,1);
        //Placement of the button in the application
        gridPane.add(cutButton,0,2);
        gridPane.add(startOverButton,1,2);
        GridPane.setHalignment(startOverButton, HPos.RIGHT);
        gridPane.add(st,0,3);

        //The action if the user click the cut button
        cutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //The title will place from Joseph cuts to The Machine Cuts
                cutButton.setText("The Machine Cuts");
                if(gc.state == GameController.State.PLAYER_1_COMPLETED){
                    cutButton.setDisable(true);
                    startOverButton.setDisable(false);
                }
                //Taken from the takeTurns method
                takeTurns(gc,playerUI1,playerUI2);
                //To keep score
                playerUI1.updateScore(gc.p1);
                playerUI2.updateScore(gc.p2);
                //The events after the machine cuts button has been clicked
                if(gc.state == GameController.State.PLAYER_2_COMPLETED){
                    try{
                        //Scenarios to determine the winner of the round
                        cutButton.setText("Joseph cuts");
                        if(gc.winner.equals(gc.p1)){
                            st.setText("Joseph wins");
                        }else if(gc.winner.equals(gc.p2)){
                            st.setText("The Machine wins");
                        }
                    }catch(NullPointerException ex){
                        //In case of a tie
                        st.setText("Tie!");
                    }
                }
            }
        });
        //The action when the user hits the start Over button
            startOverButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //Reset to the first player
                    cutButton.setText("Joseph Cuts");
                    st.setText("");
                    gc.state = GameController.State.NOT_STARTED;
                    gc.winner = null;
                    //setting the card back to the back slide
                    playerUI1.setImage(new Card());
                    playerUI2.setImage(new Card());
                    cutButton.setDisable(false);
                    startOverButton.setDisable(true);
                }
            });

            //Establishing scene on the application
        Scene scene = new Scene(gridPane);
        //Code if user hits the c key in the application
        scene.setOnKeyPressed(right -> {
            if(right.getCode() == KeyCode.C&& startOverButton.isDisabled()){
                cutButton.setText("The Machine Cuts");
                takeTurns(gc, playerUI1, playerUI2);
                playerUI1.updateScore(gc.p1);
                playerUI2.updateScore(gc.p2);
                if(gc.state.equals(GameController.State.PLAYER_2_COMPLETED)){
                    cutButton.setDisable(true);
                    startOverButton.setDisable(false);
                    try{
                        if(gc.winner.equals(gc.p1)){
                            st.setText("Joseph Wins");
                        }else if(gc.winner.equals(gc.p2)){
                            st.setText("The Machine Wins");
                        }
                    }catch(NullPointerException ex){
                        st.setText("Tie!");
                    }
                }
            }
            //If the user wants to hit the s key to as a replacement of the startOverButton
            else if(right.getCode() == KeyCode.S && cutButton.isDisabled()){
                cutButton.setText("Joseph Cuts");
                st.setText("");
                gc.state = GameController.State.NOT_STARTED;
               playerUI1.setImage(new Card());
                playerUI2.setImage(new Card());

                cutButton.setDisable(false);
                startOverButton.setDisable(true);
            }
        });
        //Making the stage for the application
        mainStage.setTitle("Cut Cards!");
        mainStage.setScene(scene);
        mainStage.show();


    }
//The method to help determine who's turn is it
private void takeTurns(GameController gc, PlayerUI p1, PlayerUI p2){
    if(gc.state.equals(GameController.State.NOT_STARTED)){
        p1.setImage(gc.takeTurn());
    }else if(gc.state.equals(GameController.State.PLAYER_1_COMPLETED)){
        p2.setImage(gc.takeTurn());
    }
}
}
