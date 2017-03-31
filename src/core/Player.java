package core;

import java.util.ArrayList;

public class Player {

    private String name;
    private int money;
    private int bet = 0;
    private ArrayList<Card> hand;


    public Player(int money) {
        this.money = money;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
    	
        this.bet += bet;
        this.money -= bet;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void resetHand(){
  
        System.out.println("============");
        System.out.println("reset hand");
        
        getHand().removeAll(hand);
        this.bet = 0;
 
    }

    public void showHand(){

        System.out.println("show hand");

        for(Card card : hand){
            System.out.println(card.getSuite());
            System.out.println(card.getFace());
            System.out.println("");
        }

    }
    
    public int handValue() {

		int sum = 0;

		for (Card card : hand) {
			sum += card.getValue();
		}

		return sum;
	}


}
