package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class ResetCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Dao.user.resetHand();
		Dao.dealer.resetHand();
		Dao.dealer.shuffleDeck();
		
		System.out.println(Dao.user.getBet());
		
	}

}
