package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class StayCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		String path = Dao.dealer.getHand().get(0).getPath();
		int value = Dao.dealer.handValue();
		int uV = Dao.user.handValue();
		
		
		System.out.println("userValue : " + uV);
		System.out.println("dealerValue : " + value);
		
		String json = "{\"path\":\"" + path + "\", \"value\":\"" + value + "\", \"userValue\":\"" + uV + "\"}";
		
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

}
