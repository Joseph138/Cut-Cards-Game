/*
Assignment: Homework 3
Professor: Dr.Lutz
Course: ITEC 3150 Advance Programming
Date: 3/14/2023
Attribute: Code Dr. Lutz
 */
package com.example.homework3;

//import main.java.edu.ggc.itec.cutcardscli.cardcontent.Card;

public class Player {

    Card lastCard;
    public int numWins;
    public String name;

    public Player(String name) {
        this.numWins = 0;
        this.lastCard = null;
        this.name = name;
    }

    public void incrementWins() {
        numWins++;
    }
}
