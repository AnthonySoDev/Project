/*
Player.java
Designed by Anthony Kervin

This is a class used to create the players in the Blackjack game.
*/

import java.util.ArrayList;

public class Player{
    
    // PROPERTIES

    // Making an ArrayList "hand" to hold the player's cards
    ArrayList<String> hand=new ArrayList<String>();

    // Creating a blank name for each player
    String name="";

    // CONSTRUCTORS

    // Player constructor
    public Player(String s){
        name=s;
    }

    // METHODS

    // Adds String item to "hand" ArrayList
    public void drawCard(String s){
        hand.add(s);
    }

    // Gets ArrayList "hand" (player's cards) for img
    public String getCardFromHand(int i){
        String temp=hand.get(i);
        return temp;
    }

    // Checks player's hand value (non-aces first)
    public int cardsValue(){
        int totalValue=0;
        int aces=0;
        for(int i=0;i<hand.size();i++){
            switch(hand.get(i)){
                case "D2":
                case "H2":
                case "C2":
                case "S2": totalValue +=2 ; break;

                case "D3":
                case "H3":
                case "C3":
                case "S3": totalValue +=3 ; break;

                case "D4":
                case "H4":
                case "C4":
                case "S4": totalValue +=4 ; break;

                case "D5":
                case "H5":
                case "C5":
                case "S5": totalValue +=5 ; break;

                case "D6":
                case "H6":
                case "C6":
                case "S6": totalValue +=6 ; break;

                case "D7":
                case "H7":
                case "C7":
                case "S7": totalValue +=7 ; break;

                case "D8":
                case "H8":
                case "C8":
                case "S8": totalValue +=8 ; break;

                case "D9":
                case "H9":
                case "C9":
                case "S9": totalValue +=9 ; break;

                case "D10":
                case "H10":
                case "C10":
                case "S10": totalValue +=10 ; break;

                case "DJ":
                case "HJ":
                case "CJ":
                case "SJ": totalValue +=10 ; break;

                case "DQ":
                case "HQ":
                case "CQ":
                case "SQ": totalValue +=10 ; break;
                
                case "DK":
                case "HK":
                case "CK":
                case "SK": totalValue +=10 ; break;

                case "DA":
                case "HA":
                case "CA":
                case "SA": aces +=1 ; break;
            }
        }

        // Gives value to any aces in hand AFTER evaluating other cards
        switch(aces){
            case 1:
                if(totalValue>10){
                    totalValue+=1;
                }else{
                    totalValue+=11;
                }
                break;
            case 2:
                if(totalValue+12>21){
                    totalValue+=2;
                }else{
                    totalValue+=12;
                }
                break;
            case 3:
                if(totalValue+13>21){
                    totalValue+=3;
                }else{
                    totalValue+=13;
                }
                break;
            case 4:
                if(totalValue+14>21){
                    totalValue+=4;
                }else{
                    totalValue+=14;
                }
                break;
        }
        return totalValue;
    }
}