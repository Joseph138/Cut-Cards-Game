/*
Assignment: Homework 3
Professor: Dr.Lutz
Course: ITEC 3150 Advance Programming
Date: 3/14/2023
Attribute: Paul Berger
 */
package com.example.homework3;

import com.example.homework3.Card;
import com.example.homework3.CardValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


import static com.example.homework3.CutCards.TOTAL_WINS_FORMAT;
public class PlayerUI {
    Label score;
    private ImageView view;
    StackPane pane;

    public PlayerUI(Player player){
        this.view = new ImageView("File:images/cards/card_back.png");
        this.pane = new StackPane();
        pane.getChildren().add(view);
        String victories = String.format(TOTAL_WINS_FORMAT, player.name, player.numWins);
        this.score = new Label(victories);
        view.setFitHeight(726.0D/2.0D);
        view.setFitWidth(250.0D);
    }
    public void updateScore(Player player){
        score.setText(String.format(TOTAL_WINS_FORMAT, player.name, player.numWins));
    }
    public void setImage(Card card){
        //Set image to be the card back slide
        view.setImage(card.image());

    }
}
