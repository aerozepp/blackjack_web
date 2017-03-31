package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class DealerBlackjackCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Dao dao = new Dao();
		
		int chip = dao.winInsurance();
		
		System.out.println("get insurance:" + chip);
		String path = Dao.dealer.getHand().get(0).getPath();
		
		String json = "{\"chip\":\""+chip+"\", \"turn\":\""+path+"\" }";
		
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
