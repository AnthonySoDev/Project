/*
Deck.java
Designed by Anthony Kervin

This is a class to create the card deck used in the blackjack game.
*/

// Collections allows ArrayList shuffling
import java.util.Collections;
import java.util.ArrayList;

public class Deck{

    // PROPERTIES

    // Making an ArrayList "cards" to hold the deck
    ArrayList<String> cards=new ArrayList<String>();

    // CONSTRUCTOR

    // Generates cards for the deck
    public Deck(){
        cards.clear();
        for(int i=2; i<11;i++){
            cards.add("H"+i);
            cards.add("C"+i);
            cards.add("D"+i);
            cards.add("S"+i);
        }
        for(int i=1;i<5;i++){
            if(i==1){
                cards.add("HJ");
                cards.add("CJ");
                cards.add("DJ");
                cards.add("SJ");
            }if(i==2){
                cards.add("HQ");
                cards.add("CQ");
                cards.add("DQ");
                cards.add("SQ");
            }if(i==3){
                cards.add("HK");
                cards.add("CK");
                cards.add("DK");
                cards.add("SK");
            }if(i==4){
                cards.add("HA");
                cards.add("CA");
                cards.add("DA");
                cards.add("SA");
            }
        }
    }
    
    // METHODS

    // Prints every card in the deck
    public void showDeck(){
        System.out.println("There are "+cards.size()+" cards.");
        for(int i=0; i<cards.size();i++){
            System.out.print(cards.get(i)+", ");
        }
        System.out.println();
    }

    // Removes card from deck and places it into String "temp"
    public String dealCards(){
        String temp;
        temp=cards.remove(cards.size()-1);
        return temp;
    }

    // Shuffles the ArrayList "cards" (the deck)
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }

}
