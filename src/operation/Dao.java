package operation;

import java.util.ArrayList;

import core.Card;
import core.Dealer;

import core.User;

public class Dao {

	public static User user;
	public static Dealer dealer;

	public void setUserAndDealer() {

		System.out.println("dao.setUandD()");
		user = new User();
		try {
			dealer = new Dealer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String deal(int bet) {

		String json = null;
		System.out.println("dao.Deal()");

		user.setBet(bet);

		user.getCard(dealer, dealer.getDealIndex());
		dealer.getCard(dealer.getDealIndex());
		user.getCard(dealer, getDealer().getDealIndex());
		dealer.getCard(dealer.getDealIndex());

		user.showHand();
		dealer.showHand();

		int userValue = user.handValue();
		System.out.println("userValue: " + userValue);

		String card1_path = user.getHand().get(0).getPath();
		String card2_path = user.getHand().get(1).getPath();
		String dCard_path = dealer.getHand().get(1).getPath();
		String dCard_face = dealer.getHand().get(1).getFace();
		String dCard_turn = dealer.getHand().get(0).getPath();
		int dCard_value = dealer.handValue();

		json = "{\"value\":\"" + userValue + "\",\"card1_path\":\"" + card1_path + "\", \"card2_path\":\"" + card2_path
				+ "\", \"dCard_path\":\"" + dCard_path + "\", \"dCard_face\":\"" + dCard_face + "\", \"dCard_value\":\""
				+ dCard_value + "\", \"dCard_turn\":\"" + dCard_turn + "\"   }";

		System.out.println(json);
		return json;

	}

	public String[] hit() {

		user.getCard(dealer, dealer.getDealIndex());

		int userValue = user.handValue();
		System.out.println("userValue: " + userValue);

		int handIndex = 2;

		int size = user.getHand().size() - 1;

		System.out.println("stringSize: " + size);
		String[] path = new String[size];

		for (int i = 0; i < size - 1; i++) {

			System.out.println("path[i] : " + i);
			path[i] = user.getHand().get(handIndex).getPath();
			handIndex += 1;
		}

		path[size - 1] = String.valueOf(userValue);

		return path;

	}

	public String[] dealerHit() {

		while (dealer.handValue() < 17) {

			dealer.getCard(dealer.getDealIndex());

			for (Card card : dealer.getHand()) {

				if (card.getFace().equals("ace")) {

					if (dealer.handValue() > 21) {
						card.setValue(1);
					}
				}
			}

		}

		int handIndex = 2;

		int size = dealer.getHand().size() - 1;

		System.out.println("stringSize: " + size);
		String[] path = new String[size];

		for (int i = 0; i < size - 1; i++) {

			System.out.println("path[i] : " + i);
			path[i] = dealer.getHand().get(handIndex).getPath();
			handIndex += 1;
		}

		path[size - 1] = String.valueOf(dealer.handValue());

		return path;
	}

	public ArrayList<String> ace() {

		int aceIndex = 0;
		String haveAce = "false";
		ArrayList<String> path = new ArrayList<String>();

		for (Card card : user.getHand()) {

			if (card.getFace().equals("ace")) {
				haveAce = "true";
				path.add(user.getHand().get(aceIndex).getPath());
				aceIndex++;
			} else {
				aceIndex++;
			}
		}

		path.add(haveAce);

		return path;
	}

	public int win() {

		user.win();

		return user.getMoney();
	}

	public int lost() {

		user.lost();
		return user.getMoney();
	}

	public int tie() {

		user.tie();
		return user.getMoney();
	}

	public int blackjack() {

		user.blackjack();

		return user.getMoney();
	}

	public int insurance(int bet) {

		user.setInsurance(bet);
		System.out.println("user insurance : " + user.getInsurance());
		System.out.println("user chip remain : " + user.getMoney());

		return user.getMoney();
	}

	public int winInsurance() {

		int win = user.getInsurance() * 2;

		user.setMoney(user.getMoney() + win);

		System.out.println("you took insurance: " + win);

		return user.getMoney();
	}

	public User getUser() {
		return user;
	}

	public Dealer getDealer() {
		return dealer;
	}

}
