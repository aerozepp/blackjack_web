package core;

public class User extends Player {

	int mostChips = 0;
	int insurance = 0;

	public User() {
		super(100);
		mostChips = 100;
	}

	public Card getCard(Dealer dealer, int dealIndex) {
		Card card = dealer.dealCard(dealIndex);
		getHand().add(card);

		return card;
	}

	public void blackjack() {

		int bet = getBet();
		int win = (int) (bet * 2.5);
		int chips = getMoney();

		setMoney(win + chips);
	}

	public void win() {

		int bet = getBet();
		int win = (int) (bet * 2);
		System.out.println("win : " + win);
		int chips = getMoney();
		System.out.println("remain : " + chips);

		setMoney(win + chips);
	}

	public void lost() {

		int chips = getMoney();

		setMoney(chips);
	}

	public void tie() {

		int bet = getBet();
		int chips = getMoney();

		setMoney(bet + chips);
	}


	
	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
		setMoney(getMoney()-insurance);
	}

	public int getMostChips() {
		return mostChips;
	}

	public void setMostChips() {

		if (mostChips < getMoney()) {
			mostChips = getMoney();
		}
	}

}
