/*
Assignment: Homework 3
Professor: Dr.Lutz
Course: ITEC 3150 Advance Programming
Date: 3/14/2023
Attribute: Code Dr. Lutz
 */
package com.example.homework3;

public enum CardValue {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14),
    JOKER(15);

    private int cardValue;

    private CardValue(int value) {
        this.cardValue = value;
    }

    public int getCardValue() {
        return cardValue;
    }

}
